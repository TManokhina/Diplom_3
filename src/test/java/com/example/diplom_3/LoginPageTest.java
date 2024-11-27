package com.example.diplom_3;

import com.example.diplom_3.api.client.UserClient;
import com.example.diplom_3.page.object.HomePage;
import com.example.diplom_3.page.object.LoginPage;
import com.example.diplom_3.page.object.RegisterPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;

import static com.example.diplom_3.page.object.HomePage.HOME_PATH;
import static com.example.diplom_3.page.object.LoginPage.LOGIN_URL;
import static com.example.diplom_3.page.object.RegisterPage.REGISTER_PATH;
import static org.junit.Assert.*;

public class LoginPageTest extends BaseSetUpTest {
    private static String name;
    private static String email;
    private static String password;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    private final Faker faker = new Faker();
    UserClient userClient = new UserClient();
    @Before
    public void setUpLogin() throws Exception {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        name = faker.funnyName().name();
        email = faker.internet().emailAddress();
        password = faker.bothify("????????");
        driver.get(REGISTER_PATH);
        registerPage.registrationWithUserCreds(name, email, password);
        loginPage.waitForLoad();
    }

    @Test
    @Description("Проверка возможности залогиниться с валидными кредами пользователя (Email, Пароль)")
    public void checkLogin() {
        loginPage.loginWithUserCreds(email, password);
        homePage.waitForLoad();
        assertEquals("Не получилось залогиниться", HOME_PATH, driver.getCurrentUrl());
    }
    @Test
    @Description("Проверка отсутствия возможности залогиниться с не валидным паролем")
    public void checkLoginWithIncorrectPassword() {
        loginPage.loginWithUserCreds(email, faker.bothify("??????"));
        assertThrows(TimeoutException.class, () -> homePage.waitForLoad());
        assertNotEquals("Получилось залогиниться с некорректным паролем", HOME_PATH, driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка отсутствия возможности залогиниться с не валидным Email пользователя")
    public void checkLoginWithIncorrectEmail() {

        loginPage.loginWithUserCreds(faker.internet().emailAddress(), password);
        assertThrows(TimeoutException.class, () -> homePage.waitForLoad());
        assertNotEquals("Получилось залогиниться с некорректным email", HOME_PATH, driver.getCurrentUrl());

    }
    @After
    public void ExitTestTearDown() {
        userClient.deleteRegisteredUser(name, email, password);
    }
}
