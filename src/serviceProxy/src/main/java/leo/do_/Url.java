package leo.do_;

import java.util.List;

/**
 * @author Leo
 * @datetime 2016年8月6日 上午10:12:42
 * @description
 */

public class Url {

	public static final String WHITE_SPACE = " ";
	public static final String DEFAULT_SCHEMA = "http";
	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final int DEFAULT_PORT = 80;
	public static final int DEFAULT_URL_LENGTH = 64;

	private String schema;
	private String host;
	private int port;
	private String path;// eg:"/user/all"
	private List<StringPair> pathParams;
	private String serviceName;

	private Url() {
	}

	public static Url newInstance() {
		return new Url().setSchema(DEFAULT_SCHEMA).setHost(DEFAULT_HOST).setPort(DEFAULT_PORT);
	}

	public String asString() throws Exception {
		StringBuilder urlBuilder = new StringBuilder(DEFAULT_URL_LENGTH);
		urlBuilder.append(schema + "://" + host + ":" + port);
		if (!isBlank(path)) {
			urlBuilder.append(path);
		}
		if (!isEmpty(pathParams)) {
			urlBuilder.append("?" + pathParams.get(0).getKey() + "=" + pathParams.get(0).getValue());
			for (int i = 1; i < pathParams.size(); i++) {
				StringPair pair = pathParams.get(i);
				urlBuilder.append("&" + pair.getKey() + "=" + pair.getValue());
			}
		}
		String url = urlBuilder.toString();
		if (url.contains(WHITE_SPACE)) {
			throw new Exception("The url contains one or more whitespaces.");
		}
		return url;
	}

	public boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}

	public boolean isEmpty(List<StringPair> list) {
		return list == null || list.isEmpty();
	}

	public String getSchema() {
		return schema;
	}

	public Url setSchema(String schema) {
		this.schema = schema;
		return this;
	}

	public String getHost() {
		return host;
	}

	public Url setHost(String host) {
		this.host = host;
		return this;
	}

	public int getPort() {
		return port;
	}

	public Url setPort(int port) {
		this.port = port;
		return this;
	}

	public String getPath() {
		return path;
	}

	public Url setPath(String path) {
		this.path = path;
		return this;
	}

	public String getServiceName() {
		return serviceName;
	}

	public Url setServiceName(String serviceName) {
		this.serviceName = serviceName;
		return this;
	}

	public List<StringPair> getPathParams() {
		return pathParams;
	}

	public Url setPathParams(List<StringPair> pathParams) {
		this.pathParams = pathParams;
		return this;
	}

}
