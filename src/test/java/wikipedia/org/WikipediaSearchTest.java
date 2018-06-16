package wikipedia.org;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WikipediaSearchTest {
    private WebDriver driver;

    @Story("Search")
    @Description("Test wikipedia.org search")
    @Test
    public void searchTestAutomation(){
        String searchTerm = "Test automation";
        WikipediaSteps steps = new WikipediaSteps(driver);
        steps.openMainPage();
        steps.search(searchTerm);
        String articleTitle = steps.getPageTitle();
        steps.makeScreenShot();
        Assert.assertEquals(articleTitle, searchTerm, "Test Automation article isn't opened");

    }

    @BeforeTest
    public void setUpDriver() {
        ChromeDriverManager.getInstance().version("2.33").setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
