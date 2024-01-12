package fr.dawan.AppliCFABack.crons;

import fr.dawan.AppliCFABack.dto.SoutenanceDto;
import fr.dawan.AppliCFABack.entities.Soutenance;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.services.SoutenanceMailService;
import fr.dawan.AppliCFABack.services.SoutenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvocationMailSender {

    SoutenanceMailService soutenanceMailService;
    SoutenanceService soutenanceService;

    @Autowired
    private DtoMapper mapper;
    public ConvocationMailSender(@Autowired SoutenanceMailService soutenanceMailService,
                                 @Autowired SoutenanceService soutenanceService) {
        this.soutenanceMailService = soutenanceMailService;
        this.soutenanceService = soutenanceService;
    }
    private static Logger LOGGER = LoggerFactory.getLogger(ConvocationMailSender.class);

    @Scheduled(cron = "0 45 15 * * *") //chaque jour à 12h du matin
    @Async("myTasksExecutor")
    public void importOffers() {
        LOGGER.info("Check for sending convocation");
        List<Soutenance> soutenances = soutenanceService.getSoutenanceWhereMailNotSent()
                .stream()
                .filter(this::shouldSendConvocation)
                .collect(Collectors.toList());
        LOGGER.info(soutenances.size() + " convocations to send");
        soutenances.forEach(soutenance -> {
            SoutenanceDto soutenanceDto = mapper.soutenanceToSoutenanceDto(soutenance);
            soutenanceMailService.sendMail(soutenanceDto);
            // TODO check if property is set when an error occure with the mail service
            this.soutenanceService.markSoutenanceMailSentAsTrue(soutenance);
        });
    }

    private boolean shouldSendConvocation(Soutenance soutenance) {
        Date dateFromNextMonth = new Date();
        dateFromNextMonth.setMonth(dateFromNextMonth.getMonth() + 1);
        /*LOGGER.info(soutenance.getEtudiant().getUtilisateur().getFullName());
        LOGGER.info("\tDate from next month : " + dateFromNextMonth);
        LOGGER.info("\tSoutenance date : " + soutenance.getExamDate());
        LOGGER.info("\t" + (soutenance.getExamDate().getTime() - dateFromNextMonth.getTime()));
        LOGGER.info("\tShould send convocation : " + (soutenance.getExamDate().getTime() <= dateFromNextMonth.getTime()));*/
        return soutenance.getExamDate().getTime() <= dateFromNextMonth.getTime();
    }
}

