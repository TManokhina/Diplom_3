package com.example.diplom_3;

import com.example.diplom_3.page.object.ForgotPasswordPage;
import com.example.diplom_3.page.object.HomePage;
import com.example.diplom_3.page.object.LoginPage;
import com.example.diplom_3.page.object.RegisterPage;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;

import static com.example.diplom_3.page.object.ForgotPasswordPage.FORGOT_PASSWORD_PATH;
import static com.example.diplom_3.page.object.HomePage.HOME_PATH;
import static com.example.diplom_3.page.object.LoginPage.LOGIN_URL;
import static com.example.diplom_3.page.object.RegisterPage.REGISTER_PATH;
import static org.junit.Assert.assertEquals;

public class RedirectToLoginPageTest extends BaseSetUpTest {

    protected LoginPage loginPage;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected ForgotPasswordPage forgotPasswordPage;
    @Before
    public void setUpRedirect() throws Exception {
        loginPage = new LoginPage(driver);
    }

    @Test
    @Description("Проверка перехода по кнопке «Войти в аккаунт» на главной странице к странице логина")
    public void checkRedirectFromHomePageLogin() {
        driver.get(HOME_PATH);
        homePage = new HomePage(driver);
        homePage.clickOnLoginButton();
        loginPage.waitForLoad();
        assertEquals("Не получилось перейти на страницу логина по кнопке «Войти в аккаунт» на главной странице",LOGIN_URL, driver.getCurrentUrl());
    }
    @Test
    @Description("Проверка перехода по кнопке «Войти в аккаунт» на главной странице к странице логина")
    public void checkRedirectFromHomePagePersonalAccount() {
        driver.get(HOME_PATH);
        homePage = new HomePage(driver);
        homePage.clickOnAccountButton();
        loginPage.waitForLoad();
        assertEquals("Не получилось перейти на страницу логина по кнопке «Войти в аккаунт» на главной странице",LOGIN_URL, driver.getCurrentUrl());
    }
    @Test
    @Description("Проверка перехода через кнопку в форме регистрации к странице логина")
    public void checkRedirectFromRegisterPage() {
        driver.get(REGISTER_PATH);
        registerPage = new RegisterPage(driver);
        registerPage.clickOnLoginButton();
        loginPage.waitForLoad();
        assertEquals("Не получилось перейти на страницу логина через кнопку в форме регистрации",LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка перехода по кнопке из формы восстановления пароля")
    public void checkRedirectFromForgotPasswordPage() {
        driver.get(FORGOT_PASSWORD_PATH);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickOnLoginButton();
        loginPage.waitForLoad();
        assertEquals("Не получилось перейти на страницу логина через кнопку в форме восстановления пароля",LOGIN_URL, driver.getCurrentUrl());
    }

}
