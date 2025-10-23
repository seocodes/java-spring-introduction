# vai construir dentro do render uma imagem com base inicial um ubuntu
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# passa tudo do diretório local pra imagem do render
COPY . .

RUN apt-get install maven -y

# faz uma limpeza e depois compila o código, executa os testes e
#instala o artefato final (como um arquivo JAR ou WAR)
RUN mvn clean install

# expoõe a porta 8080 (que roda nossa aplicação spring boot)
EXPOSE 8080

# copia o jar recém gerado no clean install para um outro arquivo app.jar (nome mais simples)
COPY --from=build /target/task-manager-1.0.0.jar app.jar

# comando que será executado quando o container iniciar
# o que tá dentro dos [] é tipo um spring-boot:run (executa o app)
ENTRYPOINT ["java", "-jar", "app.jar"]