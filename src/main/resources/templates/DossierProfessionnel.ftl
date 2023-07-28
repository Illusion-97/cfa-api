<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="${backendUrl}css/dossierPro.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>Document ${dossierPro.nom}</title>
</head>
<body>
<#---------------------------------------------------PAGE 1--------------------------------------------------------->
    <div class="divTitlePage1">
 <img class=" frenchRepublic" src="${backendUrl}/pictures/frenchRepublic.jpg" alt="image république française">
    <div class="greyBarsAndH1">
        <div class="greyBarTop"></div>
        <h1>DOSSIER PROFESSIONNEL (DP)</h1>
        <div class="greyBarBottom"></div>
        <div class="pinkLine"></div>
    </div>
    </div>                                                     
        <div class="identityDiv">
    <ul class="identityUl">
        <li>
            Nom de naissance
        </li>
        <li>
            Nom d'usage
        </li>
        <li>
            Prénom
        </li>
        <li>
            Adresse
        </li>
    </ul>
    <ul class="iconsList">
        <li><i class="fa-solid fa-play fa-2xs"></i><span class="spanData">${et.utilisateur.nom}</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><span class="spanData">${et.utilisateur.prenom}</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><span
                    class="spanData">${et.utilisateur.adresse.libelle} ${et.utilisateur.adresse.codePostal} ${et.utilisateur.adresse.ville}</span>
        </li>
    </ul>
</div>

<div class="divTitrePro">
    <div class="titlePink">
        <h2 class="h2TitrePro">Titre professionnel visé</h2>
    </div>
    <div class="pinkDiv1"></div>
    <div class="pinkDiv2"></div>
</div>

<fieldset class="divModalite">
    <h3>MODALITE D'ACCES :</h3>
    <ul>
        <li>
            <input type="checkbox">
            <label for="">Validation des Acquis de l'Expérience (VAE)</label>
        </li>
        <li>
            <input type="checkbox" checked>
            <label for="">Parcours de formation</label>
        </li>
    </ul>
</fieldset>
<#---------------------------------------------------PAGE 2--------------------------------------------------------->
<div class="divTitlePage2">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>

<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro">Présentation du dossier</h2>
    </div>
    <div class="pinkDiv1"></div>
</div>

<div class="divPresentationText">
    <div>
        <p>Le dossier professionnel (DP) constitue un élément du système de validation du titre<br> professionnel.<span
                    class="spanBold"> Ce titre est délivré par le Ministère chargé de l'emploi.</span></p>
        <p>Le DP appartient au candidat. Il le conserve, l'actualise durant son parcours et le présente <span
                    class="spanBold">obligatoirement à chaque session d'examen.</span></p>
        <p>Pour rédiger le DP, le candidat peut être aidé par un formateur ou par un accompagnateur VAE.</p>
        <p>Il est consulté par le jury au moment de la session d'examen.</p>
    </div>
    <div>
        <h3>Pour prendre sa décision, le jury dispose :</h3>
        <ol>
            <li>
                <span class="spanLi">
                    des résultats de la mise en situation professionnelle complétés, éventuellement, du questionnaire<br> <span
                            class="spanPro">professionnel ou de l'entretien technique ou du questionnement à partir de productions.</span>
                </span>
            </li>
            <li>
                <span class="spanLi">
                    du <span class="spanBold">Dossier Professionnel</span> (DP) dans lequel le candidat a consigné les preuves de sa pratique professionnelle.
                </span>
            </li>
            <li>
                <span class="spanLi">
                    des résultats des évaluations passées en cours de formation lorsque le candidat évalué est issu d'un<br> <span
                            class="spanParcours">parcours de formation.
                </span>
            </li>
            <li>
                <span class="spanLi">
                    de l’entretien final (dans le cadre de la session titre).
                </span>
            </li>
            <p class="paragraphItalic">
                [Arrêté du 22 décembre 2015, relatif aux conditions de délivrance des titres professionnels<br> <span
                        class="spanItalic">du ministère chargé de l’Emploi]</span>
            </p>
        </ol>
    </div>
    <div>
        <h3>Ce dossier comporte :</h3>
        <ul>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> pour chaque activité-type du titre visé, un à trois exemples de
                pratique professionnelle ;
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> un tableau à renseigner si le candidat souhaite porter à la
                connaissance du jury la détention d’un titre, d’un <span class="spanDiplome">diplôme, d’un certificat de qualification professionnelle (CQP) ou des attestations de formation ;</span>
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> une déclaration sur l’honneur à compléter et à signer ;
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> des documents illustrant la pratique professionnelle du candidat
                (facultatif)
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> des annexes, si nécessaire.
            </li>
        </ul>
    </div>
