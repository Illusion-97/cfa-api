package fr.dawan.AppliCFABack.interceptors;

import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private static final Logger logger = Logger.getGlobal();

	private final List<String> PUBLIC_URIS = Arrays.asList(
			"/authenticate",
			"/",
			"/error",
			"/forgot",
			"/utilisateurs/tuteur",
			"/reset-password",
			"/subscribe"
	);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		if(this.isPublicUri(request.getRequestURI())) {
			return true;
		}
		String headerAuth = request.getHeader("Authorization");
		logger.info(">>>>>> inside Token Interceptor...");
		logger.info("URI =" + request.getRequestURI());
		logger.info("Header (authorization) :" + headerAuth);

		if (headerAuth == null || headerAuth.trim().isEmpty() || !headerAuth.startsWith("Bearer ")) {
			throw new MalformedJwtException("Erreur : jeton absent ou invalide !");
		}

		String token = headerAuth.substring(7);
		try {
			if(jwtTokenUtil.isTokenExpired(token)) {
				throw new MalformedJwtException("Erreur : Token expired");
			}
			// validation du token
					/*String email = jwtTokenUtil.getUsernameFromToken(token);
					if (!TokenSaver.tokensByEmail.containsKey(email) || !TokenSaver.tokensByEmail.get(email).equals(token)) {
						throw new MalformedJwtException("Erreur : jeton non reconnu !");
					}*/
			// TODO: Autres extractions du jeton ou autres traitements

		} catch (MalformedJwtException e) {
			throw new MalformedJwtException("Erreur : jeton malformé !");
		}


		return true;
	}

	private boolean isPublicUri(String uri) {
		return this.PUBLIC_URIS.contains(uri);
	}
}

