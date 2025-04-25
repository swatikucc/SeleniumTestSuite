package com.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AppTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // Add a unique user data directory to avoid conflicts


        // Create a guaranteed unique directory
        Path userDataDir = Files.createTempDirectory("chrome-profile");
        System.out.println("Using temp user-data-dir: " + userDataDir.toAbsolutePath());

        options.addArguments("--user-data-dir=" + userDataDir.toAbsolutePath());

        // These flags are important in CI
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    // Test valid login
    @Test(priority = 1)
    public void validLoginTest() {
        driver.get("https://the-internet.herokuapp.com/login");

        // Enter valid credentials
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Assert that the login is successful (check for logout link)
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutLink.isDisplayed(), "Login failed! Logout link not found.");
    }

    // Test invalid login
    @Test(priority = 2)
    public void invalidLoginTest() {
        driver.get("https://the-internet.herokuapp.com/login");

        // Enter invalid credentials
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("wronguser");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("wrongpassword");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Assert that an error message is displayed
        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login.");
        Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"), "Error message content mismatch.");
    }


    @Test(priority = 3)
    public void validclickcheckboxTest() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        //clickcheckbox
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        checkbox.click();
        // Assert that the checkbox is checked (selected)
        Assert.assertTrue(checkbox.isSelected(), "Checkbox is not checked!");
    }

    @Test(priority = 4)
    public void validAddElementTest() {
        driver.get("https://the-internet.herokuapp.com");
        //addelement
        WebElement addElement = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[2]/a"));
        addElement.click();
        WebElement addItem  = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        addItem.click();
        WebElement  deleteItem  = driver.findElement(By.xpath("//*[@id=\"elements\"]/button"));
        Assert.assertTrue(deleteItem.isDisplayed(), "DeleteItem is not displayed!");
        deleteItem.click();
        //Assert.assertFalse(deleteItem.isDisplayed(), "DeleteItem is displayed!");
        try {
            driver.findElement(By.xpath("//*[@id=\"elements\"]/button"));
            Assert.fail("Element was expected to be removed, but it was found!");
        } catch (NoSuchElementException e) {
            // Element is not found, hence it's removed from the DOM
            Assert.assertTrue(true, "Element is removed from the DOM");
        }

    }

    @AfterMethod
    public void tearDown () {
            if (driver != null) {
                driver.quit();
            }
    }

}

