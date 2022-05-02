# Github

Github app using:

- MVVM
- Clean Architecture
- Dependency Injection
- SOLID principles, mainly the dependency inversion
- Data Binding
- LiveData
- RxJava
- RxAndroid
- Repository
- Modularization
- Unit testing
- UI Testing

# How it works

It start retriving the local or remote data using the repository pattern, then it's time for the UseCases
where the raw model is mapped into a DTO or apply some business rules to it. After this it's time for the ViewModel
get this data and apply some Redux on it transforming this data into states (like success, error, empty state, etc...)
notifying the View over the LiveData.

You can find below an image of how it works:

![chamadas](https://raw.githubusercontent.com/vfernandess/Github/master/base.png)


# Modularization

The modularization was introduced to lower the build time, besides we can separete the feature responsibilities and in the future
add Dynamic-Modules.

The rules for this modularization are:

1. When a feature module is created it must have two submodules: public and impl
2. Features modules can only be depedant from the public module of another feature module
3. The public submodule have what other features depend on. Here is the place where you share images, files, colors, interfaces.
4. The impl submodule depend only from public submodules. Here is the place for the DI, UI Testing, unit testing and whatever concrete implementation needed to project works.
5. The "app" module is the application module, it is the only which knows the all the impl submodules. It acts as a bridge to have all the implementation know each other.

You can find below an image of how it works:

![modularização](https://raw.githubusercontent.com/vfernandess/Github/master/modularization.png)

# More Resources

This project have some supplementary modules:
    
- core-binding: utils for data binding. Like the BindableAdapter. It is a android module
- core-lib: have all base mapper class also have networking setup. It is a kotlin module.
- core-android: android utils for LiveData, DI, Navigation, State Machine. It is a android module
- design-system: have UI styles like fonts, colors, styles and in the future is intended to has the design-system-components
- ui-utility: utils for UI Components like espresso ui macthers, mock helpers and JUnit TestRules for UI Testing.
- unit-utility: utils for Mocks and TestRules for unit testing
    
# Sources

[GDG Talk: Android - Modularização em larga escala](https://www.youtube.com/watch?v=UFmmcUvWoI0)

[Breaking Down Modularization](https://www.droidcon.com/media-detail?video=380844229)

[Android at Scale @Square](https://www.droidcon.com/media-detail?video=380843878)
    
