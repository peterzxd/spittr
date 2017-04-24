package spittr.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc //閸氼垳鏁ゅ▔銊ㄐ掓す鍗炲З閻ㄥ墕pringMvc
@ComponentScan(value="spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	
	
	
	
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
		ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
	//濡剝婢樺鏇熸惛
	@Bean
	public SpringTemplateEngine templateEngine(TemplateResolver temlateResolver){
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(temlateResolver);
	    templateEngine.addDialect(new SpringSecurityDialect());
	    return templateEngine;
	}
	//鐟欏棗娴樼憴锝嗙�介崳锟�
	 @Bean
	  public TemplateResolver templateResolver() {
	    TemplateResolver templateResolver = new ServletContextTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/views/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    return templateResolver;
	  }

	
	
	
	
	
	//闁板秶鐤哠pring MVC鐠佲晛鍙炬稉宥咁嚠闂堟瑦锟戒浇绁┃鎰洤html css缁涘绻樼悰灞惧閹搭亷绱濋懓灞炬Ц閻╁瓨甯撮弨鎹愵攽
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
			configurer.enable();
		}
		
		@Override
		public void addViewControllers (ViewControllerRegistry registry){
			
			registry.addViewController("/login").setViewName("login");
			
			
		}
}
