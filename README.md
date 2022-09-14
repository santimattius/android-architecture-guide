[![codecov](https://codecov.io/gh/santimattius/android-architecture-guide/branch/master/graph/badge.svg?token=4KNEHBG5ZF)](https://codecov.io/gh/santimattius/android-architecture-guide)

# Android Architecture Guide Template

This is a template to build an Android app applying good practices and using a clean architecture, you will see that the code is super decoupled with external frameworks and even with the same Android framework, this will help you to model your domain purely in Kotlin without generating external dependencies.

<p align="center">
  <img src="https://github.com/santimattius/android-architecture-guide/blob/master/screenshoot/android-clean-arch-capture.png?raw=true" alt="App Capture"/>
</p>


# Application architecture

In the following images you will see how the app is built and what its levels of abstraction are.

## Layers

<p align="center">
  <img width="500" src="https://github.com/santimattius/android-architecture-guide/blob/master/screenshoot/android-clean-arch-general.png?raw=true" alt="general architecture"/>
</p>

## Android Architecture Guide
Architecture guide recommended by google for android
- [UI layer](https://developer.android.com/jetpack/guide/ui-layer)
- [Domain layer](https://developer.android.com/jetpack/guide/domain-layer)
- [Data layer](https://developer.android.com/jetpack/guide/data-layer)

<p align="center">
  <img width="450" src="https://github.com/santimattius/android-architecture-guide/blob/master/screenshoot/android-architecture-guide.png?raw=true" alt="architecture layers"/>
</p>

## Basics checks
Run verification commands:
```shell
./gradlew app:check
```
or

```shell
./gradlew app:test
```
executing tests

## Detekt
This project contains DeteKt configurations, run next command in your terminal.

```shell
./gradlew app:detekt
```

## Jacoco
Run next command in your terminal for generate jacoco coverage report

```shell
./gradlew app:testDebugUnitTestCoverage
```

## SonarQube
Install sonarqube using Home brew
```shell
brew install sonarqube 
brew install sonar-scanner
```

After installation of the sonarqube you need to start the services. Please enter the command brew services start sonarqube in the terminal. You can create your credentials by hitting this URL: http://localhost:9000/

Update your sonar project setting on build.gradle:

```groovy
  sonarqube {
      properties {
          property "sonar.projectVersion", "1.0"
          property "sonar.projectName", "your app name in sonar"
          property "sonar.projectKey", "your app name in sonar"
          property "sonar.host.url", "http://localhost:9000/" // or https://sonarcloud.io/
          property "sonar.login", "xxxxxxxxxxxxxxx" //Generated in sonarqube
          property "sonar.language", "kotlin"
          property "sonar.sources","src/main/java"
          property "sonar.java.coveragePlugin", "jacoco"
          property "sonar.coverage.jacoco.xmlReportPaths", "${rootProject.projectDir}/app/build/reports/jacoco/debug/jacoco.xml"
      }
  }
```
Again in the Android Studio, hit this command in terminal

```shell
./gradlew sonarqube
```
## Dependencies

Below you will find the libraries used to build the template and according to my criteria the most used in android development so far.

- **[Jetpack Compose](https://developer.android.com/jetpack/compose)**, for ui components.
- **[Koin](https://insert-koin.io/)** - dependencie provider.
- **[Retrofit](https://square.github.io/retrofit/)** - networking.
- **[Gson](https://github.com/google/gson)** - json parser.
- **[Coil](https://coil-kt.github.io/coil/compose/)** with image loader.
- **[Kotlin coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)**, for concurrency.
- **[Mockk](https://mockk.io/)**, testing library.
- **[WebMockServer](https://github.com/square/okhttp/tree/master/mockwebserver )**, mock service response. 


## References

 - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
 - [Clean Code](https://blog.cleancoder.com/)
 - [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAjw7diEBhB-EiwAskVi13xJGdb6SCxqntF3pNt6JQ4ulvEQsB9JelBK2OIG5P0cePTCcsOksBoCk1sQAvD_BwE&gclsrc=aw.ds)
 - [Android developers](https://developer.android.com/)
