package fr.dawan.AppliCFABack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;

@SpringBootApplication
public class AppliCfaBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliCfaBackApplication.class, args);
	}

	@Autowired
	private TokenInterceptor tokenInterceptor;

	@Bean
	public DtoMapper dtoMapper() {
		return new DtoMapperImpl();
	}

	@Bean
	public WebMvcConfigurer myMvcConfigurer() {

		return new WebMvcConfigurer() {

			// CORS ORIGIN
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/").allowedMethods("GET").allowedOrigins("*");
				registry.addMapping("/swagger-ui/index.html").allowedMethods("POST", "PUT").allowedOrigins("*");
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*","GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
//						.exposedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
//								"Origin", "Authorization", "X-Requested-With", "requestId", "Correlation-Id")

						.allowCredentials(true).maxAge(3600);
			}

			// Intercepteurs
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(tokenInterceptor);
			}

		};
	}
}
