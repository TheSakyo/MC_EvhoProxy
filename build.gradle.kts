plugins { java }

group = "fr.TheSakyo.EvhoProxy"
version = "latest"

java { toolchain.languageVersion.set(JavaLanguageVersion.of(17)) }

tasks.withType<JavaCompile> { options.encoding = "UTF-8" }

repositories {
  mavenCentral()
  mavenLocal()
  maven(url = "https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.jetbrains:annotations:23.0.0")


    compileOnly("io.github.waterfallmc:waterfall-api:1.19-R0.1-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")

    implementation(fileTree("src/libs") { include("*.jar") })
}