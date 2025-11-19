# Récupérer l'image du Docker Hub en local
docker pull image:tag

# Exécuter un nouveau container à partir d'une image
docker run --name nom_container image:tag

# Exécuter un nouveau container à partir d'une image, en changeant la commande de base
docker run --name nom_container image:tag commande
docker run --name nom_container image:tag bash

# Même chose avec le mode interactif
docker run -it --name nom_container image:tag bash

# Exécuter un nouveau container avec une translation de port (redirigé le port local vers le port du container)
docker run -p portlocal:portcontainer --name nom_container image:tag
docker run -p 80:80 --name nom_container image:tag

# Exécuter un nouveau container avec un binding de répertoire (volume)
docker run -v chemin_repertoire_local:chemin_repertoire_container --name nom_container image:tag
docker run -v D:/repertoire/du/systeme:/chemin/dans/container --name nom_container image:tag

# Exécuter un nouveau container avec une connexion à un réseau
docker run --network nom_reseau --name nom_container image:tag

# Arrêter un container en cours d'exécution
docker stop nom_contaier
docker stop id_contaier

# Démarrer un container arrêté
docker start nom_container
docker start id_container

# Redémarrer un container en cours d'exécution
docker restart nom_container
docker restart id_container

# Afficher toutes les informations d'un container
docker inspect nom_container
docker inspect id_container