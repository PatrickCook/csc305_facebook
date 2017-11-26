# CSC 305 Facebook:

## To Run:
- Open project using Eclipse
- Ensure database exists in the /src/db/ folder. If it does not exist the application will create one on start
- Run project by starting the /src/pcook01/views/Facebook.java file
    - This is the main view and contains a main() method.

## Navigating Project:
The project has been split up into 5 different packages, 4 main packages and the last being a sub-package.
- pcook01
    - models
    - views
        - components
    - controllers
    - singletons

The views folder contains all root views. The components folder contains all the reusable components I created which are then used by the root views.

## Justification for Design:

When designing this application I tried my best to develop reusable code and demonstrate my knowledge in the design patterns taught. In the following few paragraphs I will briefly outline major design choices and explain why I chose to them.

### State Machine
I developed my application using the MVC design pattern which has been illustrated above (how I separated my models, views, and components). This project required the ability to transition between different states such as the login page, news feed page, settings page, etc. In order to transition between these different views I decided to add states to Facebook.java class. Each view (login, news feed, etc) has the ability to change the parent's state. This allows the login page to handle authentication and then hand off to the news feed page. Facebook.java class handles this state change by removing the root view of the Facebook.java class and replacing it with the next view. This allowed for smooth transitions between states and turned out to be one of my best design choices.

### Decrease Coupling, Increase Cohesion
Each view required components such as the friend list panel, new post panel, profile picture panel, and many others. I separated each of these components into their own classes so that could reuse them in any views I needed. Each main view was built by adding these pre-made components.

### Connecting the Dots (or controllers and views)
The next challenge was organizing the communication between the controllers and views. In order to make this possible I implemented a multitude of ActionListener classes in each controller. Each ActionListener was responsible for handling login, refreshing posts, profile picture upload, etc. Each controller had a reference to it's corresponding view and the model (currently logged in user). Using these references I was able to set action listeners for the components in the view. Whenever an action occurred (user input, or button click), the view would delegate the action response to it's corresponding controller. The controller would then handle the event and update the models accordingly. In many cases the controller would communicate directly with the database instead of making the models perform that communication.

### Singletons
There were a few classes which were mainly utilities, such as the database controller and password authentication. I chose to implement these as singletons or static methods. The database controller was implemented using the singleton design pattern so that only one instance of the class would be created. This forced all views/controllers to communicate to the database using a single point of contact.

### Text files are messy, just use a database
Although the project specifications advised we use text files for all data management I decided to use a database. I used a database in order to perform some powerful SQL queries, which helped immensely when it came to curating user posts and deactivation of user accounts. My project utilized a local sqlite3 database for the purpose of maintaining a local database which could be turned into you. Although working with a database required more work, I believe the integrity of my project is much better and will scale much better than a project which used text files to manage data.

### Secure Project Ingredients: Hash and Salt
In addition to implementing this project with a database, I chose to implement a secure login. This included hashing and salting all user passwords and saving only a hashed password in the database. Although not required, this feature is extremely important in any real life project. I used this opportunity to teach myself more about secure authentication. I would also like to point out that I used some hashing source code found online. I included a reference to the website. My reason for doing this instead of writing my own hashing/salting algorithms is that there are smarter people out there who have already created secure hashing practices, oh and I also didn't have the time to write my own.

### Hidden Gems
There were a few hidden design patters that don't necessarily jump out at you when viewing my source code so I would like to briefly go over some of them:
- Command Pattern: whenever an action occurs the execution of that action is carried out by the controller instead of the view. The view has a list of "actions" and has the ability to delegate the handling of those actions to the controller.
- Composition: Each view contains references to each of the children components as well as the parent component. This increases the cohesiveness of my code by truly separating the root views.
- Observer Pattern: Each component contained listeners. This allowed many of my components to be notified when an event occurred as well as responding to this event.
