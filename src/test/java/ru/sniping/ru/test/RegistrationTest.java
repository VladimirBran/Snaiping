package ru.sniping.ru.test;

import com.github.javafaker.Faker;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sniping.ru.ConfProperties;
import ru.sniping.ru.pages.LoginPage;
import ru.sniping.ru.pages.ProfilePage;
import ru.sniping.ru.pages.RegistrationPage;

import java.time.Duration;
import java.util.*;

import static java.lang.Thread.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationTest {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static RegistrationPage registrationPage;
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
        registrationPage = new RegistrationPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("registerpage"));
    }
    /**
     * Рандомные данные для полей формы регистрации
     */
    private String randomString(String prefix) {
        return UUID.randomUUID().toString().substring(0, 6);
    }
    private static final Faker faker = new Faker(new Locale("ru"));
    public static String getRandomFirstName(String имя) {
        return faker.name().firstName(); // Например: Иван
    }
    public static String getRandomLastName(String фамилия) {
        return faker.name().lastName(); // Например: Смирнов
    }
    public static String getRandomMiddleName(String отчество) {
        return faker.name().firstName() + "ович"; // Упрощённо: Иван + ович = Иванович
    }
    public static String getRandomCity(String город) {
        return faker.address().city(); // Например: Новосибирск
    }

    private String randomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }
    /**
     * Тестовый метод для проверки наличия элементов на странице авторизации
     */
    @Test
    public void test1_CheckAllElementsOnRegisterPage() throws InterruptedException {
        System.out.println("_____Проверка элементов на странице_____");
        Map<String, By> elements = new LinkedHashMap<>();
        elements.put("Логотип 'SNIPING.RU'", By.xpath("/html/body/div[1]/div[1]/header/div/div/a"));
        elements.put("Заголовок 'Регистрация'", By.xpath("/html/body/div[1]/div[2]/div/div/div/h1"));
        elements.put("Кнопка 'Зарегистрироваться'", By.xpath("//*[@id=\"register-form\"]/div[10]/button"));
        elements.put("Ссылка 'Политика конфиденциальности'", By.xpath("//*[@id=\"register-form\"]/div[8]/label/a"));

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
     * Тестовый метод проверки формы регистрации
     */
    @Test
    public void test2_RegistrationSuccessful() throws InterruptedException {
        System.out.println("_____Заполнение формы регистрации_____");
        String password = "Test1234!";
        registrationPage.enterEmail(randomEmail());
        registrationPage.enterFirstName(getRandomFirstName("Имя"));
        registrationPage.enterLastName(getRandomLastName("Фамилия"));
        registrationPage.enterMiddleName(getRandomMiddleName("Отчество"));
        registrationPage.enterCity(getRandomCity("Город"));
        registrationPage.enterPassword(password);
        registrationPage.enterConfirmPassword(password);
//        WebElement element = driver.findElement(By.id("id_first_rule"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        registrationPage.checkAgreement();
        registrationPage.clickRegister();
        sleep(2000);

        // Проверка — зависит от реализации
//        boolean isRegistrationSuccessful = driver.getCurrentUrl().contains("success") ||
//                driver.getPageSource().contains("Успешно");
//
//        Assert.assertTrue("Регистрация не удалась!", isRegistrationSuccessful);
    }

    @AfterClass
    public static void tearDown () {
        if (driver != null) {
            driver.quit();
        }
    }
}
