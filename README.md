# NASA-Pictures-App

Welcome 👋 

NASA Pictures App is an android app that displays awesome pictures parsed from a json file.

It is written 100% in Kotlin with both unit and integrated tests.🙂

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [Coroutine](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Coil](https://github.com/coil-kt/coil)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Timber](https://github.com/JakeWharton/timber)
* [Lottie](https://github.com/airbnb/lottie-android)
* [JUnit](https://junit.org/junit4/)
* [Mockito](http://site.mockito.org/)
* [Robolectric](http://robolectric.org/)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android 11 ([API 30](https://developer.android.com/preview/api-overview.html))
* Latest Android SDK Tools and build tools.

## Installation

* To run this code, clone this repository using this command `git clone https://github.com/segunfrancis/NASA-Pictures-App.git`
* Import into android studio
* Build the project and run on an android device or emulator

## Architecture

The architecture of the project follows the principles of Clean Architecture and MVVM. Here's how the project implements it:


The app when run will show you a simple list of awesome pictures parsed from a json file.
<p align="center">
<img src="https://github.com/segunfrancis/NASA-Pictures-App/blob/master/art/Screenshot_20201119-215432_NASA%20Pictures%20app.jpg" alt="Drawing" style="width: 10px;"/>
</p>

Let's look at each of the architecture layers and the role each one plays :)

![architecture](https://github.com/segunfrancis/NASA-Pictures-App/blob/master/art/clean%20arch%20graph.png)

### App module

This layer makes use of the Android Framework and is used to create all of our UI components to display inside of the Main Activity. This layer contains the views(activities and fragments) and the ViewModel. The ViewModel receives its data from the use cases of the domain layer and then supplies the views.

### Domain module

The domain layer responsibility is to simply contain the UseCase instance used to retrieve data from the Data layer and pass it onto the Presentation layer.

### Data Module

The Data layer is our access point to external data layers and is used to fetch data from multiple sources (examples are cache and network in our case). In this case, it gets data from the local source only. It contains an implementation of the Repository.

### Local Module

The local layer contains the business logic that converts the input stream to a list of data that we can use. I was not able to parse the json file from this module because the `context` was returning `null` when I tried to access it from this layer. As a result, I parsed the json to an `InputStream` from the app module and sent the data to the local module where it was converted to a list.

## Screens

<ul>
  <img src="https://github.com/segunfrancis/NASA-Pictures-App/blob/master/art/20201119_211044_edited_1.gif" width="40%" alt="Screen3" hspace="15">
  <img src="https://github.com/segunfrancis/NASA-Pictures-App/blob/master/art/20201119_211358_edited_1.gif" width="40%" alt="Screen1" hspace="15">
  <img src="https://github.com/segunfrancis/NASA-Pictures-App/blob/master/art/20201119_211621_edited_1.gif" width="40%" alt="Screen2" hspace="15">
</ul>

## Appreciation
* [Jed Nocum](https://lottiefiles.com/17651-swipe-left-to-right) - Lottie Animation

## Author

* [Segun Francis](https://www.linkedin.com/in/segun-francis-302361a1)
