package fr.dawan.AppliCFABack.services;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.PromotionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Conge;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
    JavaMailSender javaMailSender;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Override
	public void alertDemandeCongetoReferent(Conge c) {		
		//On détermine le référent de l'étudiant concerné
		List<PromotionDto> promotions = etudiantService.getPromotionsByIdEtudiant(c.getUtilisateur().getId());
		UtilisateurDto referentPedagogique = null;
		PromotionDto pSelected = null;
		for(PromotionDto p : promotions) {
			//On sélectionne la promotion courante en fonction de la date,
			//Cas particulier d'un changement de promotion en cours de formation
			//=> les deux promotions sont courantes => on choisit la dernière promotion de la liste (à priori ranger par id)
			//A voir pour trouver une meilleur solution ?
			if( ( p.getDateDebut().isBefore(LocalDate.now()) && p.getDateFin().isAfter(LocalDate.now()) )
					|| p.getDateDebut().isEqual(LocalDate.now()) 
					|| p.getDateFin().isEqual(LocalDate.now())) {
				referentPedagogique = p.getReferentPedagogiqueDto();
				pSelected = p;
			}
		}
		
		SimpleMailMessage msg = new SimpleMailMessage();
		
//		msg.setTo(referentPedagogique.getLogin());
        msg.setTo("cfaDawan@gmail.com");	//Pour tests

        msg.setSubject("noreply - Demande de Congés");
        	        
        StringBuilder str = new StringBuilder("L'étudiant ");
        str.append(c.getUtilisateur().getPrenom());
        str.append(" ");
        str.append(c.getUtilisateur().getNom());
        str.append(" de la promotion : ");
        str.append(pSelected.getNom());
        str.append(" a fais une demande de congé du ");
        str.append(c.getDateDebut());
        str.append(" au ");
        str.append(c.getDateFin());
        str.append(".\n");
        str.append("Veuillez mettre à jours le status de cette demande sur le Portail CFA Dawan.");
        
        
        msg.setText(str.toString());

        javaMailSender.send(msg);
		
	}

}
