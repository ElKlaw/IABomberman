Projet Formation Entreprise (1 semaine)
=================

Création d'une IA pour un jeu de bomberman en multijoueur (4 joueurs par partie)

Lancement d'une partie
Les parties se lancent automatiquement, toutes les minutes, lors de chacune des sessions de l'école du développeur. Votre bot va donc vivre plusieurs sessions, faites en sorte qu'il reste parmi les meilleurs !

Une partie démarre, les joueurs sont tirés au hasard. À chaque tour, DevArena enverra le contexte (méthode POST) de jeu aux joueurs sélectionnés. Ces derniers auront alors 300ms pour y répondre avec l'action que devra entreprendre le bot parmi : MOVE top, MOVE right, MOVE bottom, MOVE left, STAY ou BOMB

En cas de non-réponse, de réponse trop tardive ou d'action erronée, votre bot sera alors éliminé de la partie.

Régles détaillées
=================

Langage de votre IA
-----------------
Ce coding challenge ne porte aucune contrainte de langage, vous pouvez choisir le langage, les frameworks et les outils de votre choix pour construire votre bot.

Seules figures imposées : il devra pouvoir être déployé sur https://ops.norsys.fr , le code sera stocké sur https://git.norsys.fr et analysé par https://sonarqube6.norsys.fr

Auto-Inscription
-----------------
Une fois votre bot déployé sur le cloud Norsys (https://ops.norsys.fr), il devra s'auto-enregistrer :

Envoyer une requête HTTP Post sur l'URL http://dev-arena.app.norsys.fr/api/challenge/register

  POST http://dev-arena.app.norsys.fr/api/challenge/register
  Content-Type: application/json
  {
    name : <--*obligatoire* Un nom Unique pour votre bot-->,
    endpoint : <--*obligatoire* L'url à appeler pour questionner votre bot : uniquement *-dev-arena.app.norsys.fr-->,
    sonarProjectKey : <--La clef projet de votre bot sous sonar (facultatif)-->,
    sonarToken : <--Un token d'accès à votre projet généré par Sonar (facultatif)-->,
    promotionName : <--*obligatoire*le libelle de votre promotion sous le format : mois-annee (exemple : juillet-2018)-->,
    avatar : <--*obligatoire* Votre avatar en Base64-->
  }
                
Exemple :

  POST http://dev-arena.app.norsys.fr/api/challenge/register
  Content-Type: application/json
  {
    name : "DummyBot",
    endpoint : "http://dummy-devArena.app.norsys.fr/api/combat/exec",
    sonarProjectKey : "fr.norsys.pirat.fboissier:dev-arena-bot",
    sonarToken : "61cc0105c6f365aee075f710b28e49a85c988908",
    promotionName : "juillet-2018",
    avatar : "iVBORw0KGgoAAAANSUh ......"
  }
                
Réponse :
Code HTTP 200 + trame JSON correspondant au compétiteur enregistré.

Lancement d'une partie
-----------------
Les parties se lancent automatiquement, toutes les minutes, lors de chacune des sessions de l'école du développeur. Votre bot va donc vivre plusieurs sessions, faites en sorte qu'il reste parmi les meilleurs !

Une partie démarre, les joueurs sont tirés au hasard. À chaque tour, DevArena enverra le contexte (méthode POST) de jeu aux joueurs sélectionnés. Ces derniers auront alors 300ms pour y répondre avec l'action que devra entreprendre le bot parmi : MOVE top, MOVE right, MOVE bottom, MOVE left, STAY ou BOMB

En cas de non-réponse, de réponse trop tardive ou d'action erronée, votre bot sera alors éliminé de la partie.

Le contexte de la partie contient : Trame d'exemple


Le format de réponse attendue est ( Content-Type: application/json ) :

  {
    "message" : "MOVE right"
  }
                
Niveau 1
-----------------
Le niveau 1 démarre doucement :

* Votre Bot ne peut poser qu'une 1 bombe à la fois
* Le rayon d'explosion de la bombe fait 1 case
* La bombe explose 3 tours après avoir été posée
* Les bombes ne peuvent pas vous tuer

En 200 tours, faites exploser un maximum de caisses en bois.

Points : 1 pt / caisse
Le vainqueur est le bot qui possède le plus de points à l'issue des 200 tours ou qu'il n'y ait plus de caisse sur la carte.

Niveau 2
-----------------
Dans le niveau 2, tout est dans les bonus : 

* Ajout de bombe / augmentation du rayon d'explosion. Ils apparaissent de manière aléatoire lorsqu'une caisse explose.
* Les bombes ne s'enchaînent pas.

En 200 tours, faites exploser un maximum de caisse en bois.

Points : 1 pt / caisse
Points : 1 pt / bonus
Le vainqueur est le bot qui possède le plus de points à l'issue des 200 tours ou qu'il n'y ait plus de caisse sur la carte.

Niveau 3
-----------------
Dans le niveau 3, le danger approche : 

* les bombes peuvent maintenant vous tuer

En 200 tours, éliminez vos concurrents.

Points : 1 pt / caisse
Points : 1 pt / bonus
Points : 5 pts / morts
Points : 5 pts pour les survivants
Le vainqueur est le bot qui possède le plus de points à l'issue des 200 tours ou qu'il n'y ait plus de caisse sur la carte.
