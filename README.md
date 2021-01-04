# SampleApp

Features Implemented
- Search star war characters based on user input. 
- Display details like name, birth year, height, species, planet, films, opening moview crawl for selected star war character 

# Unit Test Cases
Unit test cases are included for the following:
- ViewModel 
- UseCases 

# Project Characteristics 
- 100% Kotlin<br />
- Architecture (Clean Architecture, MVVM)<br />
- Dependency Injection(Using HILT)<br />
- Coroutines<br />
- Kotlin Flow<br />
- Architecture Components(LiveData, ViewModel)<br />
- GSON for parsing<br />
- Navigation<br />
- Extension Functions
- Unit Testing(Junit, Mockito)<br />

# App Architecture Details:
![Untitled Diagram (1)](https://user-images.githubusercontent.com/16702310/103493822-8fd58100-4e01-11eb-8465-a443c394e83f.png)

 # View
 This layer mainly deals with the UI of the application and has its own presentation models.
 
 Components
 - **Activity/Fragment**: Presents data on the screen and pass user interaction to viewmodel
 - **ViewModel**: Executes use cases based on user interaction and updates ui using LiveData
 
 # Domain
 Contains all the business logic for the application.Domain layers is independent of other layers, has its own models, so that changes in other layers will have no effect on domain layer.
 
 Components
 - **UseCase** : Handles business logic
 - **Domain Models**: Represents structure of response data
 - **Repository Interface**: To keep domain layer independent from data layer.
 
 # Data
 Manages application data and exposes data to domain layer
 
 components
 - **Repository** : Gets the requested data using api service ans exposes data to domain layer. 
 - **Mapper** : Used to map data models to domains models to keep domain independent of data layer.
 - **DataModel** : Structure for data retrieved from remote data source. 

# TODO
- Add loaders for different sections while loading details like species, films, planet details etc.
- Add UI test cases
- Moved common test code to a base class

# Screenshots
 <img src="https://user-images.githubusercontent.com/16702310/103493990-a3351c00-4e02-11eb-975f-a748e2892e7e.png" width="30%">  <img src="https://user-images.githubusercontent.com/16702310/103493995-ab8d5700-4e02-11eb-8860-cbf1d8da85ce.png" width="30%">  <img src="https://user-images.githubusercontent.com/16702310/103494001-b0520b00-4e02-11eb-9cc7-052a89132a15.png" width="30%">
 <img src="https://user-images.githubusercontent.com/16702310/103494004-b516bf00-4e02-11eb-884d-b8d84daa73ad.png" width="30%">
 
