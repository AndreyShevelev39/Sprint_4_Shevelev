package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;


    private final By headerOrderButton = By.className("Button_Button__ra12g");
    private final By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button");
    private final By faqSection = By.className("Home_FAQ__3uVm4");
    private final By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }


    public void acceptCookies() {
        if (driver.findElements(cookieButton).size() > 0) {
            driver.findElement(cookieButton).click();
        }
    }


    public void clickFaqQuestion(int index) {
        WebElement element = driver.findElement(faqSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        By question = By.id("accordion__heading-" + index);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(question));
        driver.findElement(question).click();
    }


    public String getFaqAnswerText(int index) {
        By answer = By.id("accordion__panel-" + index);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answer));
        return driver.findElement(answer).getText();
    }

    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}