package com.example.diplom_3.page.object;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    public static final String REGISTER_PATH = "https://stellarburgers.nomoreparties.site/register";
    private final By loginButton = By.linkText("Войти");
    private final By fieldForAddPassword = By.name("Пароль");
    private final By fieldForAddName = By.xpath("//div[./label[text()='Имя']]/input");
    private final By fieldForAddEmail = By.xpath("//div[./label[text()='Email']]/input");
    private final By registrationButton = By.xpath("//*[text()='Зарегистрироваться']");
    private final By errorTextFieldPassword = By.xpath("//*[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean errorTextFieldIsDisplayed() {
        return driver.findElement(errorTextFieldPassword).isDisplayed();
    }

    public void registrationWithUserCreds(String name, String email, String password) {
        driver.findElement(fieldForAddName).sendKeys(name);
        driver.findElement(fieldForAddEmail).sendKeys(email);
        driver.findElement(fieldForAddPassword).sendKeys(password);
        driver.findElement(registrationButton).click();
    }

}
