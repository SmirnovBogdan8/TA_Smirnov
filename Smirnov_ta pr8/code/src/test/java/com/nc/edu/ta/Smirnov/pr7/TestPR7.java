package com.nc.edu.ta.Smirnov.pr7;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TestPR7 {

    public static FunctionalTest functionalTest;
    public static NonFunctionalTest nonFunctionalTest;

    /**
     * implementation of the initial setup
     */
    @BeforeClass
    public static void Out(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium-java-4.1.3\\selenium-chrome-driver-4.1.3-sources.jar");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://inventory.edu-netcracker.com/pages/registration.xhtml");

        functionalTest = new FunctionalTest(driver);
        nonFunctionalTest = new NonFunctionalTest(driver);
    }

    /**
     * Successful registration of a new user
     */
    @Test
    public void TC_1() {
        functionalTest.setUsername("User2 info");
        functionalTest.setPassword("User2 info");
        functionalTest.setRepeatPassword("User2 info");
        functionalTest.setEmail("userr2@testmail.com");
        functionalTest.setRole();
        functionalTest.setButtonRegister();
        String[] User = {functionalTest.getUserName(), functionalTest.getPassword(), functionalTest.getRepeatPassword(), functionalTest.getEmail()};
        for(int i = 0; i < 3; i++)
            Assert.assertEquals("User2 info", User[i]);
        Assert.assertEquals("userr2@testmail.com", User[3]);
    }

    /**
     *The value of the ‘Username’ field is mandatory and consists of a maximum of 6 characters:
     * only letters and numbers
     */
    @Test
    public void TC_2() {
        functionalTest.setUsername("Userinfo");
        functionalTest.setPassword("User2 info");
        functionalTest.setRepeatPassword("User2 info");
        functionalTest.setEmail("userr2@testmail.com");
        functionalTest.setRole();
        functionalTest.setButtonRegister();
        String[] User = {functionalTest.getUserName(), functionalTest.getPassword(), functionalTest.getRepeatPassword(), functionalTest.getEmail()};
        Assert.assertEquals("Userinfo", User[0]);
        for(int i = 1; i < 3; i++)
            Assert.assertEquals("User2 info", User[i]);
        Assert.assertEquals("Login must be alphanumeric string with length => 6 and <= 50.", nonFunctionalTest.setUser());
    }

    /**
     *The value of the 'Password' field is mandatory, the password must contain at least 8 characters,
     * at least 1 digit, at least 1 lowercase letter, at least 1 uppercase letter, at least 1
     * character not related to a letter or digit
     */
    @Test
    public void TC_3() {
        functionalTest.setUsername("User2 info");
        functionalTest.setPassword("Userinfo");
        functionalTest.setRepeatPassword("User2 info");
        functionalTest.setEmail("userr2@testmail.com");
        functionalTest.setRole();
        functionalTest.setButtonRegister();
        String[] User = {functionalTest.getUserName(), functionalTest.getPassword(), functionalTest.getRepeatPassword(), functionalTest.getEmail()};

        for(int i = 0; i < 3; i++){
            if(i == 1)
                Assert.assertEquals("Userinfo", User[1]);
            else
                Assert.assertEquals("User2 info", User[i]);
        }
        Assert.assertEquals("Password must match confirm password.", nonFunctionalTest.setPassword());
    }

    /**
     *The value of the 'Repeat Password' field must match the value of the 'Password' field
     */
    @Test
    public void TC_4() {
        functionalTest.setUsername("User2 info");
        functionalTest.setPassword("User2 info");
        functionalTest.setRepeatPassword("Userinfo");
        functionalTest.setEmail("userr2@testmail.com");
        functionalTest.setRole();
        functionalTest.setButtonRegister();
        String[] User = {functionalTest.getUserName(), functionalTest.getPassword(), functionalTest.getRepeatPassword(), functionalTest.getEmail()};

        for(int i = 0; i < 3; i++){
            if(i == 1)
                Assert.assertEquals("Userinfo", User[2]);
            else
                Assert.assertEquals("User2 info", User[i]);
        }
        Assert.assertEquals("Password must match confirm password.", nonFunctionalTest.setPassword());
    }

    /**
     * The values of the ‘Email’ field must contain only letters, numbers, one character ‘@’ and at least one character ‘.’
     */
    @Test
    public void TC_6() {
        functionalTest.setUsername("User2 info");
        functionalTest.setPassword("Userinfo");
        functionalTest.setRepeatPassword("User2 info");
        functionalTest.setEmail("User2 info");
        functionalTest.setRole();
        functionalTest.setButtonRegister();
        String[] User = {functionalTest.getUserName(), functionalTest.getPassword(), functionalTest.getRepeatPassword(), functionalTest.getEmail()};

        for(int i = 0; i < 3; i++){
            Assert.assertEquals("User2 info", User[i]);
        }
        Assert.assertEquals("Email address is incorrect.", nonFunctionalTest.setEmail());
    }

    /**
     * Switching between registration and login
     */
    @Test
    public void TC_8(){
        functionalTest.setLoginUsername("User2 info");
        functionalTest.setLoginPassword("User2 info");
        functionalTest.setButtonLogin();
        Assert.assertEquals("Your login attempt was not successful, try again.\n" +
                "Caused : Username/Password entered is incorrect.", nonFunctionalTest.setLogin());
    }


}



