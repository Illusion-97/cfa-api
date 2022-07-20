<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="${backendUrl}css/bulletinEval.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">

</head>
<body>
<div class="mainDiv">
    <div class="divLogo">
        <img class="logo" src="${backendUrl}pictures/institutionnel-logo.png" alt="Logo Dawan">
    </div>
    <h1>Bulletin d'évaluation</h1>
    <h4>${titrePro}</h4>
    <ul>
        <li>${etudiant.utilisateur.prenom} ${etudiant.utilisateur.nom}</li>
        <li>Année d'étude : ${promoAnnee}</li>
    </ul>
    <h2 id="red">Contrôle continu</h2>
    <table>
        <tr>
            <th>Bloc de compétences</th>
            <th>Moyenne de l'étudiant</th>
            <th>Moyenne de la promotion</th>
        </tr>
        <#list evalList as evalByBloc>
            <tr>
                <td class="tdLibelle">
                    ${evalByBloc.activiteType.libelle}
                </td>
                <td>${evalByBloc.moyenne?string["0.##"]}</td>
                <td>${evalByBloc.moyennePromo?string["0.##"]}</td>
            </tr>
        </#list>
    </table>
    <ul class="ulMoyenne">
        <li class="liMoyenne">Moyenne générale de l'étudiant : ${moyEtudiant?string["0.##"]}/20</li>
        <li>Moyenne générale de la promotion : ${moyPromo?string["0.##"]}/20</li>
    </ul>
</div>
</body>
</html>


