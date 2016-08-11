package leo.do_;

import javax.imageio.spi.RegisterableService;

/**
 * @author Leo
 * @datetime 2016年8月11日 下午11:40:35
 * @description
 */

public class AutoRegistry {

	private String registryHost;
	private int registryPort;
	private String serviceHost;
	private int servicePort;
	private String serviceName;
	private String healthCheckPath;
	private String registryPath = "/service/register";

	private static final String DEFAULT_HOST = "127.0.0.1";
	private static final int DEFAULT_PORT = 8080;

	private AutoRegistry() {

	}

	public static AutoRegistry newInstance() {
		return new AutoRegistry().setRegistryHost(DEFAULT_HOST).setRegistryPort(DEFAULT_PORT);
	}

	public String getRegistryHost() {
		return registryHost;
	}

	public AutoRegistry setRegistryHost(String registryHost) {
		this.registryHost = registryHost;
		return this;
	}

	public int getRegistryPort() {
		return registryPort;
	}

	public AutoRegistry setRegistryPort(int registryPort) {
		this.registryPort = registryPort;
		return this;
	}

	public String getServiceName() {
		return serviceName;
	}

	public AutoRegistry setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}

	public String getHealthCheckPath() {
		return healthCheckPath;
	}

	public AutoRegistry setHealthCheckPath(String healthCheckPath) {
		this.healthCheckPath = healthCheckPath;
		return this;
	}

	public String getRegistryPath() {
		return registryPath;
	}

	public AutoRegistry setRegistryPath(String registryPath) {
		this.registryPath = registryPath;
		return this;
	}

	public String getServiceHost() {
		return serviceHost;
	}

	public AutoRegistry setServiceHost(String serviceHost) {
		this.serviceHost = serviceHost;
		return this;
	}

	public int getServicePort() {
		return servicePort;
	}

	public AutoRegistry setServicePort(int servicePort) {
		this.servicePort = servicePort;
		return this;
	}

	public String getRegistryUrl() {
		String url = "http://" + registryHost + ":" + registryPort + registryPath;
		System.out.println(url);
		return url;
	}
}
