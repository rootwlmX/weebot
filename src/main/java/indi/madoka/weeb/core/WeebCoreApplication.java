package indi.madoka.weeb.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WeebCoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(WeebCoreApplication.class, args);
	}

	/*	@Bean
	public WebSocketClient webSocketClient() {
		try {
			WeebWebSocketClient webSocketClient = new WeebWebSocketClient(new URI("ws://127.0.0.1:5701"));
			webSocketClient.connect();
			return webSocketClient;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
