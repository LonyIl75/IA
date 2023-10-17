# AICode :

Classe contenant le code du fichier java de l'IA (javacode:string) ainsi que le lines associé au fichier java (linesFile : string ) , les lines indique la localisation de chaque définition (par exemple fonction x débute ligne z fini ligne y ) dans le fichier (pas sûr)

# AIFile :

Décrit le fichier java associé à l'IA :
{
    private String path; // chemin vers le .leek
	private String code; // code .leek 
	private Folder folder; // dossier qui contient le .leek
	private int owner; // pas utilisé toujours ==0 en local , je crois que c'est l'id de l'utilisateur leekwars dans la base de donnée du site , spécifier par ai_owner dans le scenario 
	private int id; 
	private long timestamp; // temps du processus de compilation
	private int version; // version de leekscript , la compilation/traduction leek -> java est différente selon les versions car toutes les fonctions n'ont pas les mêmes définition ni ne sont disponibles 
	private ArrayList<AnalyzeError> errors = new ArrayList<>(); // erreur lors de la compilation 
	private AICode compiledCode; // code compiler en java
	private String clazz; // AI_${id} chaque code compiler étends la classe EntityAI et donc AI et on les différencie par leur ${id}
	private String rootClazz; // classe de base : AI 
	private ArrayList<Token> tokens = new ArrayList<Token>(); // l'ensemble des tokens reconnu durant readCode > compile > *newWord pour chaque mot reconnu 
	private Token endOfFileToken = new Token(WordParser.T_END_OF_FILE, "", new Location(this)); 
}
Durant le [build](https://github.com/leek-wars/leek-wars-generator/blob/27f8e0fde04d0c32c913ae2668e146d2314a2c41/src/main/java/com/leekwars/generator/fight/Fight.java#L359) du fight :

Si ce n'est pas déjà fait* la traduction du .leek en java sera écrit (AIFile) puis  compilé en .class et les .class seront [écrit dans le répertoire ai](https://github.com/leek-wars/leekscript/blob/master/src/main/java/leekscript/compiler/JavaCompiler.java#L162) à la racine du projet .Ensuite java [chargera](https://github.com/leek-wars/leekscript/blob/master/src/main/java/leekscript/compiler/JavaCompiler.java#L180) l'exécutable .class dans un objet et finalement on créera une instance de la classe chargé en guise d'AI .

Cette AI sera ensuite associé à [chaque Entity](https://github.com/leek-wars/leek-wars-generator/blob/27f8e0fde04d0c32c913ae2668e146d2314a2c41/src/main/java/com/leekwars/generator/fight/Fight.java#L359) du fight .

*: les .class sont chargés et mis en cache pour éviter les surcouts [ici](https://github.com/leek-wars/leekscript/blob/master/src/main/java/leekscript/compiler/JavaCompiler.java#L64) et [ici_2](https://github.com/leek-wars/leekscript/blob/master/src/main/java/leekscript/compiler/JavaCompiler.java#L78)


# Folder

Il est appelé pour manipuler/chercher les [fichiers](https://github.com/leek-wars/leekscript/blob/master/src/main/java/leekscript/compiler/Folder.java#L95) et les [repertoires](https://github.com/leek-wars/leekscript/blob/master/src/main/java/leekscript/compiler/Folder.java#L114) notamment celui décrit dans le scenario ai_path  et ai_folder resp , ils dépendent des (resolvers)[https://github.com/leek-wars/leekscript/tree/master/src/main/java/leekscript/compiler/resolver] 

# Resolvers

Les resolvers se partagent en 2 types "Native et Resource" FileSystem ils dépendent de java.nio.file et sont utilisés pour résoudre et retourner le code d'un fichier dans un objet AIFile 
Ils ont changés de noms entre la version V3 et V4 de leekscript 
Ils interviennent [EntityAI > resolve](https://github.com/leek-wars/leek-wars-generator/blob/40c77c23339b1e30a2eebf098c322012c386742b/src/main/java/com/leekwars/generator/fight/entity/EntityAI.java#L107) et servent à [set le AIFile](https://github.com/leek-wars/leek-wars-generator/blob/27f8e0fde04d0c32c913ae2668e146d2314a2c41/src/main/java/com/leekwars/generator/Generator.java#L132) associé au IA d'un combat lors de lancement du scénario par le Generator .



