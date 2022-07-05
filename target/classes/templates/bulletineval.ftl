<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="static.css" />
</head>
<body>
<img class="logo" src="../templates/../pictures/institutionnel-logo.png" alt="Logo Dawan">
Bulletin d’évaluation (fixe)

${titrePro}
<br />
<div class="redColor">
    <p class="redColor">Nom : ${etudiant.utilisateur.nom}</p>
    <br />
    Prénom : ${etudiant.utilisateur.prenom}
    <br />
    Année d'étude : ${promoAnnee}
</div>


<br />Filière : Informatique
<br />
Pied de page
<br />
pageBreak
<br />
Logo Dawan
<br />
<h2 class="red">CONTROLE CONTINU</h2>

<table border="1">
    <tr>
        <th>Bloc de compétences</th>
        <th>Moyenne de l'étudiant</th>
        <th>Moyenne de la promotion</th>
    </tr>
    <#list evalList as evalByBloc>
        <tr>
            <td>
                ${evalByBloc.activiteType.libelle}
            </td>
            <td>${evalByBloc.moyenne?string["0.##"]}</td>
            <td>${evalByBloc.moyennePromo?string["0.##"]}</td>
        </tr>
    </#list>
</table>
Moyenne générale de l'étudiant :	${moyEtudiant?string["0.##"]}
<br />
Moyenne générale de la promotion : 	${moyPromo?string["0.##"]}

piedPage


</body>
</html>


