package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//Si tengo metodos handler GET Y POST con la misma ruta puedo ignorar la que yo quiera y aplicarlo a una en especifico
		if( request.getMethod().equalsIgnoreCase("post") ) { //Si es POST se omite
			return true;
		}
		
		if( handler instanceof HandlerMethod ) {
			HandlerMethod method = (HandlerMethod) handler;
			LOGGER.info("Es un metodo del controlador: " + method.getMethod().getName());
		}
	
		LOGGER.info("TiempoTranscurridoInterceptor: metodo preHandle... ");
		LOGGER.info("*** Intercepte a - " + handler);
		long timeInit = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", timeInit);
		
		Random random = new Random();
		Integer delay = random.nextInt(500);
		Thread.sleep(delay);
		
		return true;	//Cuando es true continua el proceso normal de la aplicación, con false termina la ejecución del controlador
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//Si tengo metodos handler GET Y POST con la misma ruta puedo ignorar la que yo quiera y aplicarlo a una en especifico
		if( request.getMethod().equalsIgnoreCase("post") ) { //Si es POST se omite
			return;
		}
		
		long timeEnd = System.currentTimeMillis();
		long timeInit = (Long) request.getAttribute("tiempoInicio");
		long timeElapsed = timeEnd - timeInit;
		
		//Validacion importante, sin ella puede tomar los recursos de JS o CSS
		if( modelAndView != null ) {
			modelAndView.addObject("tiempoTranscurrido", timeElapsed);	//Valor que paso a la vista
		}
		
		LOGGER.info("Tiempo transcurrido " + timeElapsed + " milisegundos");
		LOGGER.info("TiempoTranscurridoInterceptor: metodo postHandle... ");
		
	}

	
	
}
