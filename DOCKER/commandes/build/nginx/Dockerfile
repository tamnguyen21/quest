FROM nginx:latest

# WORKDIR /usr/share/nginx/html

# # COPY index.html index.html
# # COPY . .
# COPY index.html .

# COPY index.html /usr/share/nginx/html/index.html

# RUN apt update
# RUN apt install nano -y

COPY index.html /usr/share/nginx/html

CMD [ "nginx", "-g", "daemon off;" ]

# ENTRYPOINT [ "nginx", "-g", "daemon off;" ]

EXPOSE 80
