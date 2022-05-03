package fr.dawan.AppliCFABack.interceptors;

@SuppressWarnings("serial")
public class TokenException extends Exception {
	public TokenException(String message) {
		super(message);
	}
}
