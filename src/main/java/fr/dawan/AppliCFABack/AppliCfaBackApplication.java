package fr.dawan.AppliCFABack;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;

@SpringBootApplication
@EnableAsync
public class AppliCfaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliCfaBackApplication.class, args);
	}

	@Autowired
	private TokenInterceptor tokenInterceptor;

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory  = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(20_000);
        clientHttpRequestFactory.setReadTimeout(0);
        return new RestTemplate(clientHttpRequestFactory);
	}

	@Bean
	public DtoMapper dtoMapper() {
		return new DtoMapperImpl();
	}

	@Bean("myTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("asyncThread-");
        executor.initialize();
        return executor;
    }
	
	@Bean
	public WebMvcConfigurer myMvcConfigurer() {

		return new WebMvcConfigurer() {

			// CORS ORIGIN
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/swagger-ui/index.html").allowedMethods("POST", "PUT").allowedOrigins("*");
				registry.addMapping("/**").allowedOrigins("*")
						.allowedMethods("*", "GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
//						.exposedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
//								"Origin", "Authorization", "X-Requested-With", "requestId", "Correlation-Id")

						.allowCredentials(true).maxAge(3600);
			}

			// Intercepteurs
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(tokenInterceptor);
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
				registry.addResourceHandler("pictures/**").addResourceLocations("classpath:/pictures/");
			}

		};
	}
}
