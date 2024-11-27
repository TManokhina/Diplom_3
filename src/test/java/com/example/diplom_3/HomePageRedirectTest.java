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

import static com.example.diplom_3.page.object.ProfilePage.PROFILE_PATH;
import static com.example.diplom_3.page.object.RegisterPage.REGISTER_PATH;
import static org.junit.Assert.assertEquals;

public class HomePageRedirectTest extends BaseSetUpTest {

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
    }

    @Test
    @Description("Проверка перехода из главной страницы в «Личный кабинет»")
    public void checkLogin() {
        homePage.clickOnAccountButton();
        profilePage.waitForLoad();
        assertEquals("Не получилось залогиниться", PROFILE_PATH, driver.getCurrentUrl());
    }

    @After
    public void ExitTestTearDown() {
        userClient.deleteRegisteredUser(name, email, password);
    }

}
