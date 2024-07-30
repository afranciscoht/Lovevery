The project was designed in Kotlin, using the MVVM architecture, along with dependency injection and assisted injection.

The dependency provider can be managed from ExamViewModelsProvider (currently intended only for managing ViewModels). However, this is where navigation APIs between different modules could be added.

The main component can be observed in MainComponent, which handles the injection of all submodules (injectable with Dagger).

The unit tests added were for testing the mappers (used between the repository and the view model), separating the business models from the UI models.

Additional features:

Navigation Bar Orchestrator, which allows toggling between 2 types (for now).
Fragment navigation with the Navigation Component.
Animations with Lottie.
A mapper that transforms the business model into a UI model.
