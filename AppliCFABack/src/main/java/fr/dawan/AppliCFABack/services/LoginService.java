package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.LoginDto;

public interface LoginService {

	LoginDto authenticate(LoginDto lDto);

}
