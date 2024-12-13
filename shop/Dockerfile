FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/shop-0.0.4-SNAPSHOT.jar app.jar
COPY Wallet_CKVY7SUY33QB5HUU /app/oracle_wallet
EXPOSE 8081

CMD [ "java", "-jar", "app.jar" ]