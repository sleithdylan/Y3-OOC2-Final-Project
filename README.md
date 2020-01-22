# OOC II Final Project

**Title:** Computer Monitor Manager<br>
**Name:** Dylan Sleith<br>
**Student ID:** G00354557<br>

## Application Function
While using this simple and clean application you will be able you will be able to manage computer monitor orders. You will not only be able to add, delete, search monitors, etc but you will also be able to load and save them to the database. (See example of image added below).

![](https://i.imgur.com/J8UF1vm.png)

### Prerequisites

Download and install the following:

* [Eclipse](https://www.eclipse.org/ide/)
* [JDK 8+](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Running the Application

1. Clone repository to your desired location
```sh
git clone https://github.com/DanielCreggOrganization/ooc2-final-project-2019-sleithdylan.git
```

2. Open project in Eclipse

3. Run Main.java

### Using the Application
1. Once the application has loaded type the following in "Enter Database Path":
```sh
./resources/monitorsDB.ser
```

2. Once the database has loaded you can now use the other functions. There are 5 monitors already in the database in which you can find in the search item field, They are:
```sh
AOC
Asus
Acer
MSI
Dell
```

## Project Requirments
* The project, including code and documentaion, has been fully contained in the provied Git Classroom repository as shown above.
* The project contains a working JavaFX GUI which manages Computer Monitors of your choice.
* The GUI provides access to several methods which includes: Load Database, Add Items, Delete Items, Search Items, Show Items, Save Database and Quit.
* The code compiles.
* The application code is formatted in a consistent and standard way.
* The code contains comments.
* There has been a multiple of commits per day each week.
* The documentation and commentary is free of grammar and spelling mistakes.

## Project Requirments above and beyond

* I have chosen to do a 2 column layout using GridPane.
* I have made multiple of scenes, e.g: Once you click the "Add Item" or "Delete Item" button the scene will change.
* I have added an Alert Class so that an alert will pop up if you have entered a vaild or invalid item. Once it has shown you can click "OK" to close the pop up.
* I have added a "File" Menu in which you can open by clicking "File" or Alt + F and then Quit if you want to close application.
* I have added CSS to the application and a blue gradient button hover effect to make it a bit more customizable.

## Application Architecture
* How the application is structured is that 4 java classes such as `Alert`,`Main`,`Monitor` and `MonitorManager`are located in the `ie.gmit.monitormanager` package.

There are several methods in which were used to make the application function which were:

| Methods          | Description               |
| :---------------- | :------------------------ |
| `Load Database`   | Loads database using monitorsDB.ser |
| `addMonitor()`     | Adds new computer monitor  |
| `deleteMonitor()`     | Deletes existing computer monitor  |
| `findModelByMake()`     | Searches through database by Make and outputs Make and Number   |
| `findTotalMonitors()`     | Shows number of computer monitors in database  |
| `Save Database`     | Saves database   |
| `display()`     | Displays alert "title" and "message"   |
| `exit()`     | Quits application   |

Java Collections were to used to allow reusable components.

![](https://i.imgur.com/kNnazq9.png)

## JavaFX
I chose this type of "default" design because I wanted to keep my application clean and simple but with blue button hover effects. I chose a 2 column layout as I wanted to have spacing from all the other components, I used a different scene for 2 buttons since I did not want the application to be cluttered and a few lines of CSS to make the application seem more interactive.

## Roadblocks
There was really only one issue I faced which was when I was trying to layout my applcation, I was using the method which we learned but it wasn't turning out the way I wanted it too so instead I created Column and Row Constraints in which I used to layout the columns and rows in my application for the main menu.

## Resources

* [thenewboston](https://www.youtube.com/playlist?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG) - JavaFX GUI Design
