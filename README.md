# Android App Composable Template

This is a template to build an Android app applying good practices and using a clean architecture, you will see that the code is super decoupled with external frameworks and even with the same Android framework, this will help you to model your domain purely in Kotlin without generating external dependencies.

<p align="center">
  <img src="https://github.com/santimattius/android-arch-composable-template/blob/master/screenshoot/android-clean-arch-capture.png?raw=true" alt="App Capture"/>
</p>


# Application architecture

In the following images you will see how the app is built and what its levels of abstraction are.

## General

<p align="center">
  <img src="https://github.com/santimattius/android-arch-composable-template/blob/master/screenshoot/android-clean-arch-general.png?raw=true" alt="general architecture"/>
</p>

## Layers
<p align="center">
  <img src="https://github.com/santimattius/android-arch-composable-template/blob/master/screenshoot/android-clean-arch-layers.png?raw=true" alt="architecture layers"/>
</p>

## Dependencies

Below you will find the libraries used to build the template and according to my criteria the most used in android development so far.

- **Jetpack Compose**:
  - https://developer.android.com/jetpack/compose
- **Koin** - dependencie provider: 
  - https://insert-koin.io/
- **Retrofit** - networking: 
  - https://square.github.io/retrofit/
- **Gson** - json parser:
  - https://github.com/google/gson
- **Glide** with image loader:
  - https://github.com/bumptech/glide
- **Kotlin coroutines**
  - https://kotlinlang.org/docs/reference/coroutines-overview.html
- **Mockk**, testing library
  - https://mockk.io/  

## References

 - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
 - [Clean Code](https://blog.cleancoder.com/)
 - [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAjw7diEBhB-EiwAskVi13xJGdb6SCxqntF3pNt6JQ4ulvEQsB9JelBK2OIG5P0cePTCcsOksBoCk1sQAvD_BwE&gclsrc=aw.ds)
 - [Android developers](https://developer.android.com/)
