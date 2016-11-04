package com.spc.jpa.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.spc.jpa.common.XssFilter;
import com.spc.jpa.interceptor.LocaleInterceptor;
import com.spc.jpa.interceptor.SessionAuthInterceptor;
import com.mangofactory.swagger.models.dto.ApiInfo;

/**
 * Api Resource configration setting
 * 
 * @author lKJ
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
@EnableWebMvc
@EnableSwagger
public class ApiResouceConfig extends WebMvcAutoConfigurationAdapter {

	@Autowired
	private SessionAuthInterceptor sessionAuthInterceptor;
	
	@Autowired
	private LocaleInterceptor localeInterceptor;
	
	/**
	 * jsp PreFix, PostFix
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/json/");
		viewResolver.setSuffix(".json");
		return viewResolver;
	}

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * configureDefaultServletHandling
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * addInterceptors
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionAuthInterceptor).addPathPatterns("/**")
						//유저 관련된 기능은 예외 처리.
		.excludePathPatterns("/swapi/user/login", "/swapi/user/logout", "/swapi/user/count", "/swapi/user/register",
				"/api/user/login", "/api/user/logout", "/api/user/count", "/api/user/register", "/api-docs", "/api-docs/**");
		registry.addInterceptor(localeInterceptor).addPathPatterns("/**");
	}
	
	/**
	 * configureMessageConverters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(stringHttpMessageConverter());
	}

	/**
	 * MappingJackson2HttpMessageConverter
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	/**
	 * StringHttpMessageConverter
	 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		stringConverter.setSupportedMediaTypes(
				Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
		return stringConverter;
	}

	/**
	 * ObjectMapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
		bean.setIndentOutput(true);
		bean.setFailOnUnknownProperties(false);
		bean.setSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		bean.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		bean.afterPropertiesSet();
		ObjectMapper objectMapper = bean.getObject();
		objectMapper.registerModule(new JodaModule());
		return objectMapper;
	}

	/**
	 * RepositoryRestConfigurer
	 */
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

		return new RepositoryRestConfigurerAdapter() {

			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.setBasePath("/api");
			}
		};
	}

	/**
	 * addResourceHandlers
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app/**").addResourceLocations("classpath:static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * addViewControllers
	 */
	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/").setViewName("forward:/app/login");
		 super.addViewControllers(registry);
	 }

	/**
	 * addCorsMappings
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	/**
	 * API 서버를 만들때만 사용
	 * 
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // you USUALLY want this
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public SwaggerSpringMvcPlugin swaggerSpringMvcPlugin() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(new ApiInfo("spc-api-swagger", null, null, null, null, null)).useDefaultResponseMessages(false)
				.includePatterns("/swapi.*");
	}
	
	@Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/**");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("XssFilter");
        return registration;
    }
	
}
