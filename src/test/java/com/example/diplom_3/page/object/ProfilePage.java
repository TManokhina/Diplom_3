package com.example.diplom_3.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class ProfilePage {
    private final WebDriver driver;
    public static final String PROFILE_PATH = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By constructorButton = By.linkText("Конструктор");
    private final By logoButton = By.cssSelector("svg[viewBox='0 0 290 50']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    public void clickOnLogoButton() {
        driver.findElement(logoButton).click();
    }
    public void clickOnExitButton() {
        driver.findElement(exitButton).click();
    }

    public void waitForLoad() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(PROFILE_PATH));
    }

}