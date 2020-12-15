FROM openjdk:8-jre-alpine AS main
COPY /build/libs/gateway-1.0.0.jar /app/application.jar
WORKDIR /app
RUN addgroup -g 1000 app-executor && \
    adduser -D -u 1000 -G app-executor app-executor
RUN chown -R app-executor:app-executor /app
USER app-executor
ENTRYPOINT exec java -XX:TieredStopAtLevel=1 -XX:+UnlockExperimentalVMOptions \
                     -XX:+UseCGroupMemoryLimitForHeap -XX:InitialRAMPercentage=50.0 \
                     -XX:MaxRAMPercentage=75.0 -XX:MinRAMPercentage=25.0 \
                     -XX:+HeapDumpOnOutOfMemoryError -jar application.jar