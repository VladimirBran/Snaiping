package ru.sniping.ru.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }
    /**
     * Определение локатора меню пользователя
     */
    @FindBy(xpath = "//*[contains(@class, 'link-profile__thumb')]")
    private WebElement userMenu;
    /**
     * Определение локатора кнопки выхода из аккаунта
     */
    @FindBy(xpath = "//*[@id=\"settings\"]/div[1]/div[4]/a")
    private WebElement logoutBtn;
    /**
     * метод для получения имени пользователя из меню пользователя
     */
//    public String getUserName() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_email\"]")));
//        String userName = userMenu.getText();
//        return userName; }
    /**
     * метод для нажатия кнопки меню пользователя
     */
    public void entryMenu() {
        userMenu.click(); }
    /**
     * метод для нажатия кнопки выхода из аккаунта
     */
    public void userLogout() {
        logoutBtn.click(); }
}
