package ru.job4j.forum.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/styles/**")
			.addResourceLocations("/WEB-INF/template/css/");
		registry
			.addResourceHandler("/scripts/**")
			.addResourceLocations("/WEB-INF/template/js/");
		registry
			.addResourceHandler("/fonts/**")
			.addResourceLocations("/WEB-INF/template/fonts/");
	}
}
