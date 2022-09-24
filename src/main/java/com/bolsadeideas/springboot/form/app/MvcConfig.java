package com.bolsadeideas.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bolsadeideas.springboot.form.app.interceptors.TiempoTranscurridoInterceptor;

//Clase para añadir interceptores que implemente V-676
@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Autowired	//Inyecto por interfaz para que sea mas generico
	@Qualifier("tiempoTranscurridoInterceptor")
	private HandlerInterceptor tiempoTranscurridoInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//Si lo dejo asi se aplica para TODAS las vistas(Métodos Handlers)
		//registry.addInterceptor(tiempoTranscurridoInterceptor);
		
		//De esta manera se ejecuta el interceptor en una ruta especifica o rutas especificas
		registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**");
	}
	
	

}
