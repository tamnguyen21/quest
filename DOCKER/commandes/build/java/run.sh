# Pour exécuter le container
docker run -d -p 8081:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://quest-mysql:3306/projet_quest -e SPRING_DATASOURCE_PASSWORD=root --network quest --name quest-boot ajc/quest:boot
