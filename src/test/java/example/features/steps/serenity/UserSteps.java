package example.features.steps.serenity;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import example.person.Person;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSteps {

    Logger logger = LoggerFactory.getLogger(UserSteps.class);

    @LocalServerPort
    private int port;

    private Person person;
    private String body;

    @Managed
    private WebDriver driver;

    private void setUpDriver() {
        WebDriverManager.chromedriver().version("75").setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        chromeOptions.setHeadless(true);
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(chromeOptions);
    }

    @Step
    public void hasLastName(String name) {
        person = new Person("firstName", "World");
    }

    @Step
    @Screenshots
    public void visitsHelloPage() {
        setUpDriver();
        driver.navigate().to(String.format("http://localhost:%s/hello", port));
        body = driver.findElement(By.tagName("body")).getText();
        driver.quit();
    }

    @Step
    public void shouldSee(String greeting) {
        assertThat(body, containsString(String.format("Hello %s", person.getLastName())));
    }
}