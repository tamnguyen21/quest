# Créer le réseau "quest"
docker network create quest

# Démarrer un container mysql
# > Avec la variable d'environnement MYSQL_ROOT_PASSWORD qui aura la valeur "root"
# > En mode deamon
# Avec le nom de container "quest-mysql"
# Qui utilise l'image mysql:8.0.44-debian

docker run -d -e MYSQL_ROOT_PASSWORD=root --name quest-mysql mysql:8.0.44-debian

# Connecter ce container au réseau quest
docker network connect quest quest-mysql

# Initialiser une base de donnée "projet_quest"
# On commence par se connecter au client mysql du container quest-mysql
docker exec -it quest-mysql mysql -uroot -p

# Une fois connecté, on affiche la liste des bases
SHOW DATABASES;

# On crée la base "projet_quest"
CREATE DATABASE projet_quest;

# On vérifie la creation
SHOW DATABASES;
