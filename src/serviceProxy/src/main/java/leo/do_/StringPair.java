package leo.do_;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午11:14:21
 * @description
 */

public class StringPair {
	private String key;
	private String value;

	public StringPair() {
	}

	public StringPair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}

}
