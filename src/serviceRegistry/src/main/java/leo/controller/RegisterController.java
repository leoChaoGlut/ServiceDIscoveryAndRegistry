package leo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import leo.domainobject.HostAndPort;
import leo.domainobject.Result;
import leo.domainobject.ServicePool;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午1:18:34
 * @description
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/service")
public class RegisterController {

	// 请求头需要有:Content-type: application/x-www-form-urlencoded,否则参数获取不到.
	@RequestMapping(path = { "/register" }, method = RequestMethod.POST, produces = { "application/json" })
	Result registerService(String serviceName, String hostAndPort) {
		System.out.println("registerService:" + serviceName + "," + hostAndPort);
		Result result = null;
		try {
			ServicePool.put(serviceName, hostAndPort);
			result = Result.success();
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.failure();
			return result;
		}
		return result;
	}

	@RequestMapping(path = { "/all" }, method = RequestMethod.GET)
	Map<String, List<HostAndPort>> getServiceList() {
		Map<String, List<HostAndPort>> servicePool = ServicePool.getServicePool();
		System.out.println(JSON.toJSONString(servicePool));
		return servicePool;
	}
}
