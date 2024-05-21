**Spécifications**

<img title="a title" alt="Alt text" src="https://github.com/jolehuit/tekki/blob/master/DiagrammeFlux1.png">

***Flux Utilisateur***

1. *Page d’Accueil :*
   - Le joueur clique sur "Commencer".
   - L'application initialise le compteur à 150 points pour le joueur.
   - L'application sélectionne un personnage célèbres aléatoire et filtre les questions et ses reponses 

2. *Page des Questions :*
   - Le joueur voit une liste déroulante de questions.
   - Le joueur sélectionne une question et clique sur "Je pose la question".
   - L'application déduit 10 points et affiche la réponse.
   - Le joueur clique sur "Je veux deviner Tekki" pour passer à la phase de devinette.

3. *Page Deviner :*
   - Le joueur voit une liste déroulante de 10 personnages.
   - Le joueur sélectionne un personnage et clique sur "Je devine !".
   - Si incorrect, l'application déduit 20 points et retire le personnage de la liste.
   - Si le score atteint 0 points, le joueur est redirigé vers la page résultat.
   - Si correct, le joueur est redirigé vers la page résultat.

4. *Page Résultat :*
   - L'application affiche si le joueur a gagné ou perdu et le score final.
   - Le joueur peut cliquer sur "Rejouer" pour recommencer le jeu.
  
     

  

### Spécifications Fonctionnelles Détaillées

#### Page d’Accueil

- **Joueur :**
  - Cliquer sur le bouton "Commencer".

- **Application :**
  - Lorsque le joueur clique sur "Commencer" :
    - Initialiser un compteur de points à 150 points pour le joueur.
    - Choisir aléatoirement un personnage célèbre parmi 100 personnages existant dans la base de données.
    - Filtrer 20 questions et réponses ciblées sur ce personnage depuis la base de données.
    - Rediriger le joueur vers la page des questions.

#### Page de Jeu (Page des Questions)

- **Application :**
  - Afficher les 20 questions filtrées sous forme d'éléments dans une liste déroulante.
  - Afficher le compteur de points initialisé à 150 points.
  - Afficher une zone de texte vide pour la réponse.
  - Afficher un bouton "Je pose la question".
  - Afficher un bouton "Je veux deviner Tekki".

- **Joueur :**
  - Sélectionner une question dans la liste déroulante.
  - Cliquer sur le bouton "Je pose la question".

- **Application :**
  - Lorsqu'une question est sélectionnée et le bouton "Je pose la question" est cliqué :
    - Déduire 10 points du compteur de points.
    - Afficher la réponse correspondante à la question sélectionnée dans la zone de texte.
    - Mettre à jour l'affichage du compteur de points avec le nouveau score.

- **Joueur :**
  - Cliquer sur le bouton "Je veux deviner Tekki".

- **Application :**
  - Lorsqu'il clique sur le bouton "Je veux deviner Tekki" :
    - Rediriger le joueur vers la page deviner.

#### Page de Jeu (Page Deviner)

- **Application :**
  - Afficher une liste déroulante contenant le personnage choisi initialement et 9 autres personnages choisis aléatoirement.
  - Afficher le compteur de points actuel.
  - Afficher un bouton "Je devine !".

- **Joueur :**
  - Sélectionner un personnage dans la liste déroulante.
  - Cliquer sur le bouton "Je devine !".

- **Application :**
  - Lorsqu'un personnage est sélectionné et le bouton "Je devine !" est cliqué :
    - Si la réponse est incorrecte :
      - Déduire 20 points du compteur de points.
      - Retirer le personnage sélectionné de la liste déroulante.
      - Mettre à jour l'affichage du compteur de points avec le nouveau score.
      - Si le score atteint 0 point, rediriger le joueur vers la page résultat avec un message de défaite.
    - Si la réponse est correcte :
      - Rediriger le joueur vers la page résultat avec un message de victoire.

#### Page Résultat

- **Application :**
  - Afficher un message indiquant si le joueur a gagné ou perdu.
    - Si le joueur a gagné : "Bravo ! Tu as deviné correctement !"
    - Si le joueur atteint le score égale à 0 : "Désolé, tu as perdu. Ton score est de zéro."
    - Si le joueur a posé toutes les questions : "Désolé, tu as perdu. Il ne reste plus de questions"
  - Afficher le nombre de points restants.
  - Afficher un bouton "Réjouer".

- **Joueur :**
  - Cliquer sur le bouton "Rejouer".

- **Application :**
  - Lorsqu'il clique sur le bouton "Réjouer" :
    - Rediriger le joueur vers la page d'accueil.






