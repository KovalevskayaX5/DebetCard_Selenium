package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebetCardSelenuimTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\Downloads\\aqa-code\\api-ci\\DebetCard_Selenium\\driver\\chromedriver.exe");
    }


    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:7777");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void positiveTest1() {
        //WebElement form = driver.findElement(By.cssSelector("[data-test-id=callback-form]"));
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Ковалевская Яна");
        elements.get(1).sendKeys("+79986340085");
        driver.findElement(By.className("checkbox__control")).click();
        driver.findElement(By.className("button__text")).click();
        String actual = driver.findElement(By.className("order-success")).getText().trim();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);


    }
}
