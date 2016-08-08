package leo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import leo.check.HealthCheck;
import leo.do_.ServiceInfo;
import leo.do_.ServicePool;
import leo.dto.RegServiceReqParam;
import leo.dto.Result;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午1:18:34
 * @description
 */
@RestController
@RequestMapping("/service")
public class ServiceController implements HealthCheck {

	// 请求头需要有:Content-type: application/x-www-form-urlencoded,否则参数获取不到.
	@RequestMapping(path = { "/register" }, method = RequestMethod.POST)
	Result registerService(RegServiceReqParam reqParam) {
		Result result = null;
		try {
			ServicePool.put(reqParam);
			result = Result.success();
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.failure();
		}
		return result;
	}

	@RequestMapping(path = { "/discover/{serviceName}" })
	Result discoverService(@PathVariable String serviceName) {
		return Result.success(ServicePool.get(serviceName));
	}

	@RequestMapping(path = { "/all" })
	Map<String, ServiceInfo> getServiceList() {
		return ServicePool.getServicePool();
	}

}
