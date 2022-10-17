<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    * {
        box-sizing: border-box;


    }

    body {

        font-family: Calibri;
        width: 100vw;
        display: flex;
        flex-flow: column nowrap;
        align-items: center;
        align-content: center;
        justify-content: center;

    }


    h1 {
        text-align: center;
        font-size: 21pt;
    }

    footer h1 {
        font-size: 17pt;

    }

    .header {

        height: 6.16cm;
        align-items: center;

    }

    #titre {
        float: right;
        margin-left: 5px;
        width: 480px;

    }

    .header img {
        width: 5.48cm;
        height: 6.16cm;
        float: left;
    }

    #bas {
        margin: 0;
        border-bottom: 2px solid #bfbfbf;
        margin-bottom: 3px;
    }

    #haut {

        margin-bottom: 0;

    }

    .headerhaut {
        margin: 0;
        height: 42px;
        background-color: #f2f2f2;
        border-bottom: 1px solid #d4d4d4;
    }

    .headerbas {
        margin: 0;
        height: 97px;
        background-color: #f2f2f2
    }

    .basRose,
    .basRoseTitre {
        margin-top: 3px;
        height: 3px;
        background-color: #d60093;
    }

    .basRoseTitre {
        margin-top: 10px;
    }

    .titreProfessionnel {
        height: 50px;
        background-color: #d60093;
        color: white;
        text-align: center;
        font-size: 16pt;
        vertical-align: middle;
        line-height: 10px;

    }

    .presentation .titreProfessionnel {
        margin-top: 100px;

    }

    .nomTitre,
    .niveauTitre {
        text-align: center;
    }

    .blocDate {
        height: 180px;
        border: 1px solid #bfbfbf;
        padding-top: 50px;

    }

    .blocDate>* {
        margin: 0;
        margin-bottom: 5px;

    }

    .spanDateRight {
        width: 340;
        float: right;
        text-align: left;

    }

    .spanDate {
        width: 340;
        float: left;
        text-align: right;


    }

    .arrow-right {
        width: 0;
        height: 0;
        border-top: 5px solid transparent;
        border-bottom: 5px solid transparent;
        border-left: 5px solid black;

    }

    .spanF {
        display: inline-block;
        width: 200px;
        font-style: italic;
        padding-left: 8px;
    }

    .spanFT {

        padding-left: 15px;
    }

    .candidat .spanF,
    .candidat .arrow-right,
    .blockText .arrow-right {
        border-left: 5px solid #d60093;
    }

    .formation .spanF {
        border-left: 5px solid #bfbfbf;
    }

    .candidat .spanF {
        color: #d60093;
    }

    .formation {
        margin-top: 40px;
    }

    .candidat {
        margin-top: 30px;

    }

    .candidat>p {
        margin: 0 0 6px 5px;
        font-weight: bold;
        color: #d60093;
    }

    table.minimalistBlack {
        width: 100%;
        text-align: center;
        border-collapse: collapse;
    }

    table.minimalistBlack td,
    table.minimalistBlack th {
        border: 1px solid #000000;
        padding: 5px 4px;
    }

    table.minimalistBlack tbody td {
        font-size: 13px;
    }

    table.minimalistBlack tfoot td {
        font-size: 14px;
    }

    .presentation .minimalistBlack {
        margin-top: 60px;
    }

    .textBlock {
        margin: 15px;
        text-align: justify;
        margin-top: 0;
    }

    .guide {
        margin-top: 480px;
    }

    .guide>span {
        font-style: italic;
        text-decoration: underline;
    }

    .fiche {
        font-size: 16pt;
        height: 50px;
        background-color: #d60093;
        color: white;
        line-height: 10px;

    }

    .at {
        display: inline-block;
        width: 120px;
        margin-top: 0;
        padding-top: 0;
        color: #d60093;

    }

    .libelle {
        display: inline-block;
        width: 520px;
    }

    .competences {
        border: 1px solid #bfbfbf;
        margin: 10px 0
    }

    .competences * {
        margin: 0;
    }

    .competences h3,
    .competences p {
        margin: 5px
    }

    .minimalistBlackEvaluation h4 {
        text-decoration: underline;

    }

    table.minimalistBlackEvaluation {
        border: 3px solid #000000;
        width: 100%;
        height: 625px;
        text-align: left;
        border-collapse: collapse;
        margin-bottom: 8px;
    }

    table.minimalistBlackEvaluation td,
    table.minimalistBlackEvaluation th {
        border: 1px solid #000000;
        padding: 5px 4px;
    }

    table.minimalistBlackEvaluation tbody td {
        font-size: 13px;
    }

    table.minimalistBlackEvaluation thead {
        border-bottom: 3px solid #000000;
    }

    table.minimalistBlackEvaluation thead th {
        font-size: 15px;
        font-weight: bold;
        color: #000000;
        text-align: left;
    }

    table.minimalistBlackEvaluation tfoot td {
        font-size: 14px;
    }

    div.tableChekCompetence {
        width: 350px;
        height: 100px;
        text-align: left;
        border-collapse: collapse;
    }

    .divTable.tableChekCompetence .divTableCell,
    .divTable.tableChekCompetence .divTableHead {
        border: 1px solid #000000;
        padding: 5px 4px;
    }

    .divTable.tableChekCompetence .divTableBody .divTableCell {
        font-size: 13px;
    }

    .tableChekCompetence .tableFootStyle {
        font-size: 14px;
    }

    .divTableCell * {
        margin: 0;
    }

    .divTableCell label {
        margin: 10px;
    }

    /* DivTable.com */
    .divTable {
        display: table;
    }

    .divTableRow {
        display: table-row;
    }

    .divTableHeading {
        display: table-header-group;
    }

    .divTableCell,
    .divTableHead {
        display: table-cell;
    }

    .divTableHeading {
        display: table-header-group;
    }

    .divTableFoot {
        display: table-footer-group;
    }

    .divTableBody {
        display: table-row-group;
    }

    .blockText>h4 {
        padding: 5px;
        border-bottom: 1px solid #d60093;
        border-left: 3px solid #d60093;
        margin-bottom: 0;
    }

    .blockText>div {
        padding-top: 15px;
        padding-left: 20px;
        min-height: 240px;
        border-right: 1px solid #bfbfbf;
        border-left: 1px solid #bfbfbf;

    }

    .blockText .arrow-right {
        margin-right: 20px;
    }

    .blockText>h4>p {
        font-style: italic;
        color: #bfbfbf;
        margin: 0;
        font-size: 12pt;
        font-weight: lighter;
    }

    .arrow-formateur {
        margin: 0 10px;
    }

    table.formateur1 {
        width: 100%;
        min-height: 70px;
        text-align: left;
        border-collapse: collapse;
    }

    table.formateur1 td,
    table.formateur1 th {
        border: 1px solid #B7B7B7;
        padding: 5px 4px;
    }

    table.formateur1 tbody td {
        font-size: 13px;
    }

    table.formateur1 tfoot td {
        font-size: 14px;
    }

    .formateur1 td {
        padding-inline-start: 10px;
    }

    .separation {
        border: 1px solid #B7B7B7;
        margin-top: 80px;
        margin-bottom: 5px;
    }

    table.synthese {
        width: 100%;
        text-align: left;
        border-collapse: collapse;
    }

    table.synthese td,
    table.synthese th {
        border: 1px solid #B7B7B7;
        padding: 5px 4px;
    }

    table.synthese tbody td {
        font-size: 13px;
    }

    table.synthese thead {
        background: #E2E2E2;
        background: -moz-linear-gradient(top, #e9e9e9 0%, #e5e5e5 66%, #E2E2E2 100%);
        background: -webkit-linear-gradient(top, #e9e9e9 0%, #e5e5e5 66%, #E2E2E2 100%);
        background: linear-gradient(to bottom, #e9e9e9 0%, #e5e5e5 66%, #E2E2E2 100%);
    }

    table.synthese thead th {
        font-size: 15px;
        font-weight: bold;
        color: #000000;
        text-align: left;
    }

    table.synthese tfoot td {
        font-size: 14px;
    }

    div.synthese {
        width: 100%;
        text-align: left;
        border-collapse: collapse;
    }

    .divTable.synthese .divTableCell,
    .divTable.synthese .divTableHead {
        border: 1px solid #B7B7B7;
        padding: 5px 4px;
    }

    .divTable.synthese .divTableBody .divTableCell {
        font-size: 13px;
    }

    .divTable.synthese .divTableHeading {
        background: #E2E2E2;
        background: -moz-linear-gradient(top, #e9e9e9 0%, #e5e5e5 66%, #E2E2E2 100%);
        background: -webkit-linear-gradient(top, #e9e9e9 0%, #e5e5e5 66%, #E2E2E2 100%);
        background: linear-gradient(to bottom, #e9e9e9 0%, #e5e5e5 66%, #E2E2E2 100%);
    }

    .divTable.synthese .divTableHeading .divTableHead {
        font-size: 15px;
        font-weight: bold;
        color: #000000;
        text-align: left;
    }

    .synthese .tableFootStyle {
        font-size: 14px;
    }

    input {
        position: relative;
        top: 10px;

    }

    
</style>

<body>
    <section id="contaiter">
        <header>
            <section class="header">
                <img src="${backendUrl}/pictures/frenchRepublic.jpg" alt="image république française">
                <div id="titre">
                    <div class="headerhaut"></div>
                    <h1 id="haut">LIVRET D’EVALUATIONS PASSEES</h1>
                    <h1 id="bas">EN COURS DE FORMATION</h1>
                    <div class="headerbas"></div>
                    <div class="basRose"></div>
                </div>
            </section>
        </header>
        <main>
            <section class="presentation">
                <div class="titreProfessionnel">
                    <h2>Titre professionnel </h2>
                </div>
                <div class="basRoseTitre"></div>
                <h3 class="nomTitre">Concepteur développeur d’applications</h3>
                <p class="niveauTitre"> Niveau 6</p>
                <div class="blocDate">
                    <p>
                    <div class="spanDate">Arrêté du :</div>
                    <div class="spanDateRight"> 06/04/2018</div>
                    </p>
                    <p>
                    <div class="spanDate">J.O. du :</div>
                    <div class="spanDateRight"> 17/04/2018</div>
                    </p>
                    <p>
                    <div class="spanDate">Date d’effet au :</div>
                    <div class="spanDateRight"> 18/12/2018</div>
                    </p>
                </div>
                <div class="formation">
                    <div>
                        <span class="spanF">Organisme de formation</span>
                        <div style=" display: inline-block;" class="arrow-right"></div> <span
                            class="spanFT">Dawan</span>
                    </div>
                    <div>
                        <span class="spanF">Lieu de formation</span>
                        <div style=" display: inline-block;" class="arrow-right"></div> <span
                            class="spanFT">Rennes</span>
                    </div>

                </div>
                <div class="candidat">
                    <p>Candidat(e) :</p>
                    <div>
                        <span class="spanF">Nom </span>
                        <div style=" display: inline-block;" class="arrow-right"></div> <span class="spanFT">Ben </span>
                    </div>
                    <div>
                        <span class="spanF">Prénom</span>
                        <div style=" display: inline-block;" class="arrow-right"></div> <span
                            class="spanFT">Feres</span>
                    </div>
                    <div>
                        <span class="spanF">Date de naissance</span>
                        <div style=" display: inline-block;" class="arrow-right"></div> <span
                            class="spanFT">30/3/1994</span>
                    </div>

                </div>
                <table class="minimalistBlack">
                    <tbody>
                        <tr>
                            <td>SIGLE</td>
                            <td>Type de document</td>
                            <td>Code titre</td>
                            <td>Mill&eacute;sime</td>
                            <td>Date JO</td>
                            <td>Date de mise &agrave; jour</td>
                            <td>Page</td>
                        </tr>
                        <tr>
                            <td>CDA</td>
                            <td>Livret d’évaluations passées en cours de formation </td>
                            <td>TP-01281</td>
                            <td>03</td>
                            <td>17/04/2018</td>
                            <td>27/04/2018</td>
                            <td>1/6</td>
                        </tr>
                    </tbody>
                </table>
                <div class="titreProfessionnel">
                    <h2>Présentation du dossier</h2>
                </div>
                <div class="basRoseTitre"></div>
                <div class="blocDate">
                    <p class="textBlock">
                        Les évaluations passées en cours de formation décrites dans les fiches qui suivent ont été mises
                        en œuvre en référence aux critères issus des référentiels du titre professionnel, pour les
                        sessions d’examen « Titre », « CCP » et « CCS » telles que prévues par l’arrêté du 22 décembre
                        2015 relatif aux conditions de délivrance du titre professionnel du ministère chargé de
                        l’emploi.
                    </p>
                </div>
                <p class="guide">
                    Le <strong>Guide de mise en œuvre</strong> des Evaluations passées en cours de formation est à
                    télécharger sur le site du ministère de l’emploi :<span> http://travail-emploi.gouv.fr/</span>
                    (rubrique <span>Documents techniques</span>).
                    Il comporte un mode d’emploi du présent Livret d’évaluations passées en cours de formation
                </p>
                <table class="minimalistBlack">
                    <tbody>
                        <tr>
                            <td>SIGLE</td>
                            <td>Type de document</td>
                            <td>Code titre</td>
                            <td>Mill&eacute;sime</td>
                            <td>Date JO</td>
                            <td>Date de mise &agrave; jour</td>
                            <td>Page</td>
                        </tr>
                        <tr>
                            <td>CDA</td>
                            <td>Livret d’évaluations passées en cours de formation </td>
                            <td>TP-01281</td>
                            <td>03</td>
                            <td>17/04/2018</td>
                            <td>27/04/2018</td>
                            <td>2/6</td>
                        </tr>
                    </tbody>
                </table>
            </section>
            <section class="ficheResultat">
                <div class="fiche">
                    <h2> Fiche de résultats des évaluations</h2>
                </div>
                <div>
                    <h3 class="at">
                        Activité-type <span> 1</span>
                    </h3>

                    <h3 class="libelle">
                        Concevoir et développer des composants d'interface utilisateur en intégrant les recommandations
                        de sécurité
                    </h3>
                </div>
                <div class="basRoseTitre"></div>
                <div class="competences">
                    <h3>Compétences :</h3>
                    <p><span>1.</span>Maquetter une application</p>
                    <p><span>2.</span>Développer une interface utilisateur de type desktop </p>
                    <p><span>3.</span> Développer des composants d’accès aux données</p>
                    <p><span>4.</span>Développer la partie front-end d’une interface utilisateur web</p>
                    <p><span>5.</span>Développer la partie back-end d’une interface utilisateur web</p>
                </div>
                <table class="minimalistBlackEvaluation">
                    <thead>
                        <tr>
                            <th>Description des évaluations mises en œuvre</th>
                            <th>Dates</th>
                            <th>Compétences évaluées (cochez)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <h4> <span>1 </span> Conception d’une application Desktop de gestion d’un carnet
                                    d’adresses </h4>
                                <p>Analyse du cahier des charges </p>
                            </td>
                            <td>
                                30/07/2020
                            </td>
                            <td>
                                <div class="divTable tableChekCompetence">
                                    <div class="divTableBody">
                                        <div class="divTableRow">
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">1</label>
                                                <input type="checkbox" id="scales" name="scales">
                                            </div>
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">2</label>
                                                <input type="checkbox" id="scales" name="scales" checked>

                                            </div>
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">3</label>
                                                <input type="checkbox" id="scales" name="scales">
                                            </div>
                                        </div>
                                        <div class="divTableRow">
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">4</label>
                                                <input type="checkbox" id="scales" name="scales" checked>
                                            </div>
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">5</label>
                                                <input type="checkbox" id="scales" name="scales">
                                            </div>
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">6</label>
                                                <input type="checkbox" id="scales" name="scales">
                                            </div>
                                        </div>
                                        <div class="divTableRow">
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">7</label>
                                                <input type="checkbox" id="scales" name="scales" checked>

                                            </div>
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">8</label>
                                                <input type="checkbox" id="scales" name="scales">
                                            </div>
                                            <div class="divTableCell">&nbsp;
                                                <label for="scales">9</label>
                                                <input type="checkbox" id="scales" name="scales">
                                            </div>
                                        </div>
                                    </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h4> <span>2 </span> Développer la partie front-end d’une application de gestion d’un
                                    catalogue de produits. </h4>
                            </td>
                            <td>cell2_2</td>
                            <td>cell3_2</td>
                        </tr>
                        <tr>
                            <td>
                                <h4> <span>2 </span> Création d’une application de comparaison d’offres de formations
                                    (projet en groupes – 4 semaines)
                                </h4>

                            </td>
                            <td>cell2_3</td>
                            <td>cell3_3</td>
                        </tr>
                    </tbody>
                </table>
                <table class="minimalistBlack">
                    <tbody>
                        <tr>
                            <td>SIGLE</td>
                            <td>Type de document</td>
                            <td>Code titre</td>
                            <td>Mill&eacute;sime</td>
                            <td>Date JO</td>
                            <td>Date de mise &agrave; jour</td>
                            <td>Page</td>
                        </tr>
                        <tr>
                            <td>CDA</td>
                            <td>Livret d’évaluations passées en cours de formation </td>
                            <td>TP-01281</td>
                            <td>03</td>
                            <td>17/04/2018</td>
                            <td>27/04/2018</td>
                            <td>3/6</td>
                        </tr>
                    </tbody>
                </table>

                <h5 style="margin-bottom: 5px;">
                    Lors de l’évaluation ou des évaluations passée(e) en cours de formation, le/la candidat(e) est
                    considéré(e) :
                </h5>
                <div>
                    <input style="margin: 0; display: inline-block;" type="checkbox" id="scales" name="scales" checked>
                    Avoir satisfait aux critères issus des référentiels du titre professionnel
                    attendus pour la réalisation de cette activité-type.
                </div>
                <div>

                    <input style="margin: 0; display: inline-block;" type="checkbox" id="scales" name="scales">
                    Ne pas avoir satisfait aux critères issus des référentiels du titre professionnel.
                </div>
                <div class="blockText">
                    <h4>
                        Si le candidat n’a pas satisfait aux critères issus des référentiels, notez ci-dessous les
                        points d’attention et précisions éventuelles.
                    </h4>
                    <div>
                        <p style="margin: 0 ;">
                        <div style=" display: inline-block;" class="arrow-right"></div>
                        taper text ici
                        </p>
                    </div>
                </div>
                <div class="blockText">
                    <h4>
                        Compétences à réévaluer :
                        <p>(Voir évaluations complémentaires ci-après.)</p>
                    </h4>
                    <div>
                        <p style="margin: 0 ;">
                        <div style=" display: inline-block;" class="arrow-right"></div>
                        taper text ici
                        </p>
                    </div>
                </div>
                <h4 style="margin-bottom: 0"><span style="padding-right: 300px;">Formateur(s) évaluateur(s)</span>
                    <span> Visa <span>
                </h4>
                <table class="formateur1">
                    <tbody>
                        <tr>
                            <td> Nom
                                <div style=" display: inline-block;" class="arrow-right arrow-formateur"></div>
                                Mouhamed derkaoui

                            </td>
                            <td> Date
                                <div style=" display: inline-block;" class="arrow-right arrow-formateur"></div>
                                30/12/2022
                            </td>
                            <td>
                                <img src="" alt="" width="100px" height="50px">Signature
                            </td>
                        </tr>
                    </tbody>
                </table>
                <!-- si 2 formateur margin-top 15px -->
                <table class="minimalistBlack" style="margin-top:80px;">
                    <tbody>
                        <tr>
                            <td>SIGLE</td>
                            <td>Type de document</td>
                            <td>Code titre</td>
                            <td>Mill&eacute;sime</td>
                            <td>Date JO</td>
                            <td>Date de mise &agrave; jour</td>
                            <td>Page</td>
                        </tr>
                        <tr>
                            <td>CDA</td>
                            <td>Livret d’évaluations passées en cours de formation </td>
                            <td>TP-01281</td>
                            <td>03</td>
                            <td>17/04/2018</td>
                            <td>27/04/2018</td>
                            <td>4/6</td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </main>
        <footer>
            <div class="separation"></div>
            <div>
                <h1 id="haut">SYNTHESE DES RESULTATS OBTENUS PAR LE CANDIDAT</h1>
                <h1 id="bas">A L’ISSUE DU PARCOURS DE FORMATION</h1>
                <div class="headerhaut"></div>
                <div class="basRose"></div>
            </div>
            <div>
                <!-- <table class="synthese">
                    <thead>
                        <tr>
                            <th>Intitulé de l’activité type </th>
                            <th>Compétences professionnelles </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <h5>
                                    Concevoir et développer des composants d'interface utilisateur en intégrant les
                                    recommandations de sécurité
                                </h5>
                            </td>
                            <td>
                                <p><span>1.</span>Maquetter une application</p>
                                <p><span>2.</span>Développer une interface utilisateur de type desktop </p>
                                <p><span>3.</span> Développer des composants d’accès aux données</p>
                                <p><span>4.</span>Développer la partie front-end d’une interface utilisateur web</p>
                                <p><span>5.</span>Développer la partie back-end d’une interface utilisateur web</p>
                            </td>
                        </tr>
                    </tbody>
                </table> -->
                <div class="divTable synthese" style=" margin: 0;">
                    <div class="divTableHeading">
                        <div class="divTableRow">
                            <div class="divTableHead" style="width:250px ;">Intitulé de l’activité type</div>
                            <div class="divTableHead" style="width:450px;">Compétences professionnelles</div>
                        </div>
                    </div>
                    <div class="divTableBody">
                        <div class="divTableRow">
                            <div class="divTableCell" style="width:250px; height: 155px ">
                                <h5>
                                    Concevoir et développer des composants d'interface utilisateur en intégrant les
                                    recommandations de sécurité
                                </h5>
                            </div>
                            <div class="divTableCell" style="width:450px ;height: 155px">
                                <p><span>1.</span>Maquetter une application</p>
                                <p><span>2.</span>Développer une interface utilisateur de type desktop </p>
                                <p><span>3.</span> Développer des composants d’accès aux données</p>
                                <p><span>4.</span>Développer la partie front-end d’une interface utilisateur web</p>
                                <p><span>5.</span>Développer la partie back-end d’une interface utilisateur web</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div>

                    <div style=" margin: 0; border-left: 1px solid #B7B7B7 ;">
                        <p style=" margin: 0 ; padding-top: 12px;">
                        <div style=" margin: 0 10px; display: inline-block ;  border-left: 5px solid #d60093;"
                            class="arrow-right"></div> L’activité <span>1</span> est maîtrisée : <span> OUI</span>
                        <input display: inline-block type="checkbox" id="scales" name="scales" checked><span> NON</span>
                        <input display: inline-block type="checkbox" id="scales" name="scales">
                        </p>
                    </div>
                </div>
                <div class="divTable synthese">
                    <div class="divTableBody">
                        <div class="divTableRow"  style="height: 200px;">
                            <div class="divTableCell" style="width:250px ; height: 155px">cell1_1</div>
                            <div class="divTableCell" style="width:450px ; height: 155px">cell2_1</div>
                        </div>
                    </div>

                </div>
                <div>
                    <div style=" margin: 0; border-left: 1px solid #B7B7B7 ;">
                        <p style=" margin: 0 ; padding-top: 12px;">
                        <div style=" margin: 0 10px; display: inline-block ;  border-left: 5px solid #d60093;"
                            class="arrow-right"></div> L’activité <span>2</span> est maîtrisée : <span> OUI</span>
                        <input display: inline-block type="checkbox" id="scales" name="scales" checked><span> NON</span>
                        <input display: inline-block type="checkbox" id="scales" name="scales">
                        </p>
                    </div>
                </div>
                <div class="divTable synthese">
                    <div class="divTableBody">
                        <div class="divTableRow" style="height: 200px;">
                            <div class="divTableCell" style="width:250px  ;height: 155px;">cell1_1</div>
                            <div class="divTableCell" style="width:450px;height: 155px;">cell2_1</div>
                        </div>
                    </div>
                </div>
                <div>
                    <div style=" margin: 0; border-left: 1px solid #B7B7B7 ;">
                        <p style=" margin: 0 ; padding-top: 12px;">
                        <div style=" margin: 0 10px; display: inline-block ;  border-left: 5px solid #d60093;"
                            class="arrow-right"></div> L’activité <span>2</span> est maîtrisée : <span> OUI</span>
                        <input display: inline-block type="checkbox" id="scales" name="scales" checked><span> NON</span>
                        <input display: inline-block type="checkbox" id="scales" name="scales">
                        </p>
                    </div>
                </div>
                <table class="minimalistBlack" style="margin-top:80px;">
                    <tbody>
                        <tr>
                            <td>SIGLE</td>
                            <td>Type de document</td>
                            <td>Code titre</td>
                            <td>Mill&eacute;sime</td>
                            <td>Date JO</td>
                            <td>Date de mise &agrave; jour</td>
                            <td>Page</td>
                        </tr>
                        <tr>
                            <td>CDA</td>
                            <td>Livret d’évaluations passées en cours de formation </td>
                            <td>TP-01281</td>
                            <td>03</td>
                            <td>17/04/2018</td>
                            <td>27/04/2018</td>
                            <td>5/6</td>
                        </tr>
                    </tbody>
                </table>
                <div class="blockText">
                    <h4>
                        Observations :
                    </h4>
                    <div>
                        <p style="margin: 0 ;">
                        <div style=" display: inline-block;" class="arrow-right"></div>
                        taper text ici
                        </p>
                    </div>
                </div>
                <h4 style="margin-bottom: 0"><span style="padding-right: 300px;">Formateur(s) / Evaluateur(s)</span>
                    <span> Visa <span>
                </h4>
                <table class="formateur1">
                    <tbody>
                        <tr>
                            <td> Nom
                                <div style=" display: inline-block;" class="arrow-right arrow-formateur"></div>
                                Mouhamed derkaoui

                            </td>
                            <td> Date
                                <div style=" display: inline-block;" class="arrow-right arrow-formateur"></div>
                                30/12/2022
                            </td>
                            <td>
                                <img src="" alt="" width="100px" height="50px">Signature
                            </td>
                        </tr>
                    </tbody>
                </table>
                <h4 style="margin-bottom: 0"><span style="padding-right: 200px;">Représentant de l’organisme de formation</span>
                   
                </h4>
                <table class="formateur1">
                    <tbody>
                        <tr>
                            <td> Nom
                                <div style=" display: inline-block;" class="arrow-right arrow-formateur"></div>
                               Jérôme Merckling

                            </td>
                            <td> Date
                                <div style=" display: inline-block;" class="arrow-right arrow-formateur"></div>
                                30/12/2022
                            </td>
                            <td>
                                <img src="" alt="" width="100px" height="50px">Signature
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p>
                    Un exemplaire du livret a été remis au candidat pour information par l’organisme de formation contre signature le 10/12/2021
                </p>
                <h4>
                    Signature du candidat pour information:
                </h4>
        </footer>
    </section>
</body>

</html>