<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>Dossier Professionnel</title>
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
        <li><i class="fa-solid fa-play fa-2xs"></i><span class="spanData">${et.utilisateur.adresse.libelle} ${et.utilisateur.adresse.codePostal} ${et.utilisateur.adresse.ville}</span></li>
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
        <p>Le dossier professionnel (DP) constitue un élément du système de validation du titre<br> professionnel.<span class="spanBold"> Ce titre est délivré par le Ministère chargé de l'emploi.</span></p>
        <p>Le DP appartient au candidat. Il le conserve, l'actualise durant son parcours et le présente <span class="spanBold">obligatoirement à chaque session d'examen.</span></p>
        <p>Pour rédiger le DP, le candidat peut être aidé par un formateur ou par un accompagnateur VAE.</p>
        <p>Il est consulté par le jury au moment de la session d'examen.</p>
    </div>
    <div>
        <h3>Pour prendre sa décision, le jury dispose :</h3>
        <ol>
            <li>
                <span class="spanLi">
                    des résultats de la mise en situation professionnelle complétés, éventuellement, du questionnaire<br> <span class="spanPro">professionnel ou de l'entretien technique ou du questionnement à partir de productions.</span>
                </span>
            </li>
            <li>
                <span class="spanLi">
                    du <span class="spanBold">Dossier Professionnel</span> (DP) dans lequel le candidat a consigné les preuves de sa pratique professionnelle.
                </span>
            </li>
            <li>
                <span class="spanLi">
                    des résultats des évaluations passées en cours de formation lorsque le candidat évalué est issu d'un<br> <span class="spanParcours">parcours de formation.
                </span>
            </li>
            <li>
                <span class="spanLi">
                    de l’entretien final (dans le cadre de la session titre).
                </span>
            </li>
            <p class="paragraphItalic">
                [Arrêté du 22 décembre 2015, relatif aux conditions de délivrance des titres professionnels<br> <span class="spanItalic">du ministère chargé de l’Emploi]</span>
            </p>
        </ol>
    </div>
    <div>
        <h3>Ce dossier comporte :</h3>
        <ul>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> pour chaque activité-type du titre visé, un à trois exemples de pratique professionnelle ;
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> un tableau à renseigner si le candidat souhaite porter à la connaissance du jury la détention d’un titre, d’un <span class="spanDiplome">diplôme, d’un certificat de qualification professionnelle (CQP) ou des attestations de formation ;</span>
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> une déclaration sur l’honneur à compléter et à signer ;
            </li>
            <li>
                <i class="fa-solid fa-play fa-2xs"></i> des documents illustrant la pratique professionnelle du candidat (facultatif)
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
    <h4><div class="spanSommaireTitle"><#if at[0]??>${at[0].libelle}<#else>Intitulé de l'activité type 1</#if></div><span class="spanPageBoldAt1">p. 5</span></h4>
</div>
<div class="divExemple">
    <ul>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[0]??>${cp[0].libelle}<#else>Intitulé de l'exemple n° 1</#if><span class="spanPage">p. 5</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[1]??>${cp[1].libelle}<#else>Intitulé de l'exemple n° 2</#if><span class="spanPage">p. 7</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[2]??>${cp[2].libelle}<#else>Intitulé de l'exemple n° 3</#if><span class="spanPage">p. 9</span></li>
    </ul>
</div>
<div class="divIntitule">
    <h4><div class="spanSommaireTitle"><#if at[1]??>${at[1].libelle}<#else>Intitulé de l'activité type 2</#if></div><span class="spanPageBoldAt2">p. 11</span></h4>
</div>
<div class="divExemple">
    <ul>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[3]??>${cp[3].libelle}<#else>Intitulé de l'exemple n° 4</#if><span class="spanPage">p. 11</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[4]??>${cp[4].libelle}<#else>Intitulé de l'exemple n° 5</#if><span class="spanPage">p. 13</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[5]??>${cp[5].libelle}<#else>Intitulé de l'exemple n° 6</#if><span class="spanPage">p. 15</span></li>
    </ul>
</div>
<div class="divIntitule">
    <h4><div class="spanSommaireTitle"><#if at[2]??>${at[2].libelle}<#else>Intitulé de l'activité type 3</#if></div><span class="spanPageBoldAt3">p. 17</span></h4>
</div>
<div class="divExemple">
    <ul>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[6]??>${cp[6].libelle}<#else>Intitulé de l'exemple n° 7</#if><span class="spanPage">p. 17</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[7]??>${cp[7].libelle}<#else>Intitulé de l'exemple n° 8</#if><span class="spanPage">p. 19</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[8]??>${cp[8].libelle}<#else>Intitulé de l'exemple n° 9</#if><span class="spanPage">p. 21</span></li>
    </ul>
</div>
<div class="divIntitule">
    <h4><div class="spanSommaireTitle"><#if at[3]??>${at[3].libelle}<#else>Intitulé de l'activité type 4</#if></div><span class="spanPageBoldAt4">p. 23</span></h4>
</div>
<div class="divExemple">
    <ul>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[9]??>${cp[9].libelle}<#else>Intitulé de l'exemple n° 10</#if><span class="spanPage">p. 23</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[10]??>${cp[10].libelle}<#else>Intitulé de l'exemple n° 11</#if><span class="spanPage">p. 25</span></li>
        <li><i class="fa-solid fa-play fa-2xs"></i><#if cp[11]??>${cp[11].libelle}<#else>Intitulé de l'exemple n° 12</#if><span class="spanPage">p. 27</span></li>
    </ul>
