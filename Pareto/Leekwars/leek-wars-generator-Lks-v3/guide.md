# Python :

Les fichiers pythons à la racine de ce repertoire (autre que script.py) sont utilisés par script.py pour calculer les scores des poireaux 
Le fichier script.py écrit dans tmps > tmp les configurations des combats puis envoie au processus java les identifiants des fichiers écrits 
Petit à petit il recoit les résultats les traitent puis les envoies au serveur commun 

# Tmps :

Contient les répertoires temporaires stockant les adns des IAs 

# Data :

Le répertoire data contient les fichier json décrivant les armes , les bubles ect nécessaire a l'instanciation du Generator ainsi qu'a l'analyse des résultats retourner par le Generator

# Analyse Interpretation :

Ce répertoire contient les fichiers python utilisé par le script.py pour interpréter les adns 

# Server

Serveur commun utilisé pour stocker les adns et répartir les combats entre les différentes machines de notre équipe

# Genetic script data :

Contient la configuration du processus évolutif

# AI :
 
 Répertoire générer automatiquement , il contient la traduction des codes leekscript en JAVA 

# Test :

Ce répertoire contient les IAs leekscript et scénarios utilisés pour nourrir le Generator 

# Leekscript :

Ce repertoire contient le code de leekscript il contient :
    - le code de traduction .leek -> .java , 
    - la définition du langage leekscript
    - les fonctions leekscript et les extensions réalisés par notre équipe 

# src :

Le code source de leekwars-generator



