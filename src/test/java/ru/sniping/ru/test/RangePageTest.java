package ru.sniping.ru.test;


import org.junit.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sniping.ru.ConfProperties;
import ru.sniping.ru.pages.FilterPage;
import ru.sniping.ru.pages.LoginPage;
import ru.sniping.ru.pages.ProfilePage;
import ru.sniping.ru.pages.RangePage;

import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RangePageTest {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static RangePage rangePage;
    public static WebDriver driver;
    /**
     * Осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        rangePage = new RangePage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));
    }
    /**
     * Тестовый метод для осуществления аутентификации
     */
    @Test
    public void test1_loginTest() throws InterruptedException {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        sleep(2000);
        rangePage.clickRangeBtn();
        sleep(2000);
    }
    /**
     * Тестовый метод для проверки наличия элементов на странице
     */
    @Test
    public void test2_CheckAllElementsOnRangePage() throws InterruptedException {
        System.out.println("_____Проверка элементов на странице_____");
        Map<String, By> elements = new LinkedHashMap<>();
        elements.put("Логотип 'SNIPING.RU'", By.xpath("/html/body/div[1]/div[1]/header/div/div/a"));
        elements.put("Заголовок 'Тиры и стрельбища'", By.xpath("/html/body/div[1]/div[2]/section/div/h2"));
        elements.put("Кнопка 'Добавить'", By.xpath("/html/body/div[1]/div[2]/section/div/div/button"));
        elements.put("Выпадающий список 'ГОРОД'", By.xpath("//*[@id=\"shooting-ranges-form\"]/div[2]/div/div[2]/div/div[1]"));

        for (Map.Entry<String, By> entry : elements.entrySet()) {
            String name = entry.getKey();
            By locator = entry.getValue();
            try {
                WebElement element = driver.findElement(locator);
                Assert.assertNotNull(String.valueOf(element), "Элемент '" + name + "' не найден.");
                System.out.println("Элемент '" + name + "' найден.");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                Assert.fail("Элемент '" + name + "' не найден.");
            }
        }
        sleep(2000);
    }
    /**
     * Тестовый метод для проверки сортировки по фильтру "ГОРОД"
     */
    @Test
    public void test3_FilterOrenCity() throws InterruptedException {
        rangePage.clickCityListRangeBtn();
        sleep(2000);
        System.out.println("_____Проверка сортировки по фильтру 'г.Оренбург'_____");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"shooting-ranges-form\"]/div[2]/div/div[2]/div/div[2]/div[38]")    ////ищем Оренбург в выпадающем списке
        ));
        // прокручиваем, находим и кликаем
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        sleep(2000);
        //получаем отображаемый текст
        String cityOrenText = rangePage.getCityOrenText();
        //и сравниваем его с текстом из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("cityOrenText"), cityOrenText);
        System.out.println("Успешно проверено соответствие текста 'г. Оренбург': " + cityOrenText); // Добавлено сообщение
        //получаем отображаемый текст
        String sskPoligonText = rangePage.getSskPoligonText();
        //и сравниваем его с текстом из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("sskPoligonText"), sskPoligonText);
        System.out.println("Успешно проверено соответствие текста 'ССК Полигон': " + sskPoligonText); // Добавлено сообщение
        sleep(2000);
    }
    /**
     * Выход из браузера
     */
    @AfterClass
    public static void tearDown () {
        if (driver != null) {
            profilePage.entryMenu();
            profilePage.userLogout();
            driver.quit();
        }
    }
}





