package leo.dto;

import java.util.List;

import leo.do_.StringPair;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午11:07:35
 * @description
 */

public class Request {
	private List<StringPair> headers;
	private List<StringPair> params;

	public List<StringPair> getHeaders() {
		return headers;
	}

	public Request setHeaders(List<StringPair> headers) {
		this.headers = headers;
		return this;
	}

	public List<StringPair> getParams() {
		return params;
	}

	public Request setParams(List<StringPair> params) {
		this.params = params;
		return this;
	}

}
