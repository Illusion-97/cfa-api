package fr.dawan.AppliCFABack.interceptors;

import java.util.HashMap;
import java.util.Map;


public class TokenSaver {
	// email/token
		public static Map<String, String> tokensByEmail;

		static {
			tokensByEmail = new HashMap<String, String>();
		}
		
		public static Map<String, String> getTokensbyemail() {
			return tokensByEmail;
		}
}
