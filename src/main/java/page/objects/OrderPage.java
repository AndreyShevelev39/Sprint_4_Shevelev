package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stationInput = By.className("select-search__input");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    private final By blackColor = By.id("black");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By finalOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By orderSuccessHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstStep(String name, String surname, String address, String stationName, String phone) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);

        driver.findElement(stationInput).click();
        driver.findElement(stationInput).sendKeys(stationName);
        driver.findElement(stationInput).sendKeys(Keys.DOWN);
        driver.findElement(stationInput).sendKeys(Keys.ENTER);

        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillSecondStep(String date, String period, String comment) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(Keys.ENTER);

        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + period + "']")).click();

        driver.findElement(blackColor).click();
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(finalOrderButton).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public boolean isOrderSuccessModalVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(orderSuccessHeader));
            return driver.findElement(orderSuccessHeader).getText().contains("Заказ оформлен");
        } catch (Exception e) {
            return false;
        }
    }
}