<!DOCTYPE html>
<html lang="en">
<head>
 <style>
footer {
  text-align: right;
  padding: 5px;
  color: #A9A9A9;
}
.page-footer {
text-align: left;
  padding: 5px;
  color: #A9A9A9;
}
.centrage {text-align: center;
margin-right:20%;}
  </style>
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
    <title>Dossier Professionnel</title>
</head>
<body>
<#assign currentPage = 0>
<#---------------------------------------------------PAGE 1--------------------------------------------------------->
<div class="divTitlePage1">
    <img class=" frenchRepublic" src="${backendUrl}/pictures/frenchRepublic.jpg" alt="image république française">
    <div class="greyBarsAndH1">
        <div class="greyBarTop"></div>
        <h1>DOSSIER PROFESSIONNEL (DP)</h1>
        <div class="greyBarBottom"></div>
        <div class="pinkLine"></div>
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
    <div class="centrage">
    <h3 class="spanData">${dp.cursus.titre}</h3>
    </div>
    <div class="pinkDiv2"></div>
</div>

<fieldset class="divModalite">
    <h3>MODALITE D'ACCES :</h3>
    <ul>
        <li>
            <input type="checkbox" id="pf" name="pf" checked>
            <label for="pf">Parcours de formation</label>
        </li>
        <li>
            <input type="checkbox" id="vae">
            <label for="vae">Validation des Acquis de l'Expérience (VAE)</label>
        </li>
    </ul>
</fieldset>	
<#assign currentPage = currentPage + 1>
<div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
        </div>
<#---------------------------------------------------------PAGE 2-------------------------------------------------->
<div class="divTitlePage2">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>

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
    <div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#---------------------------------------------Page3-------------------------------------->
<div class="divTitlePage3">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>
<div class="divSommaire">
    <h2>
        Sommaire
    </h2>
</div>
<h3 class="exemplePratique">
    Exemples de pratique professionnelle
</h3>
<#list pdfActiviteDtos as a>
<div class="divIntitule">
    <h4>
        <div class="spanSommaireTitle"><#if a??>${a.libelle}<#else>Intitulé de l'activité type 1</#if></div>
        <span class="spanPageBoldAt1">p. ${currentPage}</span></h4>
</div>
<#list a.pdfCompetenceDtoSet as cp>
<div class="divExemple">
        <ul><#if cp.experienceProfessionnelleDtoList[0]??>
                <li><i class="fa-solid fa-play fa-2xs"></i><#if cp.experienceProfessionnelleDtoList[0]??><span>${cp.libelle}</span><#else>Intitulé de l'exemple n° 1</#if><span class="spanPage">p. ${currentPage}</span>
            </li>
            </#if>
        </ul>
</div>
</#list>
<#assign currentPage = currentPage + 1>
</#list>
<#if pdfActiviteDtos?size = 3>
<div class="divAnnexes3">
    <#elseif pdfActiviteDtos?size = 4>
    <div class="divAnnexes4">
        <#elseif pdfActiviteDtos?size = 2>
        <div class="divAnnexes2">
            <#elseif pdfActiviteDtos?size = 1>
            <div class="divAnnexes1">
        </#if>
    <h4>
        Titres, diplômes, CQP, attestations de formation <span class="spanFacultatif">(facultatif)</span><span
                class="spanPageBold2">p. 29</span>
    </h4>
    <h4 class="h4NoMargin">Déclaration sur l'honneur<span class="spanPageBold2">p. 30</span></h4>
    <h4 class="h4NoMargin">
        Documents illustrant la pratique professionnelles <span class="spanFacultatif">(facultatif)</span><span
                class="spanPageBold2">p. 31</span>
    </h4>
    <h4 class="h4NoMargin">Annexes <span class="spanFacultatif">(Si le RC le prévoit)<span
                    class="spanPageBold2">p. 32</span></span>
    </h4>
</div>
<div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#-------------------------------------------------------Page4--------------------------------->
<div class="divTitlePage3">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>
<h1 class="titleExemples">EXEMPLES DE PRATIQUE PROFESSIONNELLE</h1>
<div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#------------------------------------------------------Page5--------------------------------------->
<#assign currentActiviteType = 0>
<#assign currentActivite = "">
<#assign currentExemple = 0>
<#list pdfActiviteDtos as a>
    <#list a.pdfCompetenceDtoSet as cp>
            <#list cp.experienceProfessionnelleDtoList as exp>
                    <#if exp[0]??>
                     <#if currentActivite != a.libelle>
                    <#if currentActivite != "">
                        </div> <!-- on ferme la div de l'activité précédente -->
                    </#if>
                    <#assign currentActivite = a.libelle>
    <div class="divTitlePage4">
        <div class="greyBarsAndH1">
            <div class="greyBarTop2"></div>
            <h2>DOSSIER PROFESSIONNEL (DP)</h2>
            <div class="greyBarBottom2"></div>
            <div class="pinkLine"></div>
        </div>
    </div>
    <#assign currentPage = currentPage + 1>
    <#assign currentExemple = 0>
     </#if>
    <div class="divActiviteExemple">
        <h2 class="h2Activite">Activité-type ${currentActiviteType + 1} </h2>
        <span class="spanActivitetitle">${a.libelle}</span>
        <span class="spanExempleNumero">Exemple n°${currentExemple + 1}<span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span>${cp.libelle}</span>
        <div class="pinkDiv3"></div>
        <div class="pinkDiv4"></div>
    </div>
    <div class="divExempleDescription">
        <div>
            <div class="exempleDescription">
                1. Décrivez les tâches ou opérations que vous avez effectuées, et dans quelles conditions :
            </div>
        </div>
        <div class="exempleContent">
            <#if exp.tacheRealisee??>
    <div class="divExempleContent">
        ${exp.tacheRealisee}
    </div>
<#else>
    <div class="divExempleContent">
       pad de tacheRealisee.
    </div>
</#if>
        </div>
    </div>
   <div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#---------------------------------------------Page 6------------------------------------------------>
    <div class="divTitlePage5">
        <div class="greyBarsAndH1">
            <div class="greyBarTop2"></div>
            <h2>DOSSIER PROFESSIONNEL (DP)</h2>
            <div class="greyBarBottom2"></div>
            <div class="pinkLine"></div>
        </div>
    </div>
    <#assign currentPage = currentPage + 1>
    <div class="divExempleDescription2">
        <div>
            <div class="exempleDescription2">
                2. Précisez les moyens utilisés :
            </div>
        </div>
        <div class="exempleContent2">
        <#if exp.moyenUtilise??>
    <div class="divExempleContent">
        ${exp.moyenUtilise}
    </div>
<#else>
    <div class="divExempleContent">
        pas de moyenUtilise.
    </div>
</#if>
           
        </div>
    </div>
    <div class="divExempleDescription2">
        <div>
            <div class="exempleDescription2">
                3. Avec qui avez-vous travaillé ?
            </div>
        </div>
        <div class="exempleContent2">
             <#if exp.collaborateur??>
    <div class="divExempleContent">
        ${exp.collaborateur}
    </div>
<#else>
    <div class="divExempleContent">
        pas de collaborateur.
    </div>
</#if>
        </div>
    </div>
    <div class="divExempleDescription2">
        <div>
            <div class="exempleDescription2">
                4. Contexte
            </div>
        </div>
        <div class="exempleContent2">
            <#if exp.contexte??>
    <div class="divExempleContent">
        ${exp.contexte}
    </div>
<#else>
    <div class="divExempleContent">
        pas de contexte.
    </div>
</#if>
        </div>
    </div>
    <div class="divExempleDescription3">
        <div>
            <div class="exempleDescription2">
                5. Informations complémentaires (facultatif)
            </div>
        </div>
        <div class="exempleContent2">
             <#if exp.information??>
    <div class="divExempleContent">
        ${exp.information}
    </div>
<#else>
    <div class="divExempleContent">
       pas  d'information.
    </div>
    
</#if>
        </div>
    </div>
     <#assign currentExemple = currentExemple + 1>
                    </#if>
            </#list>
    </#list>
     <#assign currentActiviteType = currentActiviteType + 1> 
</#list>
<div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#--------------------------------------------Page 29----------------------------------------------->

<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>

<div class="divTitrePro3">
    <div class="titlePink">
        <h2 class="h2TitrePro">Titres,diplômes, CQP, attestations de formation</h2>
    </div>
    <div class="pinkDiv1"></div>
    <div class="facultatif">(facultatif)</div>
</div>
<table class="titreDiplome">
    <thead>
    <tr>
        <th class="thOther">Intitulé</th>
        <th class="thAutorite">Autorité ou organisme</th>
        <th>Date</th>
    </tr>
    </thead>
    <#if exp[0]??>
    <#list exp[0].dossierProfessionnel.facultatifs as f>
    <tbody>
    <tr>
        <td><#if f??>${f.intitule}</#if></td>
        <td><#if f??>${f.organisme}</#if></td>
        <td><#if f??>${f.date}</#if></td>
    </tr>
    </tbody>
    </#list>
       </#if>
</table>
<div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#-----------------------------------------Page 30------------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>
<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro">Déclaration sur l'honneur</h2>
    </div>
    <div class="pinkDiv1"></div>
</div>
<p class="paragrapheDeclaration">
    Je soussigné(e) ${et.utilisateur.nom} ${et.utilisateur.prenom}, <br><br>
    déclare sur l'honneur que les renseignements fournis dans ce dossier sont exacts et que je <br><br>
    suis l'auteur(e) des réalisations jointes. <br><br><br><br><br><br>
    Fait à ${et.utilisateur.adresse.ville}, le ${dateNow} <br><br>
    pour faire valoir ce que de droit. <br><br><br><br>
    <#if signature??>
       Signature :<img src="${signature.pieceJointe}" alt="Signature">
               </#if>
</p>
<div class="page-footer">
            Page ${currentPage}
        </div>
<#-------------------------------------------Page 31---------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>
<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro">Documents illustrant la pratique professionnelle</h2>
    </div>
    <div class="pinkDiv1"></div>
    <div class="facultatif">(facultatif)</div>
</div>
<table class="titreDiplome2">
    <thead>
    <tr>
        <th>Intitulé</th>
    </tr>
    </thead>
    <#if exp[0]??>
    <#list exp[0].dossierProfessionnel.annexes as an>
    <tbody >
    <tr>
        <td>${an.libelleAnnexe}</td>
    </tr>
    </tbody>
    </#list>
       </#if>
</table>
<div class="page-footer">
               { Page ${currentPage}}
            </div>
            <footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>
<#----------------------------------------Page 32---------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<#assign currentPage = currentPage + 1>
<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro2">ANNEXES</h2>
    </div>
    <div class="pinkDiv1"></div>
    <div class="facultatif2">(Si le RC le prévoit)</div>
</div>
<div class="divAnnexesList">
<#if exp[0]??>
    <#list exp[0].dossierProfessionnel.annexes as an>
        <div>

           <img class="annexe" src="${backendUrl}/files/DossierProfessionnel/${an.pieceJointe}" alt="annexe files" width="600">
        </div>
        </#list>
        </#if>
</div>
<div class="page-footer">
    Page ${currentPage}
</div>
<footer>
        <small>DOSSIER PROFESSIONNEL-Version du 11/09/2017</small>
        </footer>

        
</body>
</html>
