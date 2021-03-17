package fr.dawan.AppliCFABack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.services.FormateurService;

@SpringBootTest
public class FormateurTest {
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	private FormateurService formateurService;
	@Autowired
	private ObjectMapper objMapper;

	@Test
	public void testSave() {

	}
}
