# 1단계: 빌드 환경
FROM gradle:7.4.2-jdk17 AS builder

WORKDIR /app

# Gradle wrapper와 설정 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew
RUN ./gradlew dependencies

# 소스 복사 및 빌드 (서브모듈 포함) 📦
COPY . .
RUN ./gradlew bootJar --no-daemon

# 2단계: 실행 환경 (슬림한 이미지)
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
COPY --from=builder /app/src/main/resources/config/ /app/config/

# 실행
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
