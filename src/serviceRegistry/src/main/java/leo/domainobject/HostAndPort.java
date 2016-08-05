package leo.domainobject;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午12:32:25
 * @description
 */

public class HostAndPort {

	private String hostAndPort;
	private int usedCount;// default value : 0

	public HostAndPort() {
	}

	public static HostAndPort newInstance() {
		return new HostAndPort();
	}

	public String getHostAndPort() {
		return hostAndPort;
	}

	public HostAndPort setHostAndPort(String hostAndPort) {
		this.hostAndPort = hostAndPort;
		return this;
	}

	public int getUsedCount() {
		return usedCount;
	}

	public HostAndPort setUsedCount(int usedCount) {
		this.usedCount = usedCount;
		return this;
	}

}
