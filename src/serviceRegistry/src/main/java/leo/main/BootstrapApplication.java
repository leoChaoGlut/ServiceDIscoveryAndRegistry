package leo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import leo.controller.RegisterController;

/**
 * @author Leo
 * @datetime 2016年8月5日 上午1:17:22
 * @description
 */

public class BootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterController.class, args);
	}
}
