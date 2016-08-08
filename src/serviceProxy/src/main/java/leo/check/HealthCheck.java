package leo.check;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Leo
 * @datetime 2016年8月5日 下午12:26:28
 * @description 实现这个接口,即可不添加任何代码,就能被服务中心检查实现了该接口的服务接口的健康状况.
 */

public interface HealthCheck {

	@RequestMapping
	default String healthCheck() {
		return "healthy";
	}
}
