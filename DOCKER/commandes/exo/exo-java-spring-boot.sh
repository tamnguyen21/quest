# Mapping de port 9000 de la machine vers 8080 du container
# Mapping du répertoire "D:/app-docker" de la machine vers le répertoire "/app" du container
# Le nom du container sera "app-boot"
# L'exécution ne se fera PAS en arrière plan
# L'exécution entrera en interaction directe
# La commande exécutée au lancement du container sera "bash" (on sera connecté au bash au lancement)
# L'image utilisée est "eclipse-temurin:21-jdk"

docker run --name app-boot -it -v "D:/app-docker":/app -p 9000:8080 eclipse-temurin:21-jdk bash
