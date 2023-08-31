# Utiliser l'image de base Java
FROM openjdk:17-jdk

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le JAR Spring Boot dans le conteneur
COPY target/cours-service-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel votre application Spring Boot écoute
#EXPOSE 8089

# Commande pour exécuter l'application
#CMD ["java", "-jar", "app.jar"]
CMD ["sh", "-c", "sleep 30 && java -jar app.jar"]