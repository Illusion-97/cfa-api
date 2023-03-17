package fr.dawan.AppliCFABack.interceptors;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import fr.dawan.AppliCFABack.tools.JwtTokenUtil;


@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static Logger logger = Logger.getGlobal();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (!request.getMethod().equals("OPTIONS"))
			if (!request.getRequestURI().equals("/authenticate")
					&& !request.getRequestURI().equals("/")
					&& !request.getRequestURI().equals("/error")
					&& !request.getRequestURI().equals("/forgot")
					&& !request.getRequestURI().equals("/reset-password")) {
				String headerAuth = request.getHeader("Authorization");
				logger.info(">>>>>> inside Token Interceptor...");
				logger.info("URI =" + request.getRequestURI());
				logger.info("Header (authorization) :" + request.getHeader("Authorization"));
				if (headerAuth == null || headerAuth.trim().equals("") || headerAuth.length() < 7) {
					throw new TokenException("Erreur : jeton absent ou invalide !");
				}

				String token = headerAuth.substring(7);
				// validation le token et extraire les infos
				if (jwtTokenUtil.isTokenExpired(token))
					throw new TokenException("Erreur : jeton expiré !");

				String email = jwtTokenUtil.getUsernameFromToken(token);
				if (!TokenSaver.tokensByEmail.containsKey(email) || !TokenSaver.tokensByEmail.get(email).equals(token))
					throw new TokenException("Erreur : jeton non reconnu !");

				// TODO autres extractions du jeton ou autres traitements

			}
		return true;
	}

}

