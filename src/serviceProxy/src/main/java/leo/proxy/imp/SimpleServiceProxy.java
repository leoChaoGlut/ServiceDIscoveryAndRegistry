package leo.proxy.imp;

import java.lang.ref.SoftReference;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

	private static SoftReference<Set<HostAndPort>> recordSetRef = new SoftReference<>(new HashSet<>());

	private final Lock lock = new ReentrantLock();

	@Override
	public CloseableHttpResponse exec(Url url, HttpUriRequest req) throws Exception {
		if (url == null) {
			throw new Exception("Url is null");
		}
		Result result = findServices(url.getServiceName()); // 1.通过serviceName找到所有对应的可提供服务的url;
		boolean passValidation = validate(url.getServiceName(), result);// 2.校验result
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

	@Override
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

	@Override
	public String loadBalance(Url url, ServiceInfo serviceInfo) throws Exception {
		Set<HostAndPort> hapSet = serviceInfo.getHostAndPortSet();
		List<HostAndPort> hapList = new ArrayList<>(hapSet);
		int size = hapList.size();
		lock.lock();// 加锁是为了防止SoftReference被引用的时候,正好要发生OOM导致SoftReference所指向的对象为空,导致的空指针异常.
		try {
			for (int i = 0; i < size; i++) {
				HostAndPort hap = hapList.get(i);
				Set<HostAndPort> recordSet = recordSetRef.get();
				if (recordSet == null) {
					recordSetRef = new SoftReference<>(new HashSet<>());
					recordSet = recordSetRef.get();
				}
				if (recordSet.contains(hap)) {
					if (i + 1 == size) {
						recordSet.clear();
						i = 0;
					}
				} else {
					recordSet.add(hap);
					url.setHost(hap.getHost()).setPort(hap.getPort());
					return url.asString();
				}
			}
		} finally {
			lock.unlock();
		}
		return null;
	}

	@Override
	public boolean validate(String serviceName, Result result) throws Exception {
		if (result == null) {
			throw new Exception("Can't link to the service registry of " + Config.newInstance().getDiscoverUrl());
		} else if (result.getCode() != 200) {
			throw new Exception("Service registry return an error msg:" + result.getMsg());
		} else if (result.getData() == null) {
			throw new Exception("Service:" + serviceName + " not found");
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
