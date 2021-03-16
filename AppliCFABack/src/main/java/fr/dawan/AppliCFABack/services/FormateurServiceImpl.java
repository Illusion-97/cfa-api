package fr.dawan.AppliCFABack.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.FormateurDto;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService{

	@Override
	public List<FormateurDto> getAllContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormateurDto> getAllContacts(int page, int max) {
		// TODO Auto-generated method stub
		return null;
	}

}
