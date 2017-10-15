# Example-test
This a small project which logs depending of user's configuration.

# Requirements
- Maven
- MySQL

# Notes
- There are 2 packages structures:
  - <strong>com.test.testproject.old</strong> you can find here JobLogger class with the correspondent notes
  - <strong>com.test.testproject.newimpl</strong> you can find here JobLoggerNew class with the correspondent code fixes.
- Also, in test package you will find the unit test class.
- One unit test is ignore because it has dependency of the database creation. 
  If you want to run it, you have to create the database schema and the table.
  You can find in the resources folder the script.
