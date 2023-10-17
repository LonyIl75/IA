
# Attention :

Ce repertoire se doit d'appartenir au ressource du projet pour que le code java puisse y accéder :
    Sur vscode : 
        -   Add folder to java source Path sur vscode 
        -   Dans build.gradle :
            ```
            resources {
                srcDirs =["test/ai"]
            }```

# AI :

french et library compte parmis les fichiers qui sont générés lors du build (test si le generator arrive à utiliser les .leek )
basic est le script leek que nous avons utilisé pour faire les générations de notre projet 
test-extension-fonction est un script leek vérifiant que nos fonctions ont bien étaient intégrées à celle de leekscript
tagadanar est un script leek V4 que nous avons modifier pour être compatible avec une configuration des armes V3 , il sert d'exemple d'IA réellement compétitives .

# Tagadanar :

Le fichier a été mergé grâce à l'option leekwars permettant d'exporter un code de plusieurs fichier ( include ) en un seul fichier 

Ce fichier se décompose en plusieurs parties :
    - redéfinition des classes leekscript
    - redéfinition des fonctions leekscript 
    - map-danger/map-degat/combos
    - définition du concept de Consequence 
    - fichiers de poids
    - main et auto

Tout d'abord on se doit de redéfinir les classes leekscript en effet l'IA doit stocker une representation du monde leekwars , cette representation n'est rien d'autre qu'un cache de la Map , des Effets en cours , des armes disponibles ect
Pour exprimer cette représentation nous devons définir tous les concepts de leekwars ce qui revient à redéfinir les classes leekscript 
Si nous redefinissons les classes il faut redefinir également les opérateurs/fonctions qui utilise ces clases 

De nouveau concept comme la map de danger et dégat sont ajouté mais aussi la notion de combo ce sont des concepts essentiels à tout IA leekwars d'après la communauté 
Le concept de Conséquence est identique au concept d'effet il sert a calculer les altérations des statistique (par exemple les dégâts : altération des HP ) que subira une entité sur une durée supérieure ou égale à 1 tour 
Un combos est une suite d'Actions , Action: {int from , int to , int item , Consequence consequences , float score } // from et to sont des ids_fight de poireaux , item est l'id d'un item 
Le fichier de poids quand a lui (appelé scoring par la communauté) sert a évaluer la pertinence d'un combo , il est souvent l'objet d'optimisation par des algorithmes génétique comme celui proposé par Baptiste Hudyma
    
main est le main de l'IA , il lance le calcul de la meilleure action puis la joue
auto est un fichier centralisant les includes , NB : le mécanisme d'include n'est pas toujours évident surtout si le script est destiné au Generator préférez utiliser un fichier mergeant tous les fichiers 

Ce script est l'un des plus aboutis de la communauté leekwars cependant il est je pense préférable d'étendre le code java plutot que de le surclasser en leekscript 

Les éléments notables : 
    - Le système de cache sur la map
    - La définition du concept Action et Combo 
    - La re-implementation de mapPath 
    - L'update/refresh de chaque classe et l'utilisation de ses attributs par l'IA pour prendre ses décisions
    - La coloration des cases par rapport à leur danger ainsi que l'affichage des scores des combos pour faciliter le debug 

Problèmes :
    - Les effets RRS , KTP , RH sont des effets qui différents entre les versions V4-old et V4-new (en V3 vous devez les supprimer , e.g pour le Generator )
    - La marque du diable entre autre est mal pris en compte (car sont effets a changé entre la phase de release du code et actuellement )
    - Le code a été fait pour le début de la V4 ainsi il rencontre des problèmes avec l'état actuel du generator 

Pour supprimer RRS , KTP et RH :
    HiddenSingleton KILL = 17 et on supprime les lignes  ``RRS = 17`` et al 
    entityEffectType_to_stats , supprime lignes ``EFFECT_RAW_RELATIVE_SHIELD: Stats.getInstance().RRS``, et al 
    Stats static supprime lignes  ``static RRS = Stats.getInstance().RRS`` et al 
    CONVERTER supprime lignes ``EFFECT_RAW_RELATIVE_SHIELD : RRS`` et al 
    Consequences supprime lignes ``EFFECT_RAW_RELATIVE_SHIELD: 	function(effect, entitySource, entityTarget, ratioDmg, conseq){}```et al (On note que le calcul n'était pas effectuer => poids par défaut )

# Scenario :

    battleroyal est un exemple de scenario pourvu par pilow 
    test-scenario-outerFile : vérifie qu'une IA leek (V3 only) dans un autre fichier peut être chargé 
    scenario-test : scenario utiliser pour nos générations , V3 -> V4 ("ai_version":4) 




