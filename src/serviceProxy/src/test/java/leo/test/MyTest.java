package leo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import leo.do_.Url;
import leo.proxy.imp.SimpleServiceProxy;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午8:49:15
 * @description
 */

public class MyTest {
	/***
	 * 测试get请求
	 * 
	 * @throws IOException
	 */
	@Test
	public void test01() throws IOException {
		Url url = Url.newInstance().setServiceName("serviceRegistry01").setPath("/service/all");
		CloseableHttpResponse response = null;
		try {
			response = new SimpleServiceProxy().get(url);
			byte[] b = new byte[1024];
			response.getEntity().getContent().read(b);
			System.out.println(new String(b));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 测试post请求
	 */
	@Test
	public void test02() {
		Url url = Url.newInstance().setServiceName("serviceRegistry01").setPath("/service/register");
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost();
			List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
			nvpList.add(new BasicNameValuePair("serviceName", "serviceRegistry03"));
			nvpList.add(new BasicNameValuePair("host", "127.0.0.1"));
			nvpList.add(new BasicNameValuePair("port", "8888"));
			nvpList.add(new BasicNameValuePair("healthCheckPath", "/service"));
			post.setEntity(new UrlEncodedFormEntity(nvpList));
			response = new SimpleServiceProxy().exec(url, post);
			byte[] b = new byte[1024];
			response.getEntity().getContent().read(b);
			System.out.println(new String(b));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
