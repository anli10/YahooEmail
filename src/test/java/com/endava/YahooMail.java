package com.endava;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class YahooMail {
    private static WebDriver webDriver;

    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver","C:/Users/bghiuta/Documents/My Received Files/chromedriver.exe");
        webDriver=new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Before
    public void beforeTest(){
        webDriver.get("https://mail.yahoo.com");
    }

    @AfterClass
    public static void closeBrowser(){
        //webDriver.close();
    }

    @Test
    public void SendEmail(){

        WebElement usernameField=webDriver.findElement(By.id("login-username"));
        usernameField.sendKeys(" ");

        WebElement nextButton=webDriver.findElement(By.id("login-signin"));
        nextButton.click();

        WebElement passwordField=webDriver.findElement(By.id("login-passwd"));
        passwordField.sendKeys("  ");

        WebElement signinButton=webDriver.findElement(By.id("login-signin"));
        signinButton.click();

        WebElement mailButton=webDriver.findElement(By.id("Compose"));
        mailButton.click();

        WebElement toField=webDriver.findElement(By.id("to-field"));
        toField.sendKeys(" ");

        WebElement subjectField=webDriver.findElement(By.id("subject-field"));
        subjectField.sendKeys("test");

        WebElement contentField=webDriver.findElement(By.id("rtetext"));
        contentField.sendKeys("content test");

        WebElement sendButton=webDriver.findElement(By.xpath("//span[@class='btn default']"));
        sendButton.click();

        WebElement firstMailButton=webDriver.findElement(By.xpath("(//*[@class=\"from\"])[1]"));
        firstMailButton.click();

        WebElement textBox=webDriver.findElement(By.xpath("//*[@class=\"email-wrapped\"]/div/div/div/div"));
        String contentText=textBox.getText();
        //System.out.println(contentText);
        Assert.assertEquals("content test",contentText);

        WebElement sentButton=webDriver.findElement(By.id("Sent"));
        sentButton.click();

        WebElement fromField=webDriver.findElement(By.xpath("(//*[@class=\"from\"])[1]"));
        String name=fromField.getText();
        System.out.println(name);
        Assert.assertEquals("",name);

        WebElement profileButton=webDriver.findElement(By.id("yucs-profile"));
        profileButton.click();

        WebElement signOut=webDriver.findElement(By.id("yucs-signout"));
        signOut.click();
    }

}

