package leo.do_;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午1:38:59
 * @description
 */

public class HostAndPort {

	private String host;
	private int port;

	private final String DEFAULT_HOST = "127.0.0.1";
	private final int DEFAULT_PORT = 80;

	public HostAndPort() {
	}

	public HostAndPort(String host, int port) {
		if (host == null || "".equals(host.trim())) {
			host = DEFAULT_HOST;
		}
		if (port == 0) {
			port = DEFAULT_PORT;
		}
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public HostAndPort setHost(String host) {
		this.host = host;
		return this;
	}

	public int getPort() {
		return port;
	}

	public HostAndPort setPort(int port) {
		this.port = port;
		return this;
	}

	// TODO 这样的hash算法对于短字符串(大约6字符)才有非常高的效率,可能不适用于长字符串
	// TODO 这样的hash算法对于短字符串(大约6字符)才有非常高的效率,可能不适用于长字符串
	// TODO 这样的hash算法对于短字符串(大约6字符)才有非常高的效率,可能不适用于长字符串
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HostAndPort other = (HostAndPort) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return host + ":" + port;
	}

}
