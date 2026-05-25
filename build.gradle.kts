plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.crm"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //	Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Reactive database access
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.postgresql:r2dbc-postgresql:1.0.4.RELEASE")

    // JDBC ONLY for schema.sql + data.sql initialization
    runtimeOnly("org.postgresql:postgresql")

    //	Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    //  AWS
    implementation("software.amazon.awssdk:sts")
    implementation(platform("software.amazon.awssdk:bom:2.25.28"))
    implementation("software.amazon.awssdk:secretsmanager")
    implementation("io.awspring.cloud:spring-cloud-aws-starter-secrets-manager:3.1.1")

    //  Flyway
    implementation("org.flywaydb:flyway-core:11.20.0")
    implementation("org.flywaydb:flyway-database-postgresql")

    //	JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
