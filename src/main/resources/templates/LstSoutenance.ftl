<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
	@page {
		size: landscape
	}
    .divGlobal
        {
            /* margin: 3px;
            TODO
            */
        }
    table
        {
            border: 1px solid black;
            text-align: center;
        }
        table, th, td
        {
            border: 2px solid black;
            border-collapse: collapse;
        }
    th
        {
            background-color: rgb(255, 192, 0);
        }
    .l2
        {
            background-color: rgb(146, 208, 80);
        }
    .dej
        {
            background-color: rgb(166, 166, 166);
        }
</style>
<title>Document liste Soutenance</title>
</head>
<body>

<div class="divGlobal">
    <table>
            <tr>
                <th colspan="15">
                    <h3>Titre Professionnel Monteur.audiovisuel  - BORDEAUX le 12/09/2023</h3>
                </th>
            </tr>
            <tr>
                <td colspan="15" class="l2">
                    <p>
                        Jour 1 - ${(soutenanceDto?first.examDate!"N/A")?string("dd/MM/yyyy")}<br/>
                        Mise en situation professionnelle (durée : 4h)<br/>
                        Accueil candidat et jury : 8h30<br/>
                        Ouverture de pli : 8h50<br/>
                        Heure de début : 9h00 / Heure de fin : 13h00
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Civilité</p>
                </td>
                <td>
                    <p>Nom</p>
                </td>
                <td>
                    <p>Prénom</p>
                </td>
                <td>
                    <p>N° candidat</p>
                </td>
                <td>
                    <p>Adresse</p>
                </td>
                <td>
                    <p>Code Postal</p>
                </td>
                <td>
                    <p>Ville</p>
                </td>
                <td>
                    <p>Jour convocation Oral</p>
                </td>
                <td>
                    <p>Heure convocation Oral</p>
                </td>
                <td>
                    <p>Accueil candidat (5mn)</p>
                </td>
                <td>
                    <p>Entretien technique (40mn)</p>
                </td>
                <td>
                    <p>Questionnement à partir de production 50mn</p>
                </td>
                <td>
                    <p>Entretien final (30mn)</p>
                </td>
                <td>
                    <p>Délibération du jury (10mn)</p>
                </td>
                <td rowspan="4">
                    <p>JOUR 1</p>
                </td>
            </tr>
            <tr>
                <td colspan="14" class="dej">
                    <p>Pause déjeuner</p>
                </td>
            </tr>
            <#list soutenanceDto as soutenance>
                <tr>
                    <td>
                    							${soutenance.etudiant.utilisateurDto.civilite}
                    						</td>
                    						<td>
                    							${soutenance.etudiant.utilisateurDto.nom}
                    						</td>
                    						<td>
                    							${soutenance.etudiant.utilisateurDto.prenom}
                    						</td>
                    						<td>
                    							N° candidat
                    						</td>
                    						<td>
                    							${soutenance.etudiant.utilisateurDto.adresseDto.libelle}
                    						</td>
                    						<td>
                    							${soutenance.etudiant.utilisateurDto.adresseDto.codePostal}
                    						</td>
                    						<td>
                    							${soutenance.etudiant.utilisateurDto.adresseDto.ville}
                    						</td>
                    						<td>
                    							${soutenance.examDate?string("dd/MM/yyyy")}
                    						</td>
                    						<td>
                    							${soutenance.examDate?string("HH:mm")}
                    						</td>
                    						<td>
                    							${soutenance.minAccueil?string("HH:mm")} à ${soutenance.minEntretien?string("HH:mm")}
                    						</td>
                    						<td>
                    							${soutenance.minEntretien?string("HH:mm")} à ${soutenance.minQuestion?string("HH:mm")}
                    						</td>
                    						<td>
                    							${soutenance.minQuestion?string("HH:mm")} à ${soutenance.minEntretienFinal?string("HH:mm")}
                    						</td>
                    						<td>
                    							${soutenance.minEntretienFinal?string("HH:mm")} à ${soutenance.minDeliberation?string("HH:mm")}
                    						</td>
                    						<td>
                    							${soutenance.minDeliberation?string("HH:mm")} à ${soutenance.minDeliberation?string("HH:mm")}
                    						</td>
                </tr>
            </#list>
    </table>
</div>
</body>
</html>