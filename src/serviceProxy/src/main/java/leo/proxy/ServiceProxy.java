package leo.proxy;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import leo.do_.ServiceInfo;
import leo.do_.Url;
import leo.dto.Result;

/**
 * @author Leo
 * @datetime 2016年8月7日 下午11:50:13
 * @description serviceProxy的功能:<br>
 *              1.通过serviceName找到所有对应的可提供服务的url;<br>
 *              2.校验步骤1所获得的result;<br>
 *              3.通过自定义的负载均衡算法得出targetUrl;<br>
 *              4.执行请求;
 */

public interface ServiceProxy {
	/**
	 * 以serviceName作为请求参数,向setting.json所配置的serviceRegistry查找对应的服务信息
	 * 
	 * @param serviceName
	 * @return
	 */
	Result findServices(String serviceName);

	/**
	 * 对getServices()返回的结果进行校验
	 * 
	 * @param serviceName
	 * @param result
	 * @return
	 * @throws Exception
	 */
	boolean validate(String serviceName, Result result) throws Exception;

	/**
	 * 一个serviceName可能对应多个能提供相同服务的url,所以要进行选择.这里使用了随机数选择.可以根据需要,重写此处的算法.
	 * 
	 * @param url
	 * @param serviceInfo
	 * @throws Exception
	 */
	String loadBalance(Url url, ServiceInfo serviceInfo) throws Exception;

	/**
	 * 
	 * @param url
	 * @param req
	 *            eg:HttpGet,HttpPost,HttpDelete,HttpPost
	 * @return
	 * @throws Exception
	 */
	CloseableHttpResponse exec(Url url, HttpUriRequest req) throws Exception;

}
