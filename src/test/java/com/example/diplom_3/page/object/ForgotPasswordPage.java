package com.example.diplom_3.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    public static final String FORGOT_PASSWORD_PATH = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By loginButton = By.linkText("Войти");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Click on the login button on the Forgot password page.")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }
}
