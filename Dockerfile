FROM openjdk11-alpine

ENV APP_HOME /inova/app

ENV APP_NAME .jar

RUN mkdir -p $APP_HOME

ADD target/*.jar ${APP_HOME}/${APP_NAME}

WORKDIR $APP_HOME

ENTRYPOINT java -jar -Xms256M -Xmx256M -XX:+ExitOnOutOfMemoryError -XX:+CrashOnOutOfMemoryError -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom ${APP_NAME}