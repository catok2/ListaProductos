# Utiliza una imagen base oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado por Maven en el contenedor
# El archivo JAR se genera por Maven después de hacer mvn clean package
COPY target/UtilsServices-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 en el que Spring Boot está escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boots
ENTRYPOINT ["java", "-jar", "app.jar"]