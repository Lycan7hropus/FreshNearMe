# Użyj obrazu bazowego Java JRE
FROM openjdk:17-jdk


# Ustaw katalog roboczy w kontenerze
WORKDIR /app

# Skopiuj fat JAR do katalogu roboczego w kontenerze
COPY build/libs/chat-service-all.jar /app/

# Otwórz port, na którym będzie nasłuchiwać aplikacja
EXPOSE 7070

# Uruchom aplikację Java z pliku JAR
CMD ["java", "-jar", "/app/chat-service-all.jar"]
