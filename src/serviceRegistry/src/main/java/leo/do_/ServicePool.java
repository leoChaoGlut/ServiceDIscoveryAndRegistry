package leo.do_;

import java.util.HashMap;
import java.util.Map;

import leo.dto.RegServiceReqParam;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午12:30:07
 * @description
 */

public class ServicePool {

	private static Map<String, ServiceInfo> servicePool = new HashMap<>();

	private ServicePool() {
	}

	public static void put(RegServiceReqParam reqParam) {
		String serviceName = reqParam.getServiceName();
		ServiceInfo serviceInfo = servicePool.get(serviceName);
		if (serviceInfo == null) {
			serviceInfo = ServiceInfo.newInstance().addHostAndPort(reqParam.getHost(), reqParam.getPort())
					.setHealthCheckPath(reqParam.getHealthCheckPath());
			servicePool.put(serviceName, serviceInfo);
		} else {
			serviceInfo.addHostAndPort(reqParam.getHost(), reqParam.getPort())
					.setHealthCheckPath(reqParam.getHealthCheckPath());
		}
	}

	public static ServiceInfo get(String serviceName) {
		return servicePool.get(serviceName);
	}

	public static Map<String, ServiceInfo> getServicePool() {
		return servicePool;
	}

}
