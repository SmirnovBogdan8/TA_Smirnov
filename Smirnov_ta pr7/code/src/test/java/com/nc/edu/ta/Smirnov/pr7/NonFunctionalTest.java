package com.nc.edu.ta.Smirnov.pr7;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NonFunctionalTest extends TestPR7{

    /**
     * To launch the browser, you need to create a driver object
     */
    WebDriver driver;

    /**
     * Constructor with the WebDriver object
     * @param driver
     */
    NonFunctionalTest(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /**
     *Leave all fields filled in if one or more required fields are filled in incorrectly and the 'Register'
     * button is pressed
     */
    @FindBy(xpath = "//*[@id=\"registerForm:username\"]")
    private WebElement TextBox;

    /**
     * The user can optionally set the display of the value of the ‘Password’ field in the ‘Unmask’ field
     */
    @FindBy(xpath = "//*[@id=\"registerForm:hide\"]")
    private WebElement CheckUnmaskPassword;

    /**
     * Output of messages as a result of user actions
     */
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[1]/td[3]/span")
    private WebElement OutMessageUser;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[2]/td[3]/span")
    private WebElement OutMessagePassword;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[5]/td[3]/span")
    private WebElement OutMessageEmail;
    @FindBy(xpath = "//*[@id=\"content\"]/div")
    private WebElement OutMessageLogin;

    public void getOutMessage(WebElement User, WebElement Password, WebElement Email, WebElement Login){
        this.OutMessageUser = User;
        this.OutMessagePassword = Password;
        this.OutMessageEmail = Email;
        this.OutMessageLogin = Login;
    }

    public WebElement setUser(){
        return OutMessageUser;
    }

    public WebElement setPassword(){
        return OutMessagePassword;
    }

    public WebElement setEmail(){
        return OutMessageEmail;
    }

    public WebElement setLogin(){
        return OutMessageLogin;
    }

    /**
     *In case of unsuccessful login or registration, hints are displayed in red font, where exactly the error was made
     */
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[1]/td[3]/label")
    private WebElement helpOfUserName;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[2]/td[3]/label")
    private WebElement helpOfPassword;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[3]/td[3]/label")
    private WebElement helpOfRepeatPassword;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[5]/td[3]/label")
    private WebElement helpOfEmail;
    @FindBy(xpath = "//*[@id=\"registerForm\"]/table/tbody/tr[6]/td[3]/label")
    private WebElement helpOfRole;
}
