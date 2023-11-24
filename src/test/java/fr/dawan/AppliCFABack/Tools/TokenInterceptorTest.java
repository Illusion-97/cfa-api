package fr.dawan.AppliCFABack.Tools;

import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
public class TokenInterceptorTest {

    @InjectMocks
    TokenInterceptor tokenInterceptor;
    private static String token = "simulation.token.meow";
    private static String malformedToken = "malformed.jwt.token.with.too.many.parts";
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    JwtTokenUtil tokenUtil = Mockito.mock(JwtTokenUtil.class);
    @BeforeEach
    public void init() {
        tokenInterceptor = new TokenInterceptor();
    }
    @AfterEach
    public void cleanup(){
    }
    @Test
    public void testPreHandleWithValidToken() throws Exception {
        Mockito.when(request.getMethod()).thenReturn("OPTIONS");
        Mockito.when(request.getRequestURI()).thenReturn("/random-URI");
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        boolean result = tokenInterceptor.preHandle(request, response,null);

        assertTrue(result);
    }
    @Test
    public void testPreHandleWithInvalidHeaderAuthorization() {
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getRequestURI()).thenReturn("/random-URI");
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bear " + token);

        Exception exception = assertThrows(MalformedJwtException.class, () -> {
            tokenInterceptor.preHandle(request, response, null);
        });

        String expectedMessage = "Erreur : jeton absent ou invalide !";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
