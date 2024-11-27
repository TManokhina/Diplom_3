package com.example.diplom_3;

import com.example.diplom_3.api.client.UserClient;
import com.example.diplom_3.page.object.HomePage;
import com.example.diplom_3.page.object.LoginPage;
import com.example.diplom_3.page.object.ProfilePage;
import com.example.diplom_3.page.object.RegisterPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.example.diplom_3.page.object.HomePage.HOME_PATH;
import static com.example.diplom_3.page.object.LoginPage.LOGIN_URL;
import static com.example.diplom_3.page.object.RegisterPage.REGISTER_PATH;
import static org.junit.Assert.assertEquals;

public class ProfilePageRedirectTest extends BaseSetUpTest {
    private final Faker faker = new Faker();
    UserClient userClient = new UserClient();
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ProfilePage profilePage;
    private static String name;
    private static String email;
    private static String password;

    @Before
    public void setUpLogin() {
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profilePage = new ProfilePage(driver);
        name = faker.funnyName().name();
        email = faker.internet().emailAddress();
        password = faker.bothify("????????");
        driver.get(REGISTER_PATH);
        registerPage.registrationWithUserCreds(name, email, password);
        loginPage.waitForLoad();
        loginPage.loginWithUserCreds(email, password);
        homePage.waitForLoad();
        homePage.clickOnAccountButton();
        profilePage.waitForLoad();
    }

    @Test
    @Description("Проверка возможности выхода по кнопке «Выйти» в личном кабинете.")
    public void checkUnableRegistrationWithTooShortPassword() {
        profilePage.clickOnExitButton();
        loginPage.waitForLoad();
        assertEquals("Не получилось выйти по кнопке «Выйти» в личном кабинете", LOGIN_URL,
                driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка перехода из Личного кабинета по кнопке Конструктор в конструктор")
    public void checkRedirectFromConstructorButton() {
        profilePage.clickOnConstructorButton();
        homePage.waitForLoad();
        assertEquals("Не получилось перейти из Личного кабинета по кнопке Конструктор на страницу с конструктором",
                HOME_PATH, driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка перехода из Личного кабинета по кнопке Логотипа в конструктор")
    public void checkRedirectFromLogoButton() {
        profilePage.clickOnLogoButton();
        homePage.waitForLoad();
        assertEquals("Не получилось перейти из Личного кабинета по кнопке Логотипа на страницу с конструктором",
                HOME_PATH, driver.getCurrentUrl());
    }

    @After
    public void ExitTestTearDown() {
        userClient.deleteRegisteredUser(name, email, password);
    }

}
