package cn.gzsxt.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import cn.gzsxt.pms.interceptor.LoginInterceptor;
import cn.gzsxt.pms.interceptor.PowerInterceptor;

@Configuration
@EnableWebMvc
//注意事项：SpringMVC注解是通过WebMvcConfigurerAdapter适配器来配置<mvc:xxxx>组件
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	//视图解释器的配置
	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		//1.支持JSTL视图
		viewResolver.setViewClass(JstlView.class);
		//2.设置前缀
		viewResolver.setPrefix("/WEB-INF/views/");
		//3.设置后缀
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	//文件上传
	@Bean
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		//默认编码
		resolver.setDefaultEncoding("UTF-8");
		//默认上传文件最大大小
		resolver.setMaxUploadSize(20000000);
		//默认内存的大小
		resolver.setMaxInMemorySize(512000000);
		return resolver;
	}
	//<mvc:default-servlet-hanlder>
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	//<mvc:interceptors>，配置拦截器

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LoginInterceptor login = new LoginInterceptor();
		PowerInterceptor power = new PowerInterceptor();
		InterceptorRegistration registration = registry.addInterceptor(login);
		registration.addPathPatterns("/**");
		//排除登录不拦截
		registration.excludePathPatterns("/admin/doAJAXLogin.do");
		
		InterceptorRegistration powerRegistration = registry.addInterceptor(power);
		//拦截所有的请求
		powerRegistration.addPathPatterns("/**");
		//排除登录不拦截
		powerRegistration.excludePathPatterns("/admin/doAJAXLogin.do");
	     //判断注销不拦截
		powerRegistration.excludePathPatterns("/admin/logout.do");
		powerRegistration.excludePathPatterns("/toUpload");
		powerRegistration.excludePathPatterns("/admin/index.do");
		powerRegistration.excludePathPatterns("/admin/setAdminPwd.do");
		powerRegistration.excludePathPatterns("/admin/toAdminSetting.do");
		super.addInterceptors(registry);
	}
	//只是负责页面跳转，并没有业务处理的请求
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/toUpload").setViewName("upload");
	}
	

}
