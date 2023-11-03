<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
		.divGlobal
			{
				/* margin: 3px; */
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
							Jour 1 - 12/09/2023<br/>
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
				<tr>
					<#list soutenance.etudiant.Dto as soutenace>
						<td>
							<p>Monsieur</p>
						</td>
						<td>
							<p>${soutenance.etudiant.utilisateurDto.nom} Nom</p>
						</td>
						<td>
							<p>${soutenance.etudiant.utilisateurDto.prenom} Prénom</p>
						</td>
						<td>
							<p>N° candidat</p>
						</td>
						<td>
							<p>${soutenance.etudiant.utilisateurDto.adresseDto.libelle} Adresse</p>
						</td>
						<td>
							<p>${soutenance.etudiant.utilisateurDto.adresseDto.codePostal} Code Postal</p>
						</td>
						<td>
							<p>${soutenance.etudiant.utilisateurDto.adresseDto.ville} Ville</p>
						</td>
						<td>
							<p>${soutenance.jour} Jour convocation Oral</p>
						</td>
						<td>
							<p>${soutenance.heure} Heure convocation Oral</p>
						</td>
						<td>
							<p>${soutenance.minAccueil} à ${soutenance.minEntretien}</p>
						</td>
						<td>
							<p>${soutenance.minEntretien} à ${soutenance.minQuestion}</p>
						</td>
						<td>
							<p>${soutenance.minQuestion} à ${soutenance.minEntretienFinal}</p>
						</td>
						<td>
							<p>${soutenance.minEntretienFinal} à ${soutenance.minDeliberation}</p>
						</td>
						<td>
							<p>${soutenance.minDeliberation} (10mn)</p>
						</td>
					</#list>
				</tr>
			
		</table>
    </div>
</body>
</html>