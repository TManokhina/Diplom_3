package com.example.diplom_3;

import com.example.diplom_3.page.object.HomePage;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;

import static com.example.diplom_3.page.object.HomePage.HOME_PATH;
import static org.junit.Assert.assertEquals;

public class HomePageTest extends BaseSetUpTest {

    HomePage homePage;

    @Before
    public void setUpLogin() {
        driver.get(HOME_PATH);
        homePage = new HomePage(driver);
    }

    @Test
    @Description("Проверка возможности перехода к разделу Булки")
    public void checkScrollToBunSection() {
        homePage.chooseSaucesSection();
        homePage.chooseBunSection();
        assertEquals("Не получилось перейти в раздел Булки", "Булки",
                homePage.getHighlightedSectionText());
    }

    @Test
    @Description("Проверка возможности перехода к разделу Соусы")
    public void checkScrollToSaucesSection() {
        homePage.chooseSaucesSection();
        assertEquals("Не получилось перейти в раздел Соусы", "Соусы",
                homePage.getHighlightedSectionText());
    }

    @Test
    @Description("Проверка возможности перехода к разделу Начинки")
    public void checkScrollToFillingSection() {
        homePage.chooseFillingSection();
        assertEquals("Не получилось перейти в раздел Начинки", "Начинки",
                homePage.getHighlightedSectionText());
    }


}
