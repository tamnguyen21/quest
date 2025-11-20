# On part de l'image nginx:latest
FROM nginx:latest

# Si on se met dans un répertoire de travail
# WORKDIR /usr/share/nginx/html

# # COPY index.html index.html
# # COPY . .
# COPY index.html .

# Si on veut exécuter des commandes pour l'image, au moment du build
# RUN apt update
# RUN apt install nano -y

# On copie le fichier index.html vers le répertoire "/usr/share/nginx/html" (dans l'image)
# COPY index.html /usr/share/nginx/html/index.html
COPY index.html /usr/share/nginx/html

# Au démarrage du container, cette commande sera exécutée - elle pourra être remplacée par autre chose car CMD est utilisé (au lieu de ENTRYPOINT)
CMD [ "nginx", "-g", "daemon off;" ]

# ENTRYPOINT [ "nginx", "-g", "daemon off;" ]

# On dit que le container va exposer le port 80 - commentaire pour Docker uniquement
EXPOSE 80
