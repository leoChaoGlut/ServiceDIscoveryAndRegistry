package leo.domainobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午12:30:07
 * @description
 */

public class ServicePool {
	/**
	 * key:serviceName <br>
	 * value:list of HostAndPort
	 */
	private static Map<String, List<HostAndPort>> servicePool = new HashMap<>();

	private ServicePool() {
	}

	/**
	 * 
	 * @param serviceName
	 *            eg:userService
	 * @param hostAndPort
	 *            eg: 192.168.1.10:8888
	 */
	public static void put(String serviceName, String hostAndPort) {
		List<HostAndPort> hapList = servicePool.get(serviceName);
		HostAndPort hap = HostAndPort.newInstance().setHostAndPort(hostAndPort);
		if (hapList == null) {
			hapList = new ArrayList<>();
			hapList.add(hap);
			servicePool.put(serviceName, hapList);
		} else {
			hapList.add(hap);
		}
	}

	public static List<HostAndPort> get(String serviceName) {
		List<HostAndPort> hapList = servicePool.get(serviceName);
		if (hapList == null) {
			hapList = new ArrayList<>();
		}
		return hapList;
	}

	public static Map<String, List<HostAndPort>> getServicePool() {
		return servicePool;
	}

}
