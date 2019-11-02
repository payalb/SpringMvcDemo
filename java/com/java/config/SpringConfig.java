package com.java.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(basePackages = "com.java")
@EnableWebMvc
@Configuration
@Import(DatabaseConfig.class)
@PropertySource("classpath:database-${spring.profiles.active}.properties")
public class SpringConfig implements WebMvcConfigurer {

	@Value("${jdbc.url}")
	String url;
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholder() {
		PropertySourcesPlaceholderConfigurer pc= new PropertySourcesPlaceholderConfigurer();
		return pc;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/");
		vr.setSuffix(".jsp");
		registry.viewResolver(vr);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// static pages
		registry.addResourceHandler("/resource/**").addResourceLocations("classpath:/resource/")
				.setCacheControl(CacheControl.maxAge(24, TimeUnit.HOURS));

	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.addBasenames("classpath:message");
		// Bydefault accepts locale information fro Accept-language header
		return ms;
	}

	//It will tell spring to retrieve this locale information from locale req param instead of from header
	@Bean
	public LocaleChangeInterceptor interceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("locale");
		return interceptor;
	}
/*
	//It will save this locale information  in session
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}*/
	
	@Bean("localeResolver")
	public CookieLocaleResolver resolver() {
		CookieLocaleResolver resolver= new CookieLocaleResolver();
		resolver.setCookieName("lango");
		
		resolver.setDefaultLocale(Locale.ENGLISH);
		resolver.setCookieMaxAge(24*60*60);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor());
	}


}
/* /resource/js/one/one.js */