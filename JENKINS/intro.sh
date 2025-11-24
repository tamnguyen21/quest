# A partir de l'image ajc/jenkins buildée précédemment, et après avoir créé le réseau devops (vérifier)
docker run --name jenkins -d -p 8080:8080 --network devops -v /var/run/docker.sock:/var/run/docker.sock ajc/jenkins

# S'y connecter en bash pour vérifier les droits
docker exec -it jenkins bash

# Exécuter la commande suivante, si elle ne fonctionne pas car pas de droit suffisant, se déconnecter (Ctrl+D) ou "exit"
docker ps

# Se reconnecter en root
docker exec -it -u root jenkins bash

# Vérifier ici si la commande fonctionne, si ce n'est pas le cas, un problème existe soit dans le run, soit dans l'image
docker ps

# Changer les droits sur le fichier, ajouter le groupe "docker"
chgrp docker /var/run/docker.sock

# Se déconnecter et se reconnecter en utilisateur jenkins
docker exec -it jenkins bash

# Pour vérifier de nouveau
docker ps

# Générer la clé SSH pour l'associer au compte Github
ssh-keygen -t ed25519

# Ajouter github.com dans la liste des hôtes connus - attention c'est obligatoire pour que Jenkins puisse récupérer depuis un build !
ssh-keyscan github.com >> ~/.ssh/known_hosts

# Copier la clé publiquer et l'associer au compte Github
cat ~/.ssh/id_ed25519.pub

# Récupérer la clé privée et la garder de côté pour le moment
cat ~/.ssh/id_ed25519

# On peut vérifier si la connexion SSH est OK avec github.com
ssh -T git@github.com

# On en profite pour récupérer le mot de passe admin de Jenkins
cat /var/jenkins_home/secrets/initialAdminPassword

# Aller ensuite sur Jenkins (à adapter selon le binding de port)
http://localhost:8080/
