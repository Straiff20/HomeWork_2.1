import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartFormTest {
    static class Form {
        private WebDriver driver;

        @BeforeAll
        static void setUpAll() {
            System.setProperty("webdriver.chrome.driver", "Brouser_plugins/chrome/chromedriver.exe");
        }

        @BeforeEach
        void setUp() {
            driver = new ChromeDriver();
        }

        @AfterEach
        void tearDown() {
            driver.quit();
            driver = null;
        }

        @Test
        void cardFormLHappyTest() throws InterruptedException {
            //  правильно заполняем форму
            driver.get("http://localhost:7777");
            Thread.sleep(2000);
            String title = driver.findElement(By.cssSelector(".heading_theme_alfa-on-white")).getText();
            assertEquals("Заявка на дебетовую карту", title.trim());  //  Заголовок виден и правильно написан

            driver.findElement(By.cssSelector("input.input__control[type=text]")).sendKeys("Сергей");
            driver.findElement(By.cssSelector("input.input__control[type=tel]")).sendKeys("+79991112233");
            driver.findElement(By.cssSelector("form span.checkbox__box")).click();
            Thread.sleep(1000);

            driver.findElement(By.className("button__text")).click();
            Thread.sleep(2000);

            driver.findElement(By.className("Success_successBlock__2L3Cw")).isDisplayed();
            String successText = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", successText.trim());
            Thread.sleep(1000);

        }
    }
}
