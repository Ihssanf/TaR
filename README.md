# Projet Gestion de l'Entreprise

Ce projet est une application Java qui utilise JDBC pour gérer les données des développeurs et des managers dans une entreprise. Il se connecte à une base de données MySQL et permet la création, mise à jour, et récupération de développeurs et de managers, ainsi que la gestion des relations hiérarchiques entre eux.

## Fonctionnalités
- Connexion à une base de données MySQL.
- Gestion des développeurs et des managers (création, mise à jour, récupération).
- Affichage de la hiérarchie des employés (directeur général, managers, développeurs).
- Attribution des développeurs à des managers et des managers à un directeur général.
- Vérification de l'existence d'un employé avant sa création pour éviter les doublons.

## Structure du projet
Le projet est structuré en plusieurs packages, chacun ayant un rôle spécifique :
- **`ma.projet.beans`** : Contient les classes `Personne`, `Developpeur`, et `Manager` représentant les employés de l'entreprise.
- **`ma.projet.connexion`** : Gère la connexion à la base de données MySQL.
- **`ma.projet.dao`** : Interface générique pour les opérations CRUD.
- **`ma.projet.service`** : Contient les classes de service pour la gestion des développeurs (`DeveloppeurService`) et des managers (`ManagerService`).
- **`ma.projet.test`** : Contient la classe `Entreprise` avec la méthode `main` pour exécuter le programme.

## Prérequis
- *Java* 8 ou supérieur.
- *MySQL* installé et en cours d'exécution.
- Le driver JDBC pour MySQL (par exemple, `mysql-connector-java.jar`) doit être ajouté au classpath du projet.

   
 **Base de données** :
   - Créez une base de données MySQL nommée `entreprise` :
     ```sql
     CREATE DATABASE entreprise;
     ```
   - Mettez à jour les informations de connexion (URL, nom d'utilisateur, mot de passe) dans le fichier `base.properties` :
     ```properties
     jdbc.url=jdbc:mysql://localhost:3306/entreprise
     jdbc.username=root
     jdbc.password=
     jdbc.driver=com.mysql.jdbc.Driver
     ```


## Utilisation

1. **Compilez et exécutez** la classe `Entreprise` pour lancer le programme.
   
2. Le programme effectuera les opérations suivantes :
   - Création de développeurs et de managers.
   - Attribution d’un manager à chaque développeur.
   - Attribution d’un directeur général au manager.
   - Mise à jour des relations hiérarchiques entre les employés.
   - Affichage des informations sur le directeur général, les managers et les développeurs, ainsi que leurs salaires respectifs.










https://github.com/user-attachments/assets/a2881ce0-7b1e-430d-b863-eb7daf9af03a








