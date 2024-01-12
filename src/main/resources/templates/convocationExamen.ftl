<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        .divGlobal
            {
                display: inline-block;
                margin: 0 25% 0 5%;
            }
        .presentation
            {
                display: flex;
                justify-content: space-between;
                padding-right: 10%;
                margin-bottom: 5%;
            }
        .responsable
            {
                display: flex;
                justify-content: flex-end;
                margin: 5% 10% 0 0;
            }

    </style>
    <title>Convocation_candidat</title>
</head>
<body>
    <div class="divGlobal">
    <img src="capture.png" alt="image convocation"/>

        <div class="presentation">
            <div>
                <p>DAWAN <br/> {{}} 250 avenue Emile Counord <br/> {{}} 33000 BORDEAUX</p>
                <p>Contact : {{}} Frédéric James BAUDOT <br/> <span style="margin-left: 30%;"> {{}} 07 61 04 96 90</span> </p>

            </div>
            <div>
                <p style="background-color: yellow;">Monsieur ${tDto.etudiant.utilisateurDto.nom + " " + tDto.etudiant.utilisateurDto.prenom} <br/> <b>Candidat n°</b> {{}}</p>
                <p> {{}} Route de Laujuzan <br/> {{}} 32110 - CAUPENNE D’ARMAGNAC</p>
                <p style="margin-top: 17%;"> ${tDto.examDate?string("dd/MM/yyyy")}Le 12 juillet 2023,</p>
            </div>
        </div>

        <h3>
            <b><u>Objet :</u>
            Convocation à la session d’examen du ${tDto.examDate?string("dd/MM/yyyy")} 12 septembre 2023 pour le Titre professionnel
            « ${tDto.etudiant.promotionsDto[0]} Monteur / Monteuse audiovisuel ».</b>
        </h3>
        <div>

            <p>
                <p>Monsieur,</p>
                Nous vous prions de bien vouloir vous présenter à la session d’examen visant l’obtention du Titre
                professionnel « ${tDto.etudiant.promotionsDto[0]} Monteur / Monteuse audiovisuel » :

                <h3 style="margin-left: 3%;">➢ Le ${tDto.examDate?string("dd/MM/yyyy")} 12 septembre 2023 à ${tDto.examDate?string("HH:mm")} au {{}} 250 avenue Emile Counord, {{}} 33000 BORDEAUX</h3>

                Le déroulé de l’examen est le suivant : <br/>
                - Mise en situation professionnelle à {{}} 9h00 (durée : 4h00) : Réalisation d’un montage.

                <h3 style="margin-left: 3%;"> ➢ Le ${tDto.examDate?string("dd/MM/yyyy")} 12 septembre 2023 à 16h05 au {{}} 250 avenue Emile Counord, {{}} 33000 BORDEAUX</h3>

                <p>Le déroulé de l’examen est le suivant :</p>

                - Accueil et entretien technique de ${tDto.minAccueil?string("HH:mm")} 16h20 – ${tDto.minEntretien?string("HH:mm")} 17h05 (durée : 5mn + 40mn) : Présentation du montage réalisé
                lors de la mise en situation + questions techniques. <br/>
                - Questionnement à partir de production de ${tDto.minQuestion?string("HH:mm")} 17h05 – ${tDto.minEntretienFinal?string("HH:mm")} 17h55 (durée : 50mn) : Questions des jurys sur des
                montages réalisés en amont. <br/>
                - Entretien final de ${tDto.minEntretienFinal?string("HH:mm")} 17h55 – ${tDto.minDeliberation?string("HH:mm")} 18h25 (durée : 30mn) : Discussion sur le Dossier Professionnel, la vision et la
                compréhension du métier et l’acquisition d’une culture professionnelle. <br/>
                - Temps de délibération du jury (durée : 10mn).

                <h3>
                    <b><u>Vous devrez être muni :</u></b>
                </h3>

                <p>• De l’original de votre pièce d’identité,<br/>
                    • De la présente convocation.</p>
                Nous vous prions d’agréer, Monsieur, l’expression de notre considération distinguée.
            </p>

            <div class="responsable">
                Responsable de session <br/>
                {{}} Frédéric James BAUDOT <br/>

                {{}}
            </div>
        </div>

    </div>

</body>
</html>
