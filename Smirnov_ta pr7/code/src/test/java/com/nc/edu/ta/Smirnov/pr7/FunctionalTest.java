package com.nc.edu.ta.Smirnov.pr7;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class FunctionalTest extends TestPR7 {

    /**
     * To launch the browser, you need to create a driver object
     */
    WebDriver driver;

    /**
     * Constructor with the WebDriver object
     * @param driver
     */
    FunctionalTest(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /**
     *The value of the ‘Username’ field is mandatory and consists of a maximum of 6 characters:
     * only letters and numbers
     */
    @FindBy(xpath = "//*[@id=\"registerForm:username\"]")
    private WebElement username;

    /**
     *The value of the 'Password' field is mandatory, the password must contain at least 8 characters,
     * at least 1 digit, at least 1 lowercase letter, at least 1 uppercase letter, at least 1
     * character not related to a letter or digit
     */
    @FindBy(xpath = "//*[@id=\"registerForm:password\"]")
    private WebElement password;

    /**
     *The value of the 'Repeat Password' field must match the value of the 'Password' field
     */
    @FindBy(xpath = "//*[@id=\"registerForm:confirmPassword\"]")
    private  WebElement repeatPassword;

    /**
     * The values of the ‘Email’ field must contain only letters, numbers, one character ‘@’ and at least one character ‘.’
     */
    @FindBy(xpath = "//*[@id=\"registerForm:email\"]")
    private  WebElement email;

    /**
     *  Hiding the input of the 'Password' Repeat Password' field with asterisks (*)
     */
    @FindBy(xpath = "//*[@id=\"registerForm:hide\"]")
    private WebElement unmask;

    /**
     * Switching between registration and login
     */
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[7]/td[2]/input")
    private WebElement buttonRegister;

    /**
     * Getting text in a field textBox Username
     * @param username
     */
    public void setUsername(String username) {
       this.username.sendKeys(username);
    }

    /**
     * Getting a string Passwrod
     */
    public String getUserName(){
        Label userMenu = (Label) username;
        assert false;
        return userMenu.getText();
    }

    /**
     * Getting text in a field textBox Passwrod
     * @param password
     */
    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    /**
     * Getting a string Passwrod
     */
    public String getPassword(){
        Label paswMenu = (Label) password;
        assert false;
        return paswMenu.getText();
    }

    /**
     * Getting text in a field textBox RepeatPasswrod
     * @param repeatPassword
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword.sendKeys(repeatPassword);
    }

    /**
     * Getting a string RepeatPasswrod
     */
    public String getRepeatPassword(){
        Label repPaswMenu = (Label) repeatPassword;
        assert false;
        return repPaswMenu.getText();
    }

    /**
     * Getting text in a field textBox Email
     * @param email
     */
    public void setEmail(String email) {
        this.email.sendKeys(email);
    }

    /**
     * Getting a string Email
     */
    public String getEmail(){
        Label emailMenu = (Label) email;
        assert false;
        return emailMenu.getText();
    }

    /**
     * Checking the button click
     */
    public void setButtonRegister() {
        this.buttonRegister.click();
    }

    /**
     * Checking the mark Unmask
     */
    public void setUnmask() {
        this.unmask.click();
    }

    @FindBy(xpath = "//*[@id=\"registerForm:role\"]")
    private WebElement role;

    /**
     * Сhoosing a role
     */
    public void setRole(){
        role.click();
    }

    /**
     * Switching between registration and login
     */
    @FindBy(xpath = "//*[@id=\"content\"]/form/table/tbody/tr[1]/td[2]/input")
    private WebElement LoginUsername;
    @FindBy(xpath = "//*[@id=\"content\"]/form/table/tbody/tr[2]/td[2]/input")
    private WebElement LoginPassword;
    @FindBy(xpath = "//*[@id=\"content\"]/form/table/tbody/tr[3]/td[2]/input")
    private WebElement LoginButton;

    /**
     * Getting a Login Username
     * @param login
     */
    public void setLoginUsername(String login) {
        LoginUsername.sendKeys(login);
    }

    /**
     * Getting a string Passwrod
     */
    public String getUserNameLogin(){
        Label userLogMenu = (Label) LoginUsername;
        assert false;
        return userLogMenu.getText();
    }


    /**
     * Getting a Login Password
     * @param password
     */
    public void setLoginPassword(String password) {
        LoginPassword.sendKeys(password);
    }


    /**
     * Getting a Login Passwrod
     */
    public String getLoginPassword(){
        Label paswLogMenu = (Label) LoginPassword;
        assert false;
        return paswLogMenu.getText();
    }

    /**
     * Checking the button click
     */
    public void setButtonLogin() {
        LoginButton.click();
    }
}
