package leo.do_;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午12:32:25
 * @description
 */

public class ServiceInfo {

	private Set<HostAndPort> hostAndPortSet;
	private String healthCheckPath;// 发起健康检查时,调用的url,正常会返回"healthy"

	public ServiceInfo() {
	}

	public static ServiceInfo newInstance() {
		return new ServiceInfo().setHostAndPortSet(new HashSet<>());
	}

	public ServiceInfo addHostAndPort(String host, int port) {
		hostAndPortSet.add(new HostAndPort(host, port));
		return this;
	}

	public Set<HostAndPort> getHostAndPortSet() {
		return hostAndPortSet;
	}

	public ServiceInfo setHostAndPortSet(Set<HostAndPort> hostAndPortSet) {
		this.hostAndPortSet = hostAndPortSet;
		return this;
	}

	public String getHealthCheckPath() {
		return healthCheckPath;
	}

	public ServiceInfo setHealthCheckPath(String healthCheckPath) {
		this.healthCheckPath = healthCheckPath == null ? "" : healthCheckPath;
		return this;
	}

}
