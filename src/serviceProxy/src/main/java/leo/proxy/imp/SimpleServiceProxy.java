package leo.proxy.imp;

import java.net.URI;
import java.util.Random;
import java.util.Set;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;

import leo.do_.Config;
import leo.do_.HostAndPort;
import leo.do_.ServiceInfo;
import leo.do_.Url;
import leo.dto.Result;
import leo.proxy.ServiceProxy;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午9:57:21
 * @description
 */

public class SimpleServiceProxy implements ServiceProxy {

	public CloseableHttpResponse exec(Url url, HttpUriRequest req) throws Exception {
		if (url == null) {
			throw new Exception("Url is null");
		}
		Result result = findServices(url.getServiceName()); // 1.通过serviceName找到所有对应的可提供服务的url;
		boolean passValidation = validate(url, result);// 2.校验result
		String targetUrl = null;
		if (passValidation) {
			ServiceInfo serviceInfo = JSON.parseObject(result.getData().toString(), ServiceInfo.class);
			targetUrl = loadBalance(url, serviceInfo);// 3.通过负载均衡算法得出targetUrl
		}
		if (targetUrl == null) {
			throw new Exception("Get target url failed");
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		((HttpRequestBase) req).setURI(URI.create(targetUrl));
		return httpClient.execute(req);// 4.执行请求
	}

	public Result findServices(String serviceName) {
		String resultString = null;
		try {
			resultString = Request.Get(Config.newInstance().getDiscoverUrl() + serviceName).execute().returnContent()
					.asString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(resultString);// Test
		return JSON.parseObject(resultString, Result.class);
	}

	public String loadBalance(Url url, ServiceInfo serviceInfo) throws Exception {
		Set<HostAndPort> hapSet = serviceInfo.getHostAndPortSet();
		int randomIndex = new Random().nextInt(hapSet.size());
		int i = 0;
		for (HostAndPort hap : hapSet) {
			if (randomIndex == i++) {
				url.setHost(hap.getHost()).setPort(hap.getPort());
				return url.asString();
			}
		}
		return null;
	}

	public boolean validate(Url url, Result result) throws Exception {
		if (result == null) {
			throw new Exception("Can't link to the service registry of " + Config.newInstance().getDiscoverUrl());
		} else if (result.getCode() != 200) {
			throw new Exception("Service registry return an error msg:" + result.getMsg());
		} else if (result.getData() == null) {
			throw new Exception("Service:" + url.getServiceName() + " not found");
		} else {
			return true;
		}
	}

	/**
	 * 简单的get请求可直接调用该方法
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public CloseableHttpResponse get(Url url) throws Exception {
		return exec(url, new HttpGet());
	}
}
