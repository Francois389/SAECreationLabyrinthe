## 1 Objectifs des SAE
En partant d'une situation pratique et concrète qu'il sera nécessaire de modéliser, les objectifs visés par ce projet sont le développement en équipe d'un logiciel permettant de résoudre un problème donné   
 ➡ en modélisant et développant selon l'approche étudiée en R2.01-Développement Orienté Objets  
 ➡ en adaptant des algorithmes dérivés de ceux étudiés dans les ressources R2.07-Graphes et R2.09-Méthodes numériques  
 ➡ en mettant en œuvre des principes de gestion abordés en R2.10-Gestion de projet.  
 
Dans le cadre du BUT Informatique, ce travail se focalisera principalement sur 4 compétences : 
- compétence 1 : développer des applications informatiques
- compétence 2: optimiser des applications informatiques,
appréhender et construire des algorithmes
- compétence 5: conduire un projet
- compétence 6: travailler en équipe
    Les apprentissages visés sont :

  + Appréhender les besoins du client et de l'utilisateur
  + Identifier les acteurs et les différentes phases d'un cycle de développement Analyser un problème avec méthode (modélisation Orientée Objets)
  + Formaliser et mettre en œuvre des outils mathématiques pour l'informatique. Comparer des algorithmes pour des problèmes classiques
  Elaborer et implémenter des conceptions simples
  + Identifier les statuts, les fonctions et les rôles de chaque membre d'une équipe
  + Acquérir les compétences interpersonnelles pour travailler en équipe
  + Mettre en place une conduite de projet
  

## 2 Organisation
### 2.1 Responsabilités des participants
- Maitrise d'ouvrage (MOA) représentant le besoin client :  
Frédéric Barrios, Casimir Kam, Laurent Wehrlé
  
- Maitrise d'œuvre (MOE):
Groupe de 4 ou 5 étudiants de BUT1

- Conseillers ressources techniques :  
Frédéric Barrios, Casimir Kam, Laurent Wehrlé

- Contrôle qualité projet (contrôle du plan projet et de la gestion de la MOE) :  
Frédéric Barrios

### 2.2 Constitution des groupes de Maitrise d'œuvre
Les travaux demandés sont à réaliser en groupe de 3 à 5 étudiants.   
Les groupes doivent être composés au sein de chaque groupe de TP.  
La composition des groupes est laissée libre mais il doit y avoir au maximum 3 groupes par TP.

### 2.3 Calendrier prévisionnel

[image]



Dates prévisionnelles de la soutenance finale : mardi 13 juin 2023 et mercredi 14 juin 2023.


## 3 Présentation du projet : logiciel de jeu du labyrinthe
### 3.1 Représentation simplifiée d'un labyrinthe

Un labyrinthe, du grec laburinthos, est un vaste édifice composé de très nombreuses pièces disposées de telle manière qu'on n'en trouve que difficilement l'issue.  

Afin de simplifier la modélisation pour notre logiciel, nous allons considérer qu'un labyrinthe peut être représenté par un ensemble de pièces carrées connexes de façon à ce que toute pièce puisse être atteinte par cheminement depuis n'importe quelle autre.  

De plus pour faciliter la représentation sur une IHM, nous ne représenterons que la vue de dessus du labyrinthe avec un système de coordonnées orthogonal unitaire de telle façon que tout labyrinthe puisse se représenter à l'intérieur d'un rectangle de longueur L et de hauteur H:
            
L=7 H=5
(ya pas les murs j'y arrive po)
  
[image]


Chaque pièce carrée du labyrinthe peut être identifiée par ses coordonnées (colonne, ligne).  

La bordure séparant 2 pièces est soit un "mur" **|** (infranchissable) soit une "porte" '(un trou) traversable.  
Exemples: la pièce (2,2) est entourée d'une porte la séparant de la pièce (2, 1) et de 3 murs l'isolant des pièces (1, 2) (3, 2) et (2, 3)


L'ensemble forme un labyrinthe dans lequel on peut imposer une entrée et une sortie : Exemple: entrée au carré E(0, 0) et sortie au carré S(6, 2).  

Le jeu consiste alors à déterminer un parcours → de pièces traversées (si possible le plus court possible en nombre de pièces traversées) entre l'entrée et la sortie.  
Sur l'exemple précédent :

[image]


## 3.2 Restrictions apportées à la représentation (pour simplifier les problématiques)

### 3.2.1 Première restriction
Le labyrinthe ne doit pas contenir des ilots de cellules inaccessibles c'est-à-dire que depuis une cellule quelconque il doit être possible de rejoindre n'importe quelle autre cellule du labyrinthe rectangulaire en empruntant des portes.  

Exemple de labyrinthe incorrect: Les pièces colorées en jaune ne sont pas accessibles depuis E
(jaune = X)

[image]

Cet exemple pourrait en fait s'assimiler à des plans de labyrinthe non rectangulaires tels que :

[image]

Le traitement de tels « plans de labyrinthes » pourrait faire l'objet d'une version plus avancée de notre logiciel... Pour nos premières versions, nous nous en tiendrons à notre plan rectangulaire avec toutes les pièces accessibles depuis l'entrée.
fin