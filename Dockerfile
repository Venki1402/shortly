# Official Tomcat image with Java 17 (LTS)
FROM tomcat:9.0-jdk17-temurin

# Remove default Tomcat apps to reduce attack surface
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy our WAR as ROOT application
COPY target/shortly.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
