package ru.sniping.ru.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RangePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public RangePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * Определение локатора кнопки "тиры и стрельбища"
     */
    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div/div/div[2]/div/div/div/div[1]/ul/li[3]/a/span")
    private WebElement rangeBtn;
    /**
     * определение локатора кнопки "Добавить"
     */
    @FindBy(xpath = "/html/body/div[1]/div[2]/section/div/div/button")
    private WebElement addBtn;
    /**
     * определение локатора выпадающего списка "ГОРОД"
     */
    @FindBy(xpath = "//*[@id=\"shooting-ranges-form\"]/div[2]/div/div[2]/div/div[1]")
    private WebElement cityListRangeBtn;
    /**
     * определение локатора города Оренбург из выпадающего списка "ГОРОД"
     */
    @FindBy(xpath = "//*[@id=\"shooting-ranges-form\"]/div[2]/div/div[2]/div/div[2]/div[38]")
    private WebElement cityOrenListBtn;
    /**
     * определение локатора элемента содержащего "ССК Полигон"
     */
    @FindBy(xpath = "//*[@id=\"shooting-ranges-form\"]/div[2]/ul/li/div/div[2]/div[1]/a/span")
    private WebElement sskPoligonBtn;
    /**
     * определение локатора элемента содержащего "г.Оренбург"
     */
    @FindBy(xpath = "//*[@id=\"shooting-ranges-form\"]/div[2]/ul/li/div/div[2]/div[2]")
    private WebElement elementOrenBtn;
    /**
     * метод для получения текста из элемента
     */
    public String getCityOrenText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shooting-ranges-form\"]/div[2]/ul/li/div/div[2]/div[2]")));
        String cityOrenText = elementOrenBtn.getText();
        return cityOrenText;
    }
    public String getSskPoligonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shooting-ranges-form\"]/div[2]/ul/li/div/div[2]/div[1]/a/span")));
        String sskPoligonText = sskPoligonBtn.getText();
        return sskPoligonText;
    }
    /**
     * метод для осуществления нажатия кнопки
     */
    public void clickRangeBtn() {
        rangeBtn.click();
    }
    public void clickAddBtn() {
        addBtn.click();
    }
    public void clickCityListRangeBtn() {
        cityListRangeBtn.click();
    }
    public void clickCityOrenListBtn() {
        cityOrenListBtn.click();
    }
    public void clickSskPoligonBtn() {
        sskPoligonBtn.click();
    }
}