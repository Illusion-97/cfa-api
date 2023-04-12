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
        	<#if dossierProjet.projets.nom?has_content>
			  <u> Projet : ${dossierProjet.projets.nom}</u>
				<#else>
				  Le Nom du Projet est vide !
			</#if>	
        </h1>                                                           
        

        <h2 class="h2"><u>Informations Dossier Projets :</u></h2>                                                                 
            <div class="divtitre">
                <p>
                    <#if dossierProjet.infoDossierProjets[0].information_projet?has_content>
					  ${dossierProjet.infoDossierProjets[0].information_projet}
						<#else>
						  Informations non renseignées !
					</#if>	
                    
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </p>
                </p>
            </div>

        <h2 class="h2"><u>Contenu Dossier Projets :</u></h2>
            <div class="divtitre">
                <p>
                	<#if dossierProjet.contenuDossierProjets[0].contenu_projet?has_content>
					  ${dossierProjet.contenuDossierProjets[0].contenu_projet}
						<#else>
						  Informations non renseignées !
					</#if>
                    
                    <p>
                        Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
                        The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.
                        On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.
                    </p>
                </p>                    
            </div>

        <h2 class="h2"><u>Resumer Dossier Projets :</u></h2>                                                                   
            <div class="divtitre">
                <p>
                	<#if dossierProjet.resumeDossierProjets[0].resume_projet?has_content>
						${dossierProjet.resumeDossierProjets[0].resume_projet}
						<#else>
						  Informations non renseignées !
					</#if>
                   
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                </p>                
            </div>  

        <h2 class="h2"><u>Annexe Dossier Projets :</u></h2>                         
            <div class="divtitre">
                <p>
                    <h3>Liste des Pièces jointes : </h3>
                    <ul>
                    	<#if dossierProjet.annexeDossierProjets[0].pieceJointe?has_content>
                    		<li>${dossierProjet.annexeDossierProjets[0].pieceJointe}</li>
							<#else>
							  Informations non renseignées !
						</#if>
                    </ul>
                </p>
            </div>      
            
    </div>

</body>
</html>