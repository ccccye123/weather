FROM williamyeh/java8

RUN mkdir /app

COPY weather.jar /app
COPY run.sh /app

WORKDIR /app

EXPOSE 8080

CMD ["/bin/bash", "run.sh"]