</div>
<div class="divCompleter">
    Pour compléter ce dossier, le candidat dispose d'un site web en accès libre sur le site.
</div>
<h3 class="h3Url"><img class="triangleRose" src="${backendUrl}/pictures/triangle-rose.png" alt="triangle rose">
    http://travail-emploi.gouv.fr/titres-professionnels</h3>
    
<#---------------------------------------------------------PAGE 2-------------------------------------------------->
<div class="divTitlePage2">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>

<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro">Présentation du dossier</h2>
    </div>
    <div class="pinkDiv1"></div>
</div>

<div class="divPresentationText">
    <div>
        <p>Le dossier professionnel (DP) constitue un élément du système de validation du titre<br> professionnel.<span
                    class="spanBold"> Ce titre est délivré par le Ministère chargé de l'emploi.</span></p>
        <p>Le DP appartient au candidat. Il le conserve, l'actualise durant son parcours et le présente <span
                    class="spanBold">obligatoirement à chaque session d'examen.</span></p>
        <p>Pour rédiger le DP, le candidat peut être aidé par un formateur ou par un accompagnateur VAE.</p>
        <p>Il est consulté par le jury au moment de la session d'examen.</p>
    </div>
    <div>
        <h3>Pour prendre sa décision, le jury dispose :</h3>
        <ol>
            <li>
                <span class="spanLi">
                    des résultats de la mise en situation professionnelle complétés, éventuellement, du questionnaire<br> <span
                            class="spanPro">professionnel ou de l'entretien technique ou du questionnement à partir de productions.</span>
                </span>
            </li>
            <li>
                <span class="spanLi">
                    du <span class="spanBold">Dossier Professionnel</span> (DP) dans lequel le candidat a consigné les preuves de sa pratique professionnelle.
                </span>
            </li>
            <li>
                <span class="spanLi">
                    des résultats des évaluations passées en cours de formation lorsque le candidat évalué est issu d'un<br> <span
                            class="spanParcours">parcours de formation.
                </span>
            </li>
            <li>
                <span class="spanLi">
                    de l’entretien final (dans le cadre de la session titre).
                </span>
            </li>
            <p class="paragraphItalic">
                [Arrêté du 22 décembre 2015, relatif aux conditions de délivrance des titres professionnels<br> <span
                        class="spanItalic">du ministère chargé de l’Emploi]</span>
            </p>
        </ol>
    </div>
    <div>
        <h3>Ce dossier comporte :</h3>
        <ul>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> pour chaque activité-type du titre visé, un à trois exemples de
                pratique professionnelle ;
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> un tableau à renseigner si le candidat souhaite porter à la
                connaissance du jury la détention d’un titre, d’un <span class="spanDiplome">diplôme, d’un certificat de qualification professionnelle (CQP) ou des attestations de formation ;</span>
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> une déclaration sur l’honneur à compléter et à signer ;
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> des documents illustrant la pratique professionnelle du candidat
                (facultatif)
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> des annexes, si nécessaire.
            </li>
        </ul>
    </div>
</div>
<div class="divCompleter">
    Pour compléter ce dossier, le candidat dispose d'un site web en accès libre sur le site.
