package com.example.diplom_3;

import com.example.diplom_3.api.client.UserClient;
import com.example.diplom_3.page.object.LoginPage;
import com.example.diplom_3.page.object.RegisterPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;

import static com.example.diplom_3.page.object.LoginPage.LOGIN_URL;
import static com.example.diplom_3.page.object.RegisterPage.REGISTER_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterPageTest extends BaseSetUpTest {
    private final Faker faker = new Faker();
    UserClient userClient = new UserClient();
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    static String name;
    static String email;
    static String password;

    @Before
    public void setUpLogin() {
        driver.get(REGISTER_PATH);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Проверка возможности заегистрироваться с валидными кредами пользователя (Имя, Email, Пароль)")
    public void checkRegistrationWithValidCreds() {
        name = faker.funnyName().name();
        email = faker.internet().emailAddress();
        password = faker.bothify("????????");
        registerPage.registrationWithUserCreds(name, email, password);
        loginPage.waitForLoad();
        userClient.deleteRegisteredUser(name, email, password);
        assertEquals("Не получилось зарегистрироваться с валидными кредами пользователя", LOGIN_URL,
                driver.getCurrentUrl());

    }

    @Test
    @Description("Проверка возможности зарегистрироваться с граничным корректным значением пароля - 6 символов")
    public void checkRegistrationWithBoundaryValuePassword() {
        name = faker.funnyName().name();
        email = faker.internet().emailAddress();
        password = faker.bothify("??????");
        registerPage.registrationWithUserCreds(name, email, password);
        loginPage.waitForLoad();
        userClient.deleteRegisteredUser(name, email, password);
        assertEquals("Не получилось зарегистрироваться с граничным корректным значением пароля - 6 символов", LOGIN_URL,
                driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка, что невозможно зарегистрироваться с паролем меньше 6 символов")
    public void checkUnableRegistrationWithTooShortPassword() {
        name = faker.funnyName().name();
        email = faker.internet().emailAddress();
        password = faker.bothify("?????");
        registerPage.registrationWithUserCreds(name, email, password);
        assertTrue("Получилось зарегистрироваться с паролем меньше 6 символов",
                registerPage.errorTextFieldIsDisplayed());
    }


}
