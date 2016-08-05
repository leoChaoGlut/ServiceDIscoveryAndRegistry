package leo.dto;

import java.io.Serializable;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午1:23:32
 * @description
 */

public class Result implements Serializable {

	private static final long serialVersionUID = 6255500986926448570L;

	private int code;
	private String msg;
	private Object data;

	public Result(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static Result success() {
		return new Result(200, "success", null);
	}

	public static Result success(Object data) {
		return new Result(200, "success", data);
	}

	public static Result failure() {
		return new Result(404, "failure", null);
	}

	public int getCode() {
		return code;
	}

	public Result setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Result setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public Result setData(Object data) {
		this.data = data;
		return this;
	}

}
