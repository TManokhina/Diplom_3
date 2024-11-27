package com.example.diplom_3.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class LoginPage {
    private final WebDriver driver;
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    private final By fieldForAddEmail = By.name("name");
    private final By fieldForAddPassword = By.name("Пароль");
    private final By enterButton = By.xpath(".//*[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginWithUserCreds(String email, String password) {
        driver.findElement(fieldForAddEmail).sendKeys(email);
        driver.findElement(fieldForAddPassword).sendKeys(password);
        driver.findElement(enterButton).click();
    }

    public void waitForLoad() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(LOGIN_URL));
    }



}
