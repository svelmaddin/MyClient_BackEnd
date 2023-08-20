FROM openjdk:17-jdk
EXPOSE 9091
ADD target/MyClientApp-0.0.1-SNAPSHOT.jar MyClientApp.jar
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail http://localhost:9091/ || exit 1
ENTRYPOINT ["java" ,"-jar" , "MyClientApp.jar"]