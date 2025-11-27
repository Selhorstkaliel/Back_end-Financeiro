#
# Dockerfile multi-stage
# 1ª etapa: usar uma imagem com Maven + JDK para compilar o projeto
# 2ª etapa: usar uma imagem menor apenas com o JDK para rodar o jar gerado
#

# Etapa de build: imagem com Maven e JDK 21
FROM maven:3.9-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container de build
WORKDIR /app

# Copia o pom.xml e baixa as dependências primeiro para aproveitar cache do Docker
COPY pom.xml .

# Baixa dependências sem compilar o projeto ainda (otimiza rebuilds)
RUN mvn -q -DskipTests dependency:go-offline

# Copia o código-fonte para o container
COPY src ./src

# Compila o projeto e gera o jar na pasta target/
RUN mvn -q -DskipTests package

# ---------------------------------------------------------------
# Etapa final (runtime): imagem enxuta só com o JDK 21
FROM eclipse-temurin:21-jdk

# Define a pasta de trabalho do container final
WORKDIR /app

# Copia o jar gerado na etapa de build para o container final
COPY --from=build /app/target/financeiro-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta padrão do Spring Boot (8080)
EXPOSE 8080

# Comando de entrada do container: executa o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
