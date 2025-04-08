# Selenium Test Suite

This project demonstrates a simple Maven-based Java project using TestNG for automated testing for UI using Selenium. The goal of this project is to practice writing and executing test cases using the Selenium, TestNG framework with Maven.

## Prerequisites

Before running this project, make sure you have the following installed:

- **Java Development Kit (JDK 17 or later)**: [Download JDK](https://adoptopenjdk.net/)
- **Apache Maven**: [Download Maven](https://maven.apache.org/)
- **IDE (Optional but recommended)**: IntelliJ IDEA, Eclipse, or any other IDE with Maven support

## Getting Started

Follow these steps to set up and run the project:

### 1. Clone the Repository

Clone the project to your local machine:

```bash
git clone https://github.com/swatikucc/SeleniumTestSuite
```

### 2. Navigate to the Project Directory

Go to the project directory:

```bash
cd SeleniumTestSuite
```

### 3. Install Dependencies

Make sure all the required dependencies are downloaded by Maven:

```bash
mvn clean install
```

This will download and install all the necessary dependencies mentioned in the `pom.xml`.

### 4. Run Tests

To run the tests using Maven, execute the following command:

```bash
mvn test
```

Maven will compile the project and run the tests defined in the `src/test/java` directory using the TestNG framework.

## Viewing Test Reports

TestNG generates reports after running the tests, and these reports can be found in the following directory:

target/surefire-reports/

You can check the following report files:

- **TestNG-*.xml**: The detailed execution results in XML format.
- **.txt**: A summary of the test results, including passed, failed, and skipped tests.
- **.html**: A more user-friendly HTML report of the test results.

### Example: Viewing the HTML Report

1. After running mvn test, go to the target/surefire-reports/ directory.
2. Open the .html file (e.g., testng-results.html) in your browser.

You should see a detailed report with information about which tests passed, failed, or were skipped, including stack traces for failed tests.

## Project Structure
```bash
AutomatedTestNG/
├── src/
│    ├── main/
│    │    └── java/
│    │         └── com/
│    │              └── automation/
│    │                   └── App.java         # Main application (if any)
│    └── test/
│         └── java/
│              └── com/
│                   └── automation/
│                        └── SampleTest.java  # TestNG test cases
├── target/                             # Maven's output directory
│    └── surefire-reports/              # TestNG test results and reports
├── pom.xml                            # Maven project configuration file
└── README.md                          # Project README
```

This file contains two simple test methods that will print messages when executed.

## Build with Maven

To clean, compile, and run tests, use the following Maven commands:

- Clean: mvn clean
- Compile: mvn compile
- Test: mvn test
- Install: mvn install (installs the project into your local Maven repository)

## Troubleshooting

If you encounter any issues during setup or execution, ensure:

- You have installed the correct version of the **JDK** and **Maven**.
- You have set up the JAVA_HOME environment variable correctly.
- Your IDE (if used) is correctly configured to use the JDK and Maven.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
