# ANCIENT BRAWLERS
Welcome to the world of "Ancient Brawlers," an exciting player-versus-player Trading Card Game that promises strategic battles, 
thrilling encounters, and epic clashes between powerful creatures! In this immersive gaming experience, players will step 
into the shoes of formidable warriors, summoning a team of three unique creatures onto the battlefield. The objective is 
clear: outwit your opponent, unleash devastating attacks, and secure victory by eliminating all of their creatures.



# Adherence to SOLID Principles
* Single Responsibility Principle (SRP)
    The SRP requires that each class should just have a single responsibility i.e. only one reason to change. 
    Our program closely follows this starting all the way at a high level where our packages are divided by functionality
    i.e. entities, game_ui, use_cases, interface_adapters to all the way on a more granular level where the classes within
    these packages have just one responsibility. For instance under entities -> cardEffects, one can see that each class 
    is responsible for just one effect and all these effects are handled by the CardEffectFactory. Overall, this behavior can be
    for every class and interface in our program.
* Open/Closed Principle (OCP)
    The OCP requires for code to be open to extension but not modification. Taking the same example as above, for a future 
    update if a new effect was to be considered just an new class for the new effect would be needed. Similarly, the same 
    follows for the entity cards. Coming to the use cases, they all also closely follow OCP as there parent classes with
    common methods from which child classes can pull and override from.
* Liskov Substitution Principle (LSP)
    The LSP states that subclasses should only extend and not modify or remove. Our code closely follows this as is seen 
    in all the use cases where none of the child classes modify or remove but only extend. For instance, in the turn start
    use case the TurnStartInteractor only extends the methods from TurnStartInteractor.
* Interface Segregation Principle (ISP)
    ISP states that interfaces should be small so that users don't depend on things they dont need. Drawing from the 
    same example as above in all of our use cases. The interfaces implemented for the particular use case only has methods
    needed to run that use case.
* Dependency Inversion Principle (DIP)
    DIP states that one should be able to change individual pieces without having to change anything other than the 
    individual pieces. Again drawing from the use cases in our code base, for each use case, Interfaces are present to 
    insure that high-level modules don't depend on low level modules and there exists no source code dependency.

# Adherence to Clean Architecture

# Design Patterns
In terms of design patterns, one can observe the Factory Method, Listeners, and the Dependency Injection design pattern.

* Factory Method

* Listeners

* Dependency Injection
