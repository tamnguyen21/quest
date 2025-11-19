# Mapping de port 9000 de la machine vers 80 du container
# Mapping du répertoire "D:/test" de la machine vers le répertoire "/usr/share/nginx/html" du container
# Le nom du container sera "thenginx"
# L'exécution se fera en arrière plan
# L'image utilisée est "nginx:latest"

docker run -d -p 9000:80 -v "D:/test":/usr/share/nginx/html --name thenginx nginx:latest

# -- OU--

# Mapping de port 9000 de la machine vers 80 du container
# Mapping du fichier "D:/test/index.html" de la machine vers le répertoire "/usr/share/nginx/html/index.html" du container
# Le nom du container sera "thenginx"
# L'exécution se fera en arrière plan
# L'image utilisée est "nginx:latest"
docker run -d -p 9000:80 -v "D:/test/index.html":/usr/share/nginx/html/index.html --name thenginx nginx:latest
