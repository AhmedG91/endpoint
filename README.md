# EndPoint: My (Hopefully) Awesome Directory Management Challenge Solution!

Hey there! 👋

So, I tackled this directory management coding challenge, and this is my attempt at building a solution from scratch. It's not just about creating, moving, and deleting directories – I also tried to make it well-structured, scalable, and easy to extend.

**What's Inside**

*   **Composite Pattern:** I used this pattern to represent directories and files (well, just directories for now!) in a uniform way.
*   **Command Pattern:** This makes the operations (create, move, delete, list) more flexible.
*   **Path Cache:** To make things faster, I implemented a path cache that stores the locations of directories.
*   **Exception Handling:** I added custom exceptions to handle different error scenarios gracefully.
*   **Lombok:** I also used lombok to skip boiler plate getters, setters, constructors, etc.


**How to Run It**

1.  Make sure you have Java 17 (or later) and Gradle installed.
2.  Clone this repository.
3.  Run `./gradlew run` in the project root directory.
4.  The application will start and process the predefined input.

**Future Things I'd Add**

*   **More Unit Tests:** I need to add more unit tests to ensure the correctness of the code.
*   **More Robust Path Handling:** Add more validation and normalization to the path.
*   **Performance Optimizations:** Explore more efficient path caching strategies and consider iterative approaches for recursive operations.

Feel free to explore the code, offer feedback, and contribute to the project! I'm always open to learning new things and improving my skills.
