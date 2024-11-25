package com.example.diplom_3;

import com.example.diplom_3.page.object.HomePage;
import com.example.diplom_3.page.object.LoginPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import static com.example.diplom_3.page.object.HomePage.BASE_URI_PATH;
import static com.example.diplom_3.page.object.LoginPage.LOGIN_URL;
import static org.junit.Assert.*;

public class LoginPageTest extends BaseSetUpTest {
    public static final String CORRECT_MAIL = "jason-bornkarlos3@yandex.ru";
    public static final String CORRECT_PASSWORD = "NoobNoobSai789";
    protected LoginPage loginPage;
    protected HomePage homePage;
    private final Faker faker = new Faker();


    @Before
    public void setUpLogin() throws Exception {
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    @Description("Проверка возможности залогиниться с валидными кредами пользователя (Email, Пароль)")
    public void checkLogin() {
        loginPage.loginWithUserCreds(CORRECT_MAIL, CORRECT_PASSWORD);
        homePage.waitForLoad();
        assertEquals("Не получилось залогиниться", BASE_URI_PATH, driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка отсутствия возможности залогиниться с не валидным паролем")
    public void checkLoginWithIncorrectPassword() {
        loginPage.loginWithUserCreds(CORRECT_MAIL, faker.bothify("??????"));
        assertThrows(TimeoutException.class, () -> homePage.waitForLoad());
        assertNotEquals("Получилось залогиниться с некорректным паролем", BASE_URI_PATH, driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка отсутствия возможности залогиниться с не валидным Email пользователя")
    public void checkLoginWithIncorrectEmail() {

        loginPage.loginWithUserCreds(faker.internet().emailAddress(), CORRECT_PASSWORD);
        assertThrows(TimeoutException.class, () -> homePage.waitForLoad());
        assertNotEquals("Получилось залогиниться с некорректным email", BASE_URI_PATH, driver.getCurrentUrl());

    }

}
