package cn.luxun.mall.config;

import cn.luxun.mall.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private CheckTokenInterceptor checkTokenInterceptor;


	// 跨域配置
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080");
	}
/*
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 用户拦截器
		registry.addInterceptor(checkTokenInterceptor)
				// 需要拦截的请求
				.addPathPatterns("/**")
				// 需要放行的请求
				.excludePathPatterns("/user/login","/user/register")
				// 添加swagger-ui的放行路径
				.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**")
		;
	}*/
}
