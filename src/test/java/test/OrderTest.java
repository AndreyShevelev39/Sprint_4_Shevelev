package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.MainPage;
import pageObjects.OrderPage;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String entryPoint;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String period;
    private final String comment;

    public OrderTest(String entryPoint, String name, String surname, String address, String station, String phone, String date, String period, String comment) {
        this.entryPoint = entryPoint;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"header", "Антон", "Чехов", "ул. Садовая, 3", "Тверская", "+79001112233", "25.08.2024", "сутки", "Домофон 12"},
                {"bottom", "Иван", "Грозный", "ул. Кремлевская, 1", "Охотный Ряд", "+79998887766", "01.09.2024", "двое суток", "Злой собакен"}
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void successfulOrderCreation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.acceptCookies();

        if ("header".equals(entryPoint)) {
            mainPage.clickHeaderOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstStep(name, surname, address, station, phone);
        orderPage.fillSecondStep(date, period, comment);
        orderPage.confirmOrder();

        boolean isSuccess = orderPage.isOrderSuccessModalVisible();
        assertTrue("Заказ не оформлен", isSuccess);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}