FROM williamyeh/java8

RUN mkdir /app

COPY application.yaml /app
COPY weather.jar /app
COPY run.sh /app

WORKDIR /app

EXPOSE 8082

CMD ["/bin/bash", "run.sh"]

