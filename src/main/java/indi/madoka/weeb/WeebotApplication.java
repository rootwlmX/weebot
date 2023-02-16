package indi.madoka.weeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Arcueid
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WeebotApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(WeebotApplication.class, args);
	}
}
