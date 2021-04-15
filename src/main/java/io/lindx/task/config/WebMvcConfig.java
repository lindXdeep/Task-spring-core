package io.lindx.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import lombok.RequiredArgsConstructor;

/**
 * Web configuration implements {@link WebMvcConfigurer}
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */

@RequiredArgsConstructor

@Configuration
@EnableWebMvc
@ComponentScan(value = "io.lindx.task")
public class WebMvcConfig implements WebMvcConfigurer {

	private final ApplicationContext applicationContext;

	/**
	 * @return ResourceTemplateResolver.
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");
		return templateResolver;
	}

	/**
	 * @return SpringTemplateEngine.
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	/**
	 * ThymeleafViewResolver.
	 */
	@Override
	public void configureViewResolvers(final ViewResolverRegistry registry) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		registry.viewResolver(resolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry

				.addResourceHandler(

						"/img/**",

						"/css/**",

						"/libs/**")

				.addResourceLocations(

						"classpath:/static/img/",

						"classpath:/static/css/",

						"classpath:/static/libs/");
	}

}
