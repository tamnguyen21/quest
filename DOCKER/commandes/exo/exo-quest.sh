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


# Démarrer un container eclipse-temurin
# > En mode interactif, avec la commande bash
# > Avec un nom : "quest-boot"
# > Binder le port 8080 du container sur un port local
# > PAS en mode background
# > Sur le réseau "quest"
# > En mapping le répertoire projet du "quest-boot" sur /app
docker run -it --name quest-boot -v "D:/quest-boot":/app -p 8081:8080 --network quest eclipse-temurin:21-jdk bash

cd /app
./mvnw clean package
java -jar -Dspring.datasource.url=jdbc:mysql://quest-mysql:3306/projet_quest -Dspring.datasource.username=root -Dspring.datasource.password=root target/quest-boot-0.0.1-SNAPSHOT.jar

# Créer un nouveau container mysql (image mysql, tag 8.0.44-debian)
# > Sans mapping de port
# > En arrière plan
# > Avec la variable d'environnement MYSQL_ROOT_PASSWORD avec la valeur "root"
# > Sur le réseau "quest"
# > Avec le nom "quest-mysql"
# > Avec un mapping de volume : copier le fichier "quest-init.sql" ci-joint dans "/docker-entrypoint-initdb.d/init.sql"

# Puis, se connecter au client MySQL du container puis
# > Vérifier que la base projet_quest existe
# > Sélectionner cette base avec l'instruction USE projet_quest
# > Voir la liste des tables avec SHOW TABLES;
# > Faire un select de la table matiere pour vérifier s'il en existe bien une

docker run -d -e MYSQL_ROOT_PASSWORD=root --network quest --name quest-mysql -v "D:/test/quest-init.sql":/docker-entrypoint-initdb.d/init.sql mysql:8.0.44-debian

docker exec -it quest-mysql mysql -uroot -p

SHOW DATABASES;
USE projet_quest;
SHOW TABLES;
SELECT * FROM matiere;

