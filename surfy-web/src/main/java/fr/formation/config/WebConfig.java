package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("fr.formation")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}

//	@Bean
//	public UrlBasedViewResolver viewResolver() {
//		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
//
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//
//		return viewResolver;
//	}
	
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
	  SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

	  templateResolver.setPrefix("/WEB-INF/templates/");
	  templateResolver.setSuffix(".html");

	  return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
	  SpringTemplateEngine templateEngine = new SpringTemplateEngine();

	  templateEngine.setTemplateResolver(templateResolver);
	  templateEngine.setEnableSpringELCompiler(true);

	  return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine) {
	  ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

	  viewResolver.setTemplateEngine(templateEngine);
	  return viewResolver;
	}
}