</div>
<div class="divAnnexes">
    <h4>
        Titres, diplômes, CQP, attestations de formation <span class="spanFacultatif">(facultatif)</span><span class="spanPageBold2">p. 28</span>
    </h4>
    <h4 class="h4NoMargin">Déclaration sur l'honneur<span class="spanPageBold2">p. 29</span></h4>
    <h4 class="h4NoMargin">
        Documents illustrant la pratique professionnelles <span class="spanFacultatif">(facultatif)</span><span class="spanPageBold2">p. 30</span>
    </h4>
    <h4 class="h4NoMargin">Annexes <span class="spanFacultatif">(Si le RC le prévoit)<span class="spanPageBold2">p. 31</span></span>
    </h4>
</div>
<#-------------------------------------------------------Page4--------------------------------->
<div class="divTitlePage3">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<h1 class="titleExemples">EXEMPLES DE PRATIQUE PROFESSIONNELLE</h1>
<#------------------------------------------------------Page5--------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 1</h2>
    <span class="spanActivitetitle"><#if at[0]??>${at[0].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°1 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[0]??>${cp[0].libelle}</#if></span>
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

    </div>
</div>
<#---------------------------------------------Page 6------------------------------------------------>
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 7------------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 1</h2>
    <span class="spanActivitetitle"><#if at[0]??>${at[0].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°2 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[1]??>${cp[1].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 8------------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 9------------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 1</h2>
    <span class="spanActivitetitle"><#if at[0]??>${at[0].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°3 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[2]??>${cp[2].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 10------------------------------------------------>
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 11------------------------------------------------>
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 2</h2>
    <span class="spanActivitetitle"><#if at[1]??>${at[1].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°1 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[3]??>${cp[3].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 12------------------------------------------------>
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 13------------------------------------------------>
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 2</h2>
    <span class="spanActivitetitle"><#if at[1]??>${at[1].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°2 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[4]??>${cp[4].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 14------------------------------------------------>
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 15------------------------------------------------>
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 2</h2>
    <span class="spanActivitetitle"><#if at[1]??>${at[1].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°3 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[5]??>${cp[5].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 16------------------------------------------------>
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 17----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 3</h2>
    <span class="spanActivitetitle"><#if at[2]??>${at[2].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°1 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[6]??>${cp[6].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 18----------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 19----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 3</h2>
    <span class="spanActivitetitle"><#if at[2]??>${at[2].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°2 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[7]??>${cp[7].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 20----------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 21----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 3</h2>
    <span class="spanActivitetitle"><#if at[2]??>${at[2].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°3 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[8]??>${cp[8].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 22----------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 23----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 4</h2>
    <span class="spanActivitetitle"><#if at[3]??>${at[3].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°1 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[9]??>${cp[9].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 24----------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 25----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 4</h2>
    <span class="spanActivitetitle"><#if at[3]??>${at[3].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°2 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[10]??>${cp[10].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 26----------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 27----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divActiviteExemple">
    <h2 class="h2Activite">Activité-type 4</h2>
    <span class="spanActivitetitle"><#if at[3]??>${at[3].libelle}</#if></span>
    <span class="spanExempleNumero">Exemple n°3 <span class="iconExemple"><i class="icon fa-solid fa-play fa-2xs"></i></span><#if cp[11]??>${cp[11].libelle}</#if></span>
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

    </div>
</div>
<#--------------------------------------------Page 28----------------------------------------------->
<div class="divTitlePage5">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            2. Précisez les moyens utilisés :
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            3. Avec qui avez-vous travaillé ?
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription2">
    <div>
        <div class="exempleDescription2">
            4. Contexte
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<div class="divExempleDescription3">
    <div>
        <div class="exempleDescription2">
            5. Informations complémentaires (facultatif)
        </div>
    </div>
    <div class="exempleContent2">

    </div>
</div>
<#--------------------------------------------Page 29----------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
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
    <tbody>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</table>
<#-----------------------------------------Page 30------------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro">Déclaration sur l'honneur</h2>
    </div>
    <div class="pinkDiv1"></div>
</div>
<p class="paragrapheDeclaration">
    Je soussigné(e), <br><br>
    déclare sur l'honneur que les renseignements fournis dans ce dossier sont exacts et que je <br><br>
    suis l'auteur(e) des réalisations jointes. <br><br><br><br><br><br>
    Fait à le <br><br>
    pour faire valoir ce que de droit. <br><br><br><br>
    Signature :
</p>
<#-------------------------------------------Page 31---------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
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
    <tbody>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td></td>
    </tr>
    </tbody>
</table>
<#----------------------------------------Page 32---------------------------------------------->
<div class="divTitlePage4">
    <div class="greyBarsAndH1">
        <div class="greyBarTop2"></div>
        <h2>DOSSIER PROFESSIONNEL (DP)</h2>
        <div class="greyBarBottom2"></div>
        <div class="pinkLine"></div>
    </div>
</div>
<div class="divTitrePro2">
    <div class="titlePink">
        <h2 class="h2TitrePro2">ANNEXES</h2>
    </div>
    <div class="pinkDiv1"></div>
    <div class="facultatif2">(Si le RC le prévoit)</div>
</div>
</body>
</html>
