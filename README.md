# PhilosophyVille

PhilosophyVille is a 2D game developed in Java using the LibGDX framework. The project explores philosophical ideas through an interactive game environment where the player can explore, interact with characters, and engage with dialogue.

The project was developed as part of my MSc in Software Development and gave me practical experience designing and implementing a larger Java application.

## Features

* 2D player movement and exploration
* Animated player and NPC sprites
* NPC interaction and dialogue system
* Inventory system
* In-game menu and user interface
* Multiple game screens and game-state management

## Technologies

* Java
* LibGDX
* Gradle
* LWJGL3
* Git

## Project Structure

The application is separated into multiple modules:

* `core` — main game logic, including gameplay, NPCs, dialogue, inventory and UI
* `lwjgl3` — desktop launcher
* `assets` — sprites, UI assets and other game resources

## Running the Game

### Requirements

* Java
* Git

Clone the repository:

```bash
git clone https://github.com/TheZuza/PhilosophyVille.git
cd PhilosophyVille
```

Run the desktop version using the Gradle wrapper:

```bash
./gradlew lwjgl3:run
```

## What I Learned

Building PhilosophyVille gave me practical experience applying object-oriented programming concepts in Java to a larger project.

I worked with game states, user input, sprite animation, NPC behaviour, dialogue, inventory management and UI components while organising the application across multiple Java classes.

The project also gave me experience using Git for version control and Gradle for dependency management and builds.

