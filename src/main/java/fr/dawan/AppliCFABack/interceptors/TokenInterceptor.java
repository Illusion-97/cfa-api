package fr.dawan.AppliCFABack.interceptors;

import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static Logger logger = Logger.getGlobal();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!request.getMethod().equals("OPTIONS")) {
			if (!request.getRequestURI().equals("/authenticate")
					&& !request.getRequestURI().equals("/")
					&& !request.getRequestURI().equals("/error")
					&& !request.getRequestURI().equals("/forgot")
					&& !request.getRequestURI().equals("/utilisateurs/tuteur")
					&& !request.getRequestURI().equals("/reset-password")) {
				String headerAuth = request.getHeader("Authorization");
				logger.info(">>>>>> inside Token Interceptor...");
				logger.info("URI =" + request.getRequestURI());
				logger.info("Header (authorization) :" + request.getHeader("Authorization"));

				if (headerAuth == null || headerAuth.trim().isEmpty() || !headerAuth.startsWith("Bearer ")) {
					throw new MalformedJwtException("Erreur : jeton absent ou invalide !");
				}

				String token = headerAuth.substring(7);
				try {
					// validation du token
					jwtTokenUtil.isTokenExpired(token);
					String email = jwtTokenUtil.getUsernameFromToken(token);
					if (!TokenSaver.tokensByEmail.containsKey(email) || !TokenSaver.tokensByEmail.get(email).equals(token)) {
						throw new MalformedJwtException("Erreur : jeton non reconnu !");
					}

					// TODO: Autres extractions du jeton ou autres traitements

				} catch (MalformedJwtException e) {
					throw new MalformedJwtException("Erreur : jeton malformé !");
				}
			}
		}
		return true;
	}
}

