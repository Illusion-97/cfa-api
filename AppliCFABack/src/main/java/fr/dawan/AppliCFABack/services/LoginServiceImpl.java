package fr.dawan.AppliCFABack.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.LoginDto;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{

	@Override
	public LoginDto authenticate(LoginDto lDto) {	
		return lDto;
	}

}
