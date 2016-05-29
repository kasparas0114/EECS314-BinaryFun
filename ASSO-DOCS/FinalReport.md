
<h1>BinaryFun</h1>

**Index**

[Description](#description)

[New Features](#new-features) 

[Requirements](#requirements)

 
Software Architecture:
 
 [Logical View](#logical-view)
 
 [Process View](#process-view)
	 
 [Development View](#development-view)
	 
 [Physical View](#physical-view)
	 
 [Scenarios](#scenarios)
 

## Description
BinaryFun is an Android Application game that has an educational purpose.

![Image of Application](http://imageshack.com/a/img922/580/9fKEHJ.png)

When you opened the original application you would have access to game modes such as Binary Adder, Binary to Decimal, Binary to Hexadecimal and Boolean Fun. With our new features when you access the application you have to choose the Player Mode first (Single Player or Multi Player) and then you will go to a menu where you can choose between tutorials or game modes.
The games consist in timed exercises in which you a have to make calculus, depending on the game mode you choose. 


**Binary Adder**

Binary Adder is a simple adding game where you have 5 rounds and a timer. 
In each round you are given two binary numbers to sum and once you insert the correct result you immediatly pass to the next round.
The least time you take to pass the 5 rounds the bigger score you achieve and if you get stuck on a round you can always start a new game!
In Multiplayer mode the player which has a bigger score wins!

![Image of Binary Adder](http://imageshack.com/a/img921/6862/9FkPuk.png)


**Binary To Decimal and Binary To Hex**

Binary To Decimal and Binary To Hex are conversion games where it's given a target number in decimal base and you need to insert the correct conversion to binary or hexadecimal respectively. 
Similarly to BinaryAdder you have 5 rounds and a timer. The score is obtained upon completion of the 5 rounds and with the time spent to do so.
In Multiplayer mode the player which finishes the 5 rounds first wins!

![Image of BtD & BtH](http://s33.postimg.org/exyegt66n/Deepin_Screenshot20160528190542.png)


**Boolean Fun**

Boolean Fun is a game where it's given a boolean tree and, unlike the other games described so far, it doesn't have 5 rounds nor only one correct solution to the challenge given to you. 
There are different solutions to the same boolean tree so your task is to finish as fast as you can and the best way to do so is to seek for the simplest solution. Whenever you are ready just press the 'Done' button and check your solution.
In Multiplayer mode the player which finishes in the smallest amount of time wins!

![Image of BooleanFun](http://s33.postimg.org/nkyqscdqn/Deepin_Screenshot20160327134109.png)


There is a score and a table of high scores.

![Image of Highscore Table](http://imageshack.com/a/img922/6553/I1QOQ2.png)


If you have any doubts about the games you can access Learn Binary, Learn Logic, Learn Arithmetic and Learn Representations for more explanations and help.

![Image of Learn](http://imageshack.com/a/img922/5359/MyYr4S.png)


## Requirements

Since it's an Android Application it is implemented in Java, tutorials view are made by HTML and css and it uses Gradle to build the project. 
To run it you need an Android environment, like a smartphone with a software version Android API 21, also known as Lollipop.
To compile the whole project you need 
	
	internal_impl-22.0.0.jar

	classpath 'com.android.tools.build:gradle:1.1.0'
	
which are the libraries of gradle to build the project and an Android's library.


**Git and BinaryFun**

In GitHub, the description of the commits show us what functionalities have been added or what problems were fixed.
To make a commit you have to fork the original repository. When you make a change that is usefull to the project you make a pull request and the ''master'' decides if he wants to add your changes to the project or not.


## New Features

We decided to add some features to the game in order to make it more complete and to turn it into a client-server application.
We added the feature of multiplayer. This feature would allow us to implement a network package, necessary in client-server applications. 
We added a quit button in the end so the user could quit the application.

## Logical View
The Logical view main purpose in the software architecture of a system is to describe the functionality, that system provides to users, giving the static view of an application. As a tool to represent it, we used Class Diagram. Class diagrams are mainly used for objective oriented programming, because it can be directly mapped to the language. It helps us to analyse the system and to describe all responsibilities of a system. 
On logical view it is comfortable to add new features to a system. As we decided before to add multiplayer mode to a system, as a solution we chose open source JXTA (Juxtapose) peer-to-peer protocol.

![Image of Class Diagram](https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/ClassDiagram.jpg)

## Process View
The Process view is responsable about dynamic aspect of the system. It explains the communication between system processes and key concepts needed to describe what happens in the development process, on what, when it happens, and why. Tools used for process view modeling is Activity Diagram. In the application we are improving and analysing, we separated everything to 3 main activities.
The first one is the main one, when user opens an application, he can pick on of three selections - Multiplayer mode, Tutorials and Single player mode. So there is a switch with 3 different routes and end states.

![Image of Main Activity Diagram](https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/ActivityDiagramMain.jpg)

Second one, is the singleplayer mode activity, where user can choose only one route - pick a game, play it and submit a highscore, going all the way to the final state. 

![Image of Single Player Activity Diagram](https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/ActivityDiagramSinglePlayer.jpg)

And last but not least, multiplayer mode activity, where user can create a room, play with a random player, or join the existing room.

![Image of Multiplayer Activity Diagram](https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/ActivityDiagramMultiplayer.jpg)

##Development View
The development view illustrates a system from porgrammer's perspective, it is all about software management. This view uses UML Component diagram to describe system components. It is primarily for developers who will be building the software.
In our diagrams we show dependencies and relationships between modules and how they are organized.
Our application's engine is using Database service, Juxtapose Service, Game module and android as an operating system for this application.

![Image of Component Diagram] (https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/ComponentDiagram.jpg)

##Physical View
The physical view describes the system from a system engineer's point of view. How sofware and hardware are connected to each other. What our system nodes (harware or sofware) are dependent on. UML diagram we used to analyse this view is Deployment Diagram. So at first, game is dependent on database and contrary.

![Image of Deployment Diagram Game Database] (https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/DeploymentGameDatabase.jpg)

The second one deployment diagram we did, was to describe, how multiplayer game works, so there are 2 peers, connected to eachother, they are both using a server, which is using a database.

![Image of Deploment Multiplayer Diagram] (https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/DeploymentMultiplayer.jpg)

##Scenarios
This part of 4+1 architectural view model, is an ilustration of an architecture. In this part we described sequences of interactions between objects and processes. For this view, we were using UML Use-case Diagrams. Using use-case diagrams it is easy for everyone to understand, how this application is intended to be used.

![Image of Use-Case Diagram] (https://github.com/kasparas0114/EECS314-BinaryFun/blob/master/ASSO-DOCS/Diagrams/UseCaseDiagram.jpg)
**Contributions**

Kasparas Stadalnikas (201509821) - 50%

Vytautas Nekraševičius (201509869) - 50%

Status API Training Shop Blog About
© 2016 GitHub, Inc. Terms Privacy Security Contact Help
