<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <style>
	.divGlobal
	    {
	        margin: 3px;
	    }
	
	.head
	    {
	        display: flex;
	        margin-bottom: 45px;
	        /* background-color: #e11b28;
	        color: white; */
	    }
	    
	.head h1
		{
			margin-left: 25px;
		}
		
	hr
		{
			background-color: red;
		}
	
	h1
	    {
	        font-size: 55px;
	    }
	
	h3  
	    {
	        font-size: 31px;
	    }
	
	.projet
	    {
	        text-align: center;
	        margin-bottom: 15px;
	    }
	
	.h2  
	    {
	        font-size: 35px;
	        padding-left: 5px;
	        background-color: #565656;
	        color: white;
	        border-radius: 0 9px;
	    }
	
	.divtitre
	    {
	    	padding: 0 23px 0 45px;
	        margin: 0 0 55px 0;
	    }

    </style>
    <title>Document ${dossierProjet.nom}</title>
</head>
<body>

    <div class="divGlobal">

        <div class="head">
            <img class="logo" src="${backendUrl}pictures/institutionnel-logo.png" alt="Logo Dawan">
            <h1> DP - ${dossierProjet.nom}</h1>													
        </div>                                                                    <hr size="9px" width="75%"/>  
        
        
        <h1 class="projet">
        	<#if dossierProjet.projets?has_content>
			  <u> Projet : ${dossierProjet.projets.nom}</u>
				<#else>
				  Le Nom du Projet est vide !
			</#if>	
        </h1>                                                           
        

        <h2 class="h2"><u>Informations Dossier Projets :</u></h2>                                                                 
            <div class="divtitre">
                <p>
                    <#if dossierProjet.infoDossierProjets[0]?has_content>
					  ${dossierProjet.infoDossierProjets[0]}
						<#else>
						  Informations non renseignées !
					</#if>
                </p>
            </div>

        <h2 class="h2"><u>Contenu Dossier Projets :</u></h2>
            <div class="divtitre">
                <p>
                	<#if dossierProjet.contenuDossierProjets[0]?has_content>
					  ${dossierProjet.contenuDossierProjets[0]}
						<#else>
						  Informations non renseignées !
					</#if>
                </p>                    
            </div>

        <h2 class="h2"><u>Resumer Dossier Projets :</u></h2>                                                                   
            <div class="divtitre">
                <p>
                	<#if dossierProjet.resumeDossierProjets[0]?has_content>
						${dossierProjet.resumeDossierProjets[0]}
						<#else>
						  Informations non renseignées !
					</#if>
                </p>
            </div>  

        <h2 class="h2"><u>Annexe Dossier Projets :</u></h2>                         
            <div class="divtitre">
                <p>
                    <h3>Liste des Pièces jointes : </h3>
                    <ul>
                    	<#if dossierProjet.annexeDossierProjets[0]?has_content>
                    		<li>${dossierProjet.annexeDossierProjets[0]}</li>
							<#else>
							  Informations non renseignées !
						</#if>
                    </ul>
                </p>
            </div>      
            
    </div>

</body>
</html>