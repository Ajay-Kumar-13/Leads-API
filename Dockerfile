FROM eclipse-temurin:latest

# Create a directory for certs
RUN mkdir -p /opt/certs

COPY global-bundle.pem /opt/certs/global-bundle.pem

# Import into JVM truststore
RUN keytool -importcert -trustcacerts \
    -alias aws-rds \
    -file /opt/certs/global-bundle.pem \
    -keystore $JAVA_HOME/lib/security/cacerts \
    -storepass changeit \
    -noprompt

# Copy application
COPY build/libs/leads-0.0.1-SNAPSHOT.jar /tmp/leads-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/tmp/leads-0.0.1-SNAPSHOT.jar"]
