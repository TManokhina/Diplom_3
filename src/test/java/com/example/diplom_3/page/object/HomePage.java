package com.example.diplom_3.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

// page_url = https://stellarburgers.nomoreparties.site/
public class HomePage {
    private final WebDriver driver;
    public static final String HOME_PATH = "https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath("//*[text() = 'Войти в аккаунт']");
    private final By accountButton = By.xpath("//a[@href='/account' and ./p[text()='Личный Кабинет']]");
    private final By bunSection = By.xpath(".//div[span[text()='Булки']]");
    private final By saucesSection = By.xpath(".//div[span[text()='Соусы']]");
    private final By fillingSection = By.xpath(".//div[span[text()='Начинки']]");

    private final By highlightedSectionText =
            By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
    }

    public void chooseBunSection() {
        driver.findElement(bunSection).click();
    }

    public void chooseSaucesSection() {
        driver.findElement(saucesSection).click();
    }

    public void chooseFillingSection() {
        driver.findElement(fillingSection).click();
    }

    public String getHighlightedSectionText() {
        return driver.findElement(highlightedSectionText).getText();
    }

    public void waitForLoad() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(HOME_PATH));
    }
}