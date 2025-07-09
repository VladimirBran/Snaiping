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
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import static java.lang.Thread.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalendarPageTest {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static FilterPage filterPage;
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
        filterPage = new FilterPage(driver);
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
    public void test1_loginTest() {
        //получение доступа к методам класса LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver и loginpage
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //получаем отображаемый логин
    }
    /**
     * Тестовый метод для проверки фильтрации только по типу соревнований "Снайпинг"
     */
    @Test
    public void test2_FilterCategories() throws InterruptedException {
        System.out.println("_____Проверка фильтрации  по типу соревнований 'Снайпинг'_____");
        filterPage.clickCalendarBtn();
        filterPage.clickTrenBtn();
        sleep(2000);
        filterPage.clickLongRangeBtn();
        sleep(5000);
        //получаем отображаемый текст
        String snipingText = filterPage.getSnipingText();
        //и сравниваем его с логином из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("snipingText"), snipingText);
        System.out.println("Успешно проверено соответствие текста 'Снайпинг': " + snipingText);
        sleep(2000);
    }
    /**
     * Тестовый метод для проверки по фильтрам "Все города"
     */
    @Test
    public void test3_FilterCity() throws InterruptedException {
        System.out.println("_____Проверка фильтрации  по фильтрам 'Все города'_____");
        filterPage.clickFilterCityBtn();
        sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(), 'Стерлитамак')]")    ////ищем Стерлитамак в выпадающем списке
        ));
        // прокручиваем, находим и кликаем
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        sleep(2000);
        //получаем отображаемый текст
        String cityText = filterPage.getCityText();
        //и сравниваем его с текстом из файла настроек
        Assert.assertEquals(ConfProperties.getProperty("cityText"), cityText);
        System.out.println("Успешно проверено соответствие текста 'г.Стерлитамак': " + cityText);
    }
    /**
     * Тестовый метод для проверки наличия элементов
     */
    @Test
    public void test4_CheckAllElements() {
        System.out.println("_____Проверка элементов на странице_____");
        List<By> elementLocators = Arrays.asList(
                By.xpath("/html/body/div[1]/div[1]/header/div/div/a"),    // SNIPING.RU
                By.xpath("//*[@id=\"add-com\"]"),       //Добавить событие
                By.xpath("/html/body/div[1]/div[2]/div/div/div/div[2]/span[5]/span[1]/span"),   //фильтр "Все статусы"
                By.xpath("//*[@id=\"id_event-search\"]")     // строка поиска "мероприятие"
        );
         // Проверяем, что каждый элемент существует
        for (By locator : elementLocators) {
            try {
                WebElement element = driver.findElement(locator);
                Assert.assertNotNull(String.valueOf(element), "Элемент с локатором '" + locator + "' не найден.");
                System.out.println("Элемент с локатором '" + locator + "' найден.");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                Assert.fail("Элемент с локатором '" + locator + "' не найден.");
            }
        }
    }

    @AfterClass
    public static void tearDown () {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit();
    }
}

