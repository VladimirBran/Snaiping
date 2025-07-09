package ru.sniping.ru.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FilterPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public FilterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * Определение локатора страницы "события"
     */
    @FindBy(xpath = "/html/body/div[1]/div[1]/header/div/div/div[2]/div/div/div/div[1]/ul/li[1]/a/span")
    private WebElement calendarBtn;
    /**
     * определение локатора кнопки "трен"
     */
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[2]/ul/li[6]/label")
    private WebElement trenBtn;
    /**
     * определение локатора кнопки "Long Range"
     */
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[2]/ul/li[2]/label")
    private WebElement longRangeBtn;
    /**
     * определение локатора кнопки содержащей "Снайпинг"
     */
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[3]/div[2]/div[2]/div[2]/div[1]")
    private WebElement snipingBtn;
    /**
     * определение локатора кнопки содержащей "Стерлитамак"
     */
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[3]/div[2]/div[2]/div[3]/div")
    private WebElement cityBtn;
    /**
     * определение локатора кнопки фильтра по городам
     */
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/div[2]/span[2]/span[1]/span/span[2]/b")
    private WebElement filterCityBtn;
    /**
     * определение локатора кнопки города Стерлитамак
     */
    @FindBy(xpath = "//*[@id=\"select2-city-ti-result-jezn-4\"]")
    private WebElement citySterlBtn;
    /**
     * метод для получения текста из элемента
     */
    public String getSnipingText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[3]/div[2]/div[2]/div[2]/div[1]")));
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d->false);
        String snipingText = snipingBtn.getText();
        return snipingText;
    }
    public String getCityText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div/div[3]/div[2]/div[2]/div[3]/div")));
        String cityText = cityBtn.getText();
        return cityText;
    }
    /**
     * метод для осуществления нажатия кнопки
     */
    public void clickCalendarBtn() {
        calendarBtn.click();
    }
    public void clickTrenBtn() {
        trenBtn.click();
    }
    public void clickLongRangeBtn() {
        longRangeBtn.click();
    }
    public void clickFilterCityBtn() {
        filterCityBtn.click();
    }
    public void clickCitySterlBtn() {
        citySterlBtn.click();
    }
}
