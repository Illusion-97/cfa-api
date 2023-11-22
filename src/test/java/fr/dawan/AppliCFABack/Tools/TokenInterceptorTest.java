package fr.dawan.AppliCFABack.Tools;

import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.interceptors.TokenInterceptor;
import fr.dawan.AppliCFABack.interceptors.TokenSaver;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.JwtTokenUtil;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TokenInterceptorTest {

    @Autowired
    TokenInterceptor tokenInterceptor;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @MockBean
    UtilisateurRepository utilisateurRepository;
    @Autowired
    DtoMapper mapper;
    MockHttpServletRequest request = new MockHttpServletRequest();
    private static String email = "test@test.fr";
    private static String token;
    private static String malformedToken = "malformed.jwt.token.with.too.many.parts";
    @BeforeEach
    public void init() {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("user_id", 1);
        token = jwtTokenUtil.doGenerateToken(claims, email);
        TokenSaver.tokensByEmail.put(email, token);
    }
    @AfterEach
    public void cleanup(){
        TokenSaver.getTokensbyemail().remove(email);
    }
    @Test
    public void testPreHandleWithValidToken() throws Exception {
        HttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();

        request.setMethod("GET");
        request.setRequestURI("/random-URI");
        request.addHeader("Authorization", "Bearer " + token);

        boolean result = tokenInterceptor.preHandle(request,response,handler);

        assertTrue(result);
    }
    @Test
    public void testPreHandleWithInvalidTokenFormation() {
        HttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();

        request.setMethod("GET");
        request.setRequestURI("/random-URI");
        request.addHeader("Authorization", "Bearer " + malformedToken);

        Exception exception = assertThrows(MalformedJwtException.class, () -> {
            tokenInterceptor.preHandle(request, response, handler);
        });

        String expectedMessage = "JWT strings must contain exactly 2 period characters";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
