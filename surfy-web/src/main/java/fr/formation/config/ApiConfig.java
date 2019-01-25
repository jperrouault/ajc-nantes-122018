package fr.formation.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableWebMvc
@ComponentScan("fr.formation.restcontroller")
@Import(JpaConfig.class)
public class ApiConfig implements WebMvcConfigurer {
	public MappingJackson2HttpMessageConverter jsonConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = jsonConverter.getObjectMapper();

		objectMapper.registerModule(new Hibernate5Module());
		return jsonConverter;
	}

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(this.jsonConverter());
	}
}