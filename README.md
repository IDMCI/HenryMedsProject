# HenryMedsProject #
This is an Android application built using MVVM Clean Architecture with Jetpack Compose.

> NOTE: This is a take-home project for interviewing purposes only. 

## Features: ##

- MVVM architecture for clean separation of concerns
- Clean Architecture for independent and testable layers
- Jetpack Compose for a modern and declarative UI
- Hilt for dependency injection
- Hilt ViewModel for simplified ViewModel creation
- Android Navigation for seamless app navigation
- Unit tests for core logic
- Integration tests for remote endpoint interaction

## Requirements: ##

Project requirements (Link is not shared here. See email for details)
Android Studio (latest version recommended)
Java 19 (tested with Azul Zulu 19.0.2)

## State of App: ##
- The app has the basic structure to completed at a later date.
- The app has the ability to select a date, starting with tomorrow's date.
- There are example classes to show the intended structure of the app and allow others to "hit the ground running" with feature development.
- `Retrofit` has been imported and injected within the `AppModule`. Once the endpoints have been completed, the `UserScheduleDataSource` should be refactored.
- `Room` has been imported and injected within the `AppModule`. Currently the data is not being stored.
- There are two tables created for `Room`; Client and Provider. Although the data wouldn't be stored in this manor, having the user's info stored and schedules of the client/provider would be helpful for caching.
- BUG: `kotlinx.serialization` plugin compiler is not being applied to the module. (See comment in `StoredUser` for more information) 

## Getting Started: ##

1. Clone the repository: `git clone https://github.com/IDMCI/FetchRewardsProject.git`
2. Open the project in Android Studio.
3. Run the app on an Android device or emulator with API level `28` or higher. Target SDK is set to API level `34`.

## Project Structure: ##

The project is organized following the clean architecture principles, with separate folders for:

- domain: Business logic independent of Android frameworks.
- datasource: Data access layer (repositories) with abstractions for local and remote data sources.
- di: Dependency injection configuration using Hilt.
- presentation: UI logic with Jetpack Compose and ViewModels.

## Testing: ##

The project does NOT currently include unit tests. However, the libraries for `mockito`, `mockk`, `junit` and others have been imported.

## Other Recommendations: ##
- It's recommended that the `presentation`, `domain` and `datasource` layers be moved into separate modules for actual separation of concerns.
- It's recommended that some of the business logic currently implemented and required be handled by the future endpoint.
- Project needs to be migrated from `kapt` to `ksp`. Currently the `kotlin` version of `2.0.20` is being downgraded to `1.9` because `kapt` isn't supported in `2.0+`.

### ScreenShot: ###
<img height="540" alt="items_llist" src="https://github.com/user-attachments/assets/c0b3fcf8-169f-4aa3-8b17-f634a97df08e">
