package cn.com.inlee.authority;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebMvcConfigurerAdapter {

	
	@Value("${security.exclude.path.patterns}")
	private String[] excludePaths;
	
	@Bean
	public SecurityAnnotationHandler securityInterceptor() {
		return new SecurityAnnotationHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//.excludePathPatterns("/static/*", "/login", "/error")
		registry.addInterceptor(securityInterceptor()).excludePathPatterns(excludePaths)
				.addPathPatterns("/**");
	}

}
