package fr.dawan.AppliCFABack.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.SifaDto;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.SifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SifaServiceImpl implements SifaService{
    @Autowired
    SifaRepository sifaRepository;
    @Autowired
    private DtoMapper mapper = new DtoMapperImpl();
    @Value("${app.storagefolder}")
    private String storageFolder;
    ObjectMapper objectMapper = new ObjectMapper();
    private String jsonString;
    private String fileName = "sifa.txt";
    private String headerTabler = "ine_rnie;numero_uai;og;sit_form;uai_eple;nat_str_jur;statut;diplome;dur_form_theo;" +
            "dur_form_reelle;an_form;nom;nom2;prenom1;prenom2;prenom3;adresse;cod_post;com_resid;tel_jeune;" +
            "tel_resp1_perso;tel_resp1_pro;tel_resp2_perso;tel_resp2_pro;mail_jeune;mail_resp1;mail_resp2;date_nais;" +
            "lieu_nais;sexe;regime_sco;pcs;handi;natio;sit_av_app;dip_obt;sit_n_1;etab_n_1;type_emp;date_entree_cfa;" +
            "date_deb_cont;date_rupt_cont;com_etab;naf_etab;nbsal_emp;siret_emp;Etat;";

    @Override
    public SifaDto getById(long id) {
        return mapper.sifaToSifaDto(sifaRepository.findById(id).get());
    }

    @Override
    public List<SifaDto> getAll() {
        return sifaRepository.findAll().stream().map(mapper::sifaToSifaDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        sifaRepository.deleteById(id);
    }

    @Override
    public SifaDto saveOrUpdate(SifaDto sDto) {
        return mapper.sifaToSifaDto(sifaRepository.save(mapper.sifaDtoToSifa(sDto)));
    }

    @Override
    public void generateTabler() {
        try{
            File isFilePresent = new File(storageFolder + fileName);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            if (isFilePresent.exists()){isFilePresent.delete();}
            List<SifaDto> sifaDtoList = sifaRepository.findAll().stream().map(mapper::sifaToSifaDto).collect(Collectors.toList());
            FileWriter sifaFile = new FileWriter(fileName);
            //On met en place le header pour l'import en xlsx
            sifaFile.write(headerTabler + "\n");
            for (SifaDto sifaDto : sifaDtoList){
                jsonString = objectMapper.writeValueAsString(sifaDto).replace(",",";");
                sifaFile.write(jsonString + "\n");
            }
        }catch(Exception e){

        }
    }


}
