# Ancient Brawlers
Ancient Brawlers is a Trading Card Game (TCG) that is currently designed for two players to play locally. In this game, 
each player controls three "creatures" (used for battle), and each has a deck of cards containing "actions" and 
"structures" (used as additional support). Players must fight to defeat all the opponent's creatures, by directing creatures
to attack and giving them boosts using the deck cards. Like all TCGs out there, cards in the deck have different effects, 
so use them strategically, and outwit your opponent!

## Table of Contents
* [Specifications (Running the game)](#specifications)
* [Game Rules and Tutorial](#game-rules-and-tutorial)
* [The Game's Software Architecture](#software-architecture)
* [Contributions](#contributions)

## Specifications
This program is written in Java, using JDK `Amazon Corretto version 11.0.19`. Unit tests are written with `JUnit 5`.

To run the game, clone this repository and run the file `src/main/java/AncientBrawlersApp.java`.

The data in this game are stored in JSON files. Make sure you have the JSON dependency added to your configuration file.
The project repository contains the `build.gradle` file which should already include the necessary settings.

## Game Rules and Tutorial
During gameplay, players will mostly be looking at this screen:

![GameBoardDemo](images/GameBoardDemo.png)

Let's break it down:

### Cards: Creature, Essence, Action, Structure

![Creature](src/gameArt/The-Celestial.png)

This is a *Creature* card. The two numbers in the bottom right corner of the card shows the *Attack cost* (top) and 
*Defend cost* (bottom). A Creature also has *Hit-points* and *Attack Damage*, but these stats are shown in separate 
components in the gameplay screen.

![Essence](src/gameArt/Essence.png)

This is an *Essence* card. It is the *cost* for a creature to attack or defend. During the start of a player's turn, 
there will always be two Essence cards given to the player.

![Action](src/gameArt/Cataclysm.png)

This is an *Action* card. It is like a "spell card" that a player can use during their turn. Action cards are contained 
in every player's deck. Each unique Action card has its own effects, and it will have a description that shows when you
hover your mouse over the card. For example, the card we are seeing right now is "Cataclysm", and its description is 
"Deal 10 damage to ALL creatures." (You didn't misread it, this card deals damage to *everyone*, but use it wisely and you
may just claim victory!)

![Structure]

![Structure](src/gameArt/Forest-of-the-Elves.png)

This is a *Structure* card. It is similar to an *Action* in that it has effects and is in the player's deck, but the catch 
is that playing a *Structure* card will set it on the game board (until you play another one to take its place, or until 
your opponent destroys it with some *Action*). Most importantly, the effects on a *Structure* can keep triggering, as long as
the game has come to the required moments (start of turn, end of turn, etc.). For example, the "Forest of the Elves" card 
has the following description: "At the end of your turn, restore 2 HP to all friendly creatures."

### The player's resources

Let's take a closer look at one side of the game board.

![PlayerSide](images/PlayerSideDemo.png)

The bottom row displays the cards currently in the player's hand. You can click to select any of them, and the selected 
card will be shown just above the "Play Card" button. Once you've decided, hit that "Play Card" button and the effects from
the selected card will be unleashed! If you select and play a Structure, it will show up at the left-most card slot show in
this image. Finally, on the right side of the board, you get to see the supplies you have: a deck of cards (Action and 
Structure) and another deck full of Essence (unlimited Essence!).

### Attacking

To attack, the player must select a friendly creature, then select an enemy creature. The attack will initiate once this 
sequence of actions are taken. When a player decides to let a creature attack an enemy creature, the game will switch to 
this screen:

![AttackScreen](images/AttackDemo.png)

The game will calculate for the other player whether they have enough Essence to spend on a Defend move, and any available
defending creatures will show up on the bottom two card slots. In this situation the player under attack can do two things:
- Clicking "Pass", letting the attack proceed, and the target creature *takes damage*.
- Selecting one of the defenders and clicking "Confirm": the defending creature will *take the damage instead*.
So, you may want to coordinate your creatures to protect a certain powerful creature, and let that creature be ready to
deliver a powerful strike!

### Target Selection

When an Action is played and its effect is on a single creature, then we see a target-selection screen:

![TargetScreen](images/TargetSelectionDemo.png)

Here, the card "Salvage" has the effect "Restore 10 HP to a friendly creature." Thus, we see that amount the six creatures
that are on the game board, the available choices are only the 3 creatures friendly to the player who used this card.
In this screen, you will have to select a target to proceed.

### Game Flow

A typical turn goes like this:
1. The turn begins. The player draws 2 deck cards, and also draws 2 Essence. Buffs are cleared during this stage.
2. Middle stage of the turn. The player can play cards from their hand (as many as they want, but Essence cards can't be directly played).
3. Next, if the player is done, they can press the "End Turn" button, and the turn will *proceed to the end phase*.
4. Or, if the player decides to attack, then the attacking phase is processed. After that, the turn *also proceeds to the end phase*.
5. The turn ends. Effects that trigger during this stage shall take place. Finally, the turn is passed to the opposing player.

Now you are ready to start the brawl!


## Software Architecture

### Adherence to SOLID principles
* Single Responsibility Principle (SRP)<br>
  
    The SRP requires that each class should just have a single responsibility i.e. only one reason to change. 
    Our program closely follows this starting all the way at a high level where our packages are divided by functionality
    i.e. entities, game_ui, use_cases, interface_adapters to all the way on a more granular level where the classes within
    these packages have just one responsibility. For instance under entities -> cardEffects, one can see that each class 
    is responsible for just one effect and all these effects are handled by the CardEffectFactory. Overall, this behavior can be
    for every class and interface in our program.
* Open/Closed Principle (OCP)<br>

    The OCP requires for code to be open to extension but not modification. Taking the same example as above, for a future 
    update if a new effect was to be considered just an new class for the new effect would be needed. Similarly, the same 
    follows for the entity cards. Coming to the use cases, they all also closely follow OCP as there parent classes with
    common methods from which child classes can pull and override from.
* Liskov Substitution Principle (LSP)<br>

    The LSP states that subclasses should only extend and not modify or remove. Our code closely follows this as is seen 
    in all the use cases where none of the child classes modify or remove but only extend. For instance, in the turn start
    use case the TurnStartInteractor only extends the methods from TurnStartInteractor.
* Interface Segregation Principle (ISP)<br>

    ISP states that interfaces should be small so that users don't depend on things they dont need. Drawing from the 
    same example as above in all of our use cases. The interfaces implemented for the particular use case only has methods
    needed to run that use case.
* Dependency Inversion Principle (DIP)<br>

    DIP states that one should be able to change individual pieces without having to change anything other than the 
    individual pieces. Again drawing from the use cases in our code base, for each use case, Interfaces are present to 
    insure that high-level modules don't depend on low level modules and there exists no source code dependency.

### Clean Architecture

### Design Patterns
In terms of design patterns, one can observe the Factory Method, Listeners, and the Dependency Injection design pattern.

* Factory Method<br>

  This design pattern can be found in the entities of our codebase. More specifically, this design pattern was implemented for CardEffects,   Cards, and Decks. Within each of these modules one can find a class ending with Factory for example CardFactory which is responsible 
  for entity construction. We chose to implement this design pattern for our entities as The Factory Method separates product construction 
  code from the code that actually uses the product, making it easier to extend the product construction code independently from the rest 
  of the code.
* Listeners<br>

  The listener or the Observer design pattern can be found in the game ui part of our codebase. Within the module game_ui, all the classes 
  ending with "Screen" such as DefendScreen and MulliganScreen are observers/listeners to the GameFrame class. Whenever the GameFrame is 
  told to switch screen all the observers/listeners will update themselves.   
* Dependency Injection<br>

  This design pattern can be found in a lot of places in our codebase. The most primrary example of a use of this design principle would be
  the dependency injection of our game's GameState class into all the use_cases. As the name suggest, The GameState class is responsible 
  for storing the current state of the game and its dependency injection into our use cases, more specifically, the Interactor class within 
  each of the use case allows us to make sure that each use case is up to date.   


