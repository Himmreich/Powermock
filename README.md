# Powermock Application Tests

This repository contains test cases for the `IOUtils` class using PowerMockito. The tests are implemented as JUnit tests to verify the behavior of __static__ methods.

## Overview

The purpose of this project is to demonstrate how to use PowerMockito to test methods that interact with __static__ methods. In this example, we're testing the behavior of the `IOUtils.toString(URI, Charset)` method from the `IOUtils` class.

## Getting Started

To run the tests, follow these steps:

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/Himmreich/Powermock.git

2. Navigate to the project directory:

   ```bash
   cd Powermock

3. Build and run the tests using Maven:

   ```bash
   mvn test

## Dependencies

The following dependencies are used in this project:

- **JUnit**: A testing framework for Java.
- **Mockito**: A mocking framework for unit tests.
- **PowerMockito**: An extension of Mockito to mock static and final methods.
- **SpringBoot**: A framework for building Java applications with minimal setup.
- **CommonsIO**: A library for common input/output operations.
- **Lombok**: A library for reducing boilerplate code in Java.

## Project Structure

The project is structured as follows:

- **`src/main`**: Contains the main application code (not relevant to this demonstration).
- **`src/test`**: Contains the test classes for the IOUtils testing using PowerMockito.

## Test Explanation

The `PowermockApplicationTests` class contains a test method `contextLoads` that demonstrates the usage of PowerMockito to test the behavior of the `IOUtils.toString(URI, Charset)` method.

1. We use `@PrepareForTest(IOUtils.class)` to indicate that we're preparing the `IOUtils` class for static mocking.

2. Within the `contextLoads` test method:
   - We mock the static methods of the `IOUtils` class using `PowerMockito.mockStatic(IOUtils.class)`.
   - We define the behavior of the mocked `IOUtils.toString` method to return "test" when called with any `URI` and `Charset`.
   - We call the actual method being tested, i.e., `IOUtils.toString(new ClassPathResource("fakeFile").getURI(), StandardCharsets.UTF_8)`.
   - We use `Assert.assertEquals` to verify that the returned value matches the expected value "test".
   - We use `PowerMockito.verifyStatic(IOUtils.class)` to ensure that the `IOUtils.toString` method was called.

## Maven Surefire Plugin Configuration

The project's `pom.xml` file includes a configuration for the `maven-surefire-plugin` to address potential issues with reflective access in Java 17.

```xml
<build>
    <plugins>
        <!-- ... Other plugins ... -->

        <!-- Configure Maven Surefire Plugin -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <argLine>--add-opens java.base/java.lang=ALL-UNNAMED</argLine>
            </configuration>
        </plugin>
    </plugins>
</build>

```

Explanation:

The maven-surefire-plugin is responsible for running tests. In some cases, when working with Java 17, you might encounter issues related to reflective access. To mitigate these issues, the <argLine> configuration is used to pass additional JVM arguments.

`--add-opens java.base/java.lang=ALL-UNNAMED` is a JVM argument that allows reflective access to all unnamed modules from java.base (the Java SE Platform Module containing fundamental classes like java.lang) to resolve any potential reflective access warnings.

This configuration helps ensure a smooth test execution experience, especially when using newer Java versions.

Note: It's important to regularly review and update configurations based on changes in Java versions and libraries.

For more information on Java module system and reflective access, refer to the official Java documentation: Java Platform, Standard Edition Tools Reference.
