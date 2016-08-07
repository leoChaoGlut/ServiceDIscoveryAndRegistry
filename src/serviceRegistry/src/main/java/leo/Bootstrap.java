package leo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午1:17:22
 * @description jdk1.8 or later is required
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Bootstrap implements EmbeddedServletContainerCustomizer {

	public static void main(String[] args) {
		SpringApplication.run(Bootstrap.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8889);
	}
}
