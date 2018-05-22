# ParcelPoint Coding Challenge



## Question 1

A robotic arm places boxes in 5 columns (A, B, C, D, E) each with height 10 boxes. The default position for the arm is before column A. The command F makes the arm move forward along the rows. The command D causes the load to be dropped. The load must be a string value that is required to be surrounded by {} following a D command. The command R causes the arm to go back to default position.

Dropping the load on a full column or the default position leads to nothing happening. Once a load is successfully dropped the arm moves forward one position except when in the last column where nothing happens.

The arm cannot move ahead of the last column and will stay there if it receives a forward command.

Any other command is ignored and the robot stays in its current position. The commands are case sensitive.

Using the package structure provided, implement the ```public Map<String, List<String>> simulateArm(final String input)``` method in the Robot class.

The ```simulateArm``` method should accept the commands as a string and returns the state of the columns as a ```Map<String, List<String>>```.

E.g.

```
Robot robot = new Robot();
Map<String, List<String>> output = robot.simulateArm("FD{foo}FFD{bar}RFFD{Foo}RFFD{World}D{}DA{hello}”);
System.out.println(output.toString()) // prints {A=[foo], B=[Foo, World], C=[], D=[bar, hello], E=[]} to the console
```
Your free to add to the files, classes, pom, dependencies, etc, as you require. Please don't change the existing project structure or the skeleton classes provided.

Implement your test cases in the ```RobotTest``` class. Your free to use any testing framework(s) of your choice.

Please include instructions on how to run your unit tests.



## Question 2

Create an HTML page with two forms.

Form 1 has 2 fields: First Name and Last Name.

Form 2 has 2 fields: Age and Gender.

Display only first form when page is loaded in the browser. When the first form is filled and the Next button is clicked show the second form only. When second form is filled and Submit button is clicked, display the form data as text.

Use JavaScript/jQuery/Angular/React or any other framework of your choice to build the page. You may use multiple HTML pages to modularise your work but do not navigate between pages.



## Submission Instructions

Please upload your solution to both questions to git repos and notify us. Include any instructions in a README.md file.

## daffleskmc

Question 1

RobotTest.java - to run using JUnit

Question 2

Files included: index.html, form.html, result.html

Run the first html, the /WEB-INF/index.html.
