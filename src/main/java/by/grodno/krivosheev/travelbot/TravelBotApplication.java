package by.grodno.krivosheev.travelbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class TravelBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelBotApplication.class, args);
	}

	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return registry -> registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
	}
}
