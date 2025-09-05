# ---- Estágio de Build ----
# Usamos uma imagem oficial do Maven com Java 17 para compilar nosso projeto.
# 'AS build' dá um nome a este estágio, para que possamos nos referir a ele mais tarde.
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache de camadas do Docker.
# Se as dependências não mudarem, o Docker não vai baixá-las novamente.
COPY pom.xml .

# Copia o resto do código-fonte da aplicação.
COPY src ./src

# Executa o comando do Maven para limpar, compilar e empacotar a aplicação em um .jar.
# '-DskipTests' pula a execução dos testes para acelerar o processo de build no deploy.
RUN mvn clean package -DskipTests


# ---- Estágio de Execução ----
# Usamos uma imagem leve do OpenJDK 17, apenas com o necessário para rodar a aplicação.
# Isso torna nossa imagem final muito menor e mais segura.
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container final.
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio de 'build' para o nosso container final.
# Renomeamos para 'app.jar' para facilitar a execução.
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080, que é a porta padrão do Spring Boot, para fora do container.
EXPOSE 8080

# Define o comando que será executado quando o container iniciar.
# Ele simplesmente executa o arquivo .jar da nossa aplicação.
ENTRYPOINT ["java", "-jar", "app.jar"]
