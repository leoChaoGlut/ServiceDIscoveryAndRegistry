package leo.func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import leo.do_.AutoRegistry;
import leo.dto.Result;

/**
 * @author Leo
 * @datetime 2016年8月11日 下午11:03:40
 * @description
 */

public class AutoRegisterService {

	private static final int DEFAULT_BUILDER_SIZE = 64;

	/**
	 * 
	 * @param entity
	 * 
	 */
	public static void register(AutoRegistry entity) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(entity.getRegistryUrl());

		List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		nvpList.add(new BasicNameValuePair("serviceName", entity.getServiceName()));
		nvpList.add(new BasicNameValuePair("host", entity.getRegistryHost()));
		nvpList.add(new BasicNameValuePair("port", entity.getRegistryPort() + ""));
		nvpList.add(new BasicNameValuePair("healthCheckPath", entity.getHealthCheckPath()));

		BufferedReader br = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nvpList));
			CloseableHttpResponse response = httpClient.execute(post);
			if (response == null || response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Request failed");
			}
			br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			int contentLength = (int) response.getEntity().getContentLength();
			if (contentLength < 0) {
				contentLength = DEFAULT_BUILDER_SIZE;
			}
			StringBuilder sb = new StringBuilder(contentLength);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			Result result = JSON.parseObject(sb.toString(), Result.class);
			if (result == null) {
				throw new Exception("Request failed");
			} else if (result.getCode() != 200) {
				throw new Exception("Request Error:" + result.getMsg());
			} else {
				// service register successful
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
