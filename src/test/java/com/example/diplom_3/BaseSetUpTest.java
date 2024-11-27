package com.example.diplom_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseSetUpTest {
    public static final String YANDEX_DRIVER_ENV = "YADRIVER";
    public static final String YANDEX_BROWSER_ENV = "YAPATH";
    public static final String YANDEX_BROWSER = "Yandex";
    public static final String CHROME_BROWSER = "Chrome";
    public static final String TARGET_BROWSER_PROPERTY = "browser";

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        String targetBrowser = System.getProperty(TARGET_BROWSER_PROPERTY, CHROME_BROWSER);
        driver = YANDEX_BROWSER.equalsIgnoreCase(targetBrowser)
                ? createYandexDriver()
                : createChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    private ChromeDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private ChromeDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", System.getenv(YANDEX_DRIVER_ENV));
        ChromeOptions options = new ChromeOptions().addArguments("--no-sandbox", "--disable-dev-shm-usage")
                .setBinary(System.getenv(YANDEX_BROWSER_ENV));
        return new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        //закрываем браузер
        driver.quit();
    }
}
