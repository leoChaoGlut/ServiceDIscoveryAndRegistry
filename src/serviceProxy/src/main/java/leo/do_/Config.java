package leo.do_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午8:45:59
 * @description
 */

public class Config {

	private String scheme;
	private String host;
	private int port;

	private static final String DEFAULT_CONFIG_PATH = "/setting.json";
	private static final int DEFAULT_FILE_LENGTH = 256;
	private static final String REGISTER_PATH = "/service/register";
	private static final String DISCOVER_PATH = "/service/discover/";

	/**
	 * 每次调用,都会去读一次配置文件,保证是最新的配置.
	 * 
	 * @return
	 */
	public static Config newInstance() {
		StringBuilder sb = new StringBuilder(DEFAULT_FILE_LENGTH);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream(DEFAULT_CONFIG_PATH)));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return JSON.parseObject(sb.toString(), Config.class);
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
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

	public String getRegisterUrl() {
		return scheme + "://" + host + ":" + port + REGISTER_PATH;
	}

	public String getDiscoverUrl() {
		return scheme + "://" + host + ":" + port + DISCOVER_PATH;
	}

}
