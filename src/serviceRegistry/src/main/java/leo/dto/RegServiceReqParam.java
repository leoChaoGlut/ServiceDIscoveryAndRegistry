package leo.dto;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午1:09:18
 * @description Request Parameter of Service Registration
 */

public class RegServiceReqParam {

	private String serviceName;
	private String host;
	private int port;
	private String healthCheckPath;

	public RegServiceReqParam() {
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHealthCheckPath() {
		return healthCheckPath;
	}

	public void setHealthCheckPath(String healthCheckPath) {
		this.healthCheckPath = healthCheckPath;
	}

	@Override
	public String toString() {
		return "ServiceReqParam [serviceName=" + serviceName + ", host=" + host + ", port=" + port
				+ ", healthCheckPath=" + healthCheckPath + "]";
	}

}