</div>
<h3 class="h3Url"><img class="triangleRose" src="${backendUrl}/pictures/triangle-rose.png" alt="triangle rose">
    http://travail-emploi.gouv.fr/titres-professionnels</h3>
<#---------------------------------------------Page3-------------------------------------->
<div class="divTitlePage3">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divSommaire">
    <h2>
        Sommaire
    </h2>
</div>
<h3 class="exemplePratique">
    Exemples de pratique professionnelle
</h3>
<div class="divIntitule">
    <h4>
        <div class="spanSommaireTitle">Intitulé de l’activité-type n° 1</div>
        <span class="spanPageBoldAt1">p. 5</span></h4>
</div>
 <span> Intitulé de l’exemple n° 1 <br/>
 Intitulé de l’exemple n° 2 <br/>
 Intitulé de l’exemple n° 3 
 </span>
<div class="divExemple">
          <div class="spanSommaireTitle">Intitulé de l’activité-type n° 1</div>
        <span class="spanPageBoldAt1">p. 5</span></h4>
        
        <div class="divExemple">
        <ul>
                <li>
                <i class="fa-solid fa-play fa-2xs">
                </i>Intitulé de l'exemple n° 1<span class="spanPage">p. 5</span>
            </li>
        </ul>
</div>
</div>

        <h2 class="h2"><u>Cursus DossierProfessionnel :</u></h2>                                                                 
            <div class="divtitre">
                <p>
                
                   <#if dossierPro.cursusDto[0]?has_content>
                       ${dossierPro.cursusDto.titre}
                    <#else>
                        Cursus non renseignées !
                    </#if>
                    
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </p>
                </p>
            </div>

        <h2 class="h2"><u>ExperienceProfessionnelles :</u></h2>
            <div class="divtitre">
                <p>
                	<#if dossierPro.experienceProfessionnelleDtos[0]?has_content>
					  <#list dossierPro.experienceProfessionnelleDtos as exp>
					  <#if exp.collaborateur??>
                        ${exp.collaborateur}
                         </#if>
                         <#if exp.contexte??>
                        ${exp.contexte}
                         </#if>
                         <#if exp.information??>
                        ${exp.information}
                         </#if>
                         <#if exp.moyenUtilise??>
                        ${exp.moyenUtilise}
                         </#if>
                         <#if exp.tacheRealisee??>
                        ${exp.tacheRealisee}
                         </#if>
                        </#list>
						<#else>
						  experienceProfessionnelles non renseignées !
					</#if>
                    
                    <p>
                        Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
                        The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.
                        On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.
                    </p>
                </p>                    
            </div>

      

        <h2 class="h2"><u>Annexe DossierProfessionnel :</u></h2>                         
            <div class="divtitre">
                <p>
                    <h3>Liste des Annexes : </h3>
                    <ul>
                    
                    	<#if dossierPro.annexeDtos[0]?has_content>
                    	    <#list dossierPro.annexeDtos as annexe>
                    	    <#if annexe.libelleAnnexe??>
                                ${annexe.libelleAnnexe}
                                </#if>
                                <#if annexe.pieceJointe??>
                                ${annexe.pieceJointe}
                                </#if>
                                   </#list>
							<#else>
							  Informations non renseignées !
						</#if>
                    </ul>
                </p>
            </div>     
            
              <h2 class="h2"><u>Facultatif DossierProfessionnel :</u></h2>                         
            <div class="divtitre">
                <p>
                    <h3>Facultatif : </h3>
                    <ul>
                    	<#if dossierPro.facultatifDto?has_content>
                    	   <#list dossierPro.facultatifDto as f>
                    	    <#if f.intitule??>
                                ${f.intitule}
                                </#if>
                                <#if f.organisme??>
                                ${f.organisme}
                                </#if>
                                <#if f.date??>
                                ${f.date}
                                </#if>
                                   </#list>
							<#else>
							  Informations non renseignées !
						</#if>
                    </ul>
                </p>
            </div>      
             
            
    </div>

</body>
</html>