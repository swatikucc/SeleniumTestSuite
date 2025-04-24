package com.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AppTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
