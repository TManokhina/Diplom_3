package com.example.diplom_3.page.object;

import io.qameta.allure.Step;
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
    @Step("Click on the login button on the Home page.")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Click on the account button on the Home page.")
    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
    }
    @Step("Choose a section of buns in the Constructor on the Home page.")
    public void chooseBunSection() {
        driver.findElement(bunSection).click();
    }
    @Step("Choose a section of sauces in the Constructor on the Home page.")
    public void chooseSaucesSection() {
        driver.findElement(saucesSection).click();
    }
    @Step("Choose a section of fillings in the Constructor on the Home page.")
    public void chooseFillingSection() {
        driver.findElement(fillingSection).click();
    }
    @Step("Get the text of the highlighted section in Constructor on the Home page.")
    public String getHighlightedSectionText() {
        return driver.findElement(highlightedSectionText).getText();
    }
    @Step("Wait for the Home page to load.")
    public void waitForLoad() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(urlToBe(HOME_PATH));
    }
}