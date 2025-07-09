package ru.sniping.ru.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * Определение локатора поля "email"
     */
//    private By emailField = By.xpath("//*[@id=\"id_email\"]");
    @FindBy(xpath = "//*[@id=\"id_email\"]")
    private WebElement emailField;
    /**
     * определение локатора поля "Фамилия"
     */
    @FindBy(xpath = "//*[@id=\"id_last_name\"]")
    private WebElement lastNameField;
    /**
     * определение локатора поля "Имя"
     */
    @FindBy(xpath = "//*[@id=\"id_first_name\"]")
    private WebElement firstNameField;
    /**
     * определение локатора поля "Отчество"
     */
    @FindBy(xpath = "//*[@id=\"id_middle_name\"]")
    private WebElement middleNameField;
    /**
     * определение локатора поля "Город"
     */
    @FindBy(xpath = "//*[@id=\"id_city\"]")
    private WebElement cityField;
    /**
     * определение локатора поля "Пароль"
     */
    @FindBy(xpath = "//*[@id=\"id_password1\"]")
    private WebElement passwordField;
    /**
     * определение локатора поля "Пароль ещё раз"
     */
    @FindBy(xpath = "//*[@id=\"id_password2\"]")
    private WebElement confirmPasswordField;
    /**
     * определение локатора чекбокса "обработка перс данных"
     */
//    private By agreementCheckbox = By.xpath("//*[@id=\"register-form\"]/div[8]/label");
//    @FindBy(xpath = "//label[@for='id_first_rule']")
//    @FindBy(xpath = "//*[@id='id_first_rule']")
//    private WebElement agreementCheckbox;
    /**
     * определение локатора кнопки "Регистрация"
     */
    @FindBy(xpath = "//*[@id=\"register-form\"]/div[10]/button")
    private WebElement registerButton;
//    /**
//     * метод для получения текста из элемента
//     */
//    public String getCityText() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[3]/div[2]/div[2]/div[3]/div")));
//        String cityText = cityBtn.getText();
//        return cityText;
//    }
    /**
     * метод для осуществления действий с полями
     */
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }
    public void enterMiddleName(String middleName) {
        middleNameField.sendKeys(middleName);
    }
    public void enterCity(String city) {
        cityField.sendKeys(city);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void enterConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
    }
    public void checkAgreement() {
        WebElement element = driver.findElement(By.id("id_first_rule"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
//    public void checkAgreement() {
//        if (!agreementCheckbox.isSelected()) {
//            agreementCheckbox.click();
//        }
//    }
    public void clickRegister() {
        registerButton.click();
    }
}
