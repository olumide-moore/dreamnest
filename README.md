# CS2TP Team 3 (Dreamnest)

Dreamnest E-commerce website aims to sell room enhancement items such as artworks, lamps and other room decor items  to customers who want to elevate the look of their rooms, whether their goal is to add some life with lights or change their room to look like an art gallery with artworks. 

To achieve this, we use Vaadin, a Java framework, to build the application in conjunction with MySQL to store the data displayed on the website and to store users information.

## Getting Started
### Prerequisites
- Java JDK 11 or later
- Maven
- MySQL
## Running the application
1. Clone the repository using git clone https://github.com/olumide-moore/dreamnest.git.
2. Open the project in your preferred IDE (IntelliJ or Eclipse).
3. Set up a new MySQL database with the name "dreamnest".
4. Modify the username and password for the MySQL database in the project  'src/main/resources/application.properties.' (Once this is done, the application automatically creates the required tables when run.)
5. Run the application either from the IDE or command line using Maven by typing `mvnw` (Windows) or `./mvnw` (Mac & Linux) from the project root, then open http://localhost:8080 in your browser.

## Add test products
To further simplify the process, we have included an SQL query file in the 'sql-query/ourproducts.sql' directory, which can be run to add test products.

## Project structure


The project structure of Dreamnest E-commerce website is organized in a way that makes it easy to understand and navigate. Here are some additional details about the project structure:

- Views: The views of the application are located in the `src/main/resources/templates folder`. These views are responsible for rendering the user interface of the application.

- CSS, Images and JavaScripts: The CSS styles, images, and JavaScript files are located in the `src/main/resources/static` folder. These files are used to style the views and add interactivity to the user interface.

- Entities, Repositories, Services and Controllers: The `src/main/java/com/example/application/data` folder contains the backend logic of the application. The entities represent the data model of the application, while the repositories provide a way to interact with the database. The services contain the business logic of the application and the controllers handle the requests and responses of the HTTP protocol.

- Tests: The tests of the application are located in the `src/test` folder. These tests ensure that the application is working correctly and they can be run before deploying the application to production.

The project structure is designed to separate the different components of the application, making it easy to maintain and extend the functionality of the application.

## Deploying to Production

To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/flowcrmtutorial-1.0-SNAPSHOT.jar`

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training](https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- View use case applications that demonstrate Vaadin capabilities at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Discover Vaadin's set of CSS utility classes that enable building any UI without custom CSS in the [docs](https://vaadin.com/docs/latest/ds/foundation/utility-classes). 
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/platform).
