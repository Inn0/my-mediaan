# MyMediaan

"MyMediaan" is a helper tool for [Mediaan](https://mediaan.com/) employees.

## Credits

This pet project was created by [Daan Brocatus](https://github.com/Inn0) and [Ivans Mihailovs](https://github.com/ivansdivans). Their intention was to practice Android and Jetpack Compose.

## Technologies used
1. [Android Studio](https://developer.android.com/studio) - Integrated Development Environment
2. [Kotlin](https://kotlinlang.org/) - Programming language
3. [Jetpack Compose](https://developer.android.com/develop/ui/compose) - UI toolkit

## Collaboration rules
1. Branching strategy - [Trunk-based workflow](https://www.atlassian.com/continuous-delivery/continuous-integration/trunk-based-development); create short-lived branches and regularly integrate them into `main` branch
2. Branch naming convention - `typeOfBranch-issueNumber-descriptiveBranchTitle` (examples: `feature/1-create-basic-navigation-panel`, `release/v0.1`, `bugfix/22-navigation-doesnt-work-in-landscape-mode`)
4. Design patterns - [Model-View-ViewModel](https://learn.microsoft.com/en-us/dotnet/architecture/maui/mvvm)
5. Development conventions - keep `main` ready-to-deploy at all times; use [feature-flags](https://martinfowler.com/articles/feature-toggles.html) for potentially breaking changes
6. Conventional commits - keep a clear and concise commit history using the [conventional commit format](https://www.conventionalcommits.org/en/v1.0.0/)
7. Testing strategy - meaningful unit tests for each feature and bugfix
8. Merging policy - mandatory PR, build and unit tests are green, 1 approval; no force pushes
9. Feature tracking - each feature/bug is registered as an issue in Github and added to project (Kanban board)
10. Feature estimation - issues were estimated using "T-shirt sizing" technique
11. Feature prioritisation - issues are prioritised based on trafic lights system; focus on mvp related features, highest priority & smallest in size are implemented first
