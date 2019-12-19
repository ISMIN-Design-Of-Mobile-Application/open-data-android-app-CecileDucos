
## [Rescencement établissement scolaires français]  
  
Codé par :   
 - [Cécile] [DUCOS]   
 - [Hossein] [HASHEMI]   

## Description  
  
URL des données : [https://data.opendatasoft.com/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur%40mesr]  
  
Cette application regroupe les principaux établissements d'enseignement supérieur français et leurs caractéristiques.\n\n

Pour chaque établissement, nous affichons au clic son nom, son adresse, son type et son site internet.\n\n

Tous les établissements sont affichés sur une carte et nous pouvons également accéder au détail de chacun au clic sur le marker.\n\n

Un bouton permet de rafraichir les données.

Application réalisé dans le cadre d'un projet du cours Android de la formation ISMIN de l'Ecole des Mines de Saint Etienne par Cécile Ducos et Hossein Hashemi.\n\n   
  
## Consignes  
  
Lien vers le sujet : https://docs.google.com/presentation/d/1mwu2xx7_qfCZDfsRxseC94n7oBGYfhw-9xIftaTDbzk/edit#slide=id.p97  
  
### But du projet  
  
 - Le but du projet est de réaliser une application permettant de visualiser une série de données OpenData  
 - Les données devront être récupérées sur un serveur distant et affichées dans une liste et sur une carte  
 - Un clique sur un élément de la liste ou sur un marker de la carte permet d’accéder à un écran présentant le détail de l’élément  
 - Un écran présentera des informations générales sur les données récupérées  
  
### Exigences    
- [ ] Format des données OpenData :   
	 - Format Json  
	 - Avec un champ correspondant à l’url d’une image  
	 - Avec un champ correspondant à une position  
 - [ ] Application composée au minimum de 3 Fragment et 2 Activity  
 - [ ] Une Actionbar sera présente et permettra de rafraîchir les données récupérées et affichées
 
 ## Bonus
 
 - Amélioration de l’expérience utilisateur :
    - Clustering des markers sur la carte en fonction du niveau de zoom
    - Mise en place d’un système de recherche/filtre sur la liste affichée
 
 - Enrichissements techniques :
    - Mise en place d’une base de données locale pour afficher la liste d’élément en mode hors connexion
    - Utilisation de LiveData ou d’Observable pour la récupération de données dans la BDD
 
