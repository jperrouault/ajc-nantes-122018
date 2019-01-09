package fr.formation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.formation.config.AppConfig;

public class SpringApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext =
			new AnnotationConfigApplicationContext(AppConfig.class);
		
		myContext.getBeanFactory()
			.createBean(AppListeProduit.class)
			.run(args);
		
		myContext.close();
	}
}