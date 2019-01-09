package fr.formation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan("fr.formation")
public class AppConfig {
	@Bean
	public PropertySourcesPlaceholderConfigurer propSources() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}