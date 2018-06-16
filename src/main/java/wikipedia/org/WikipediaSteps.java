package wikipedia.org;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WikipediaSteps {

    private WebDriver driver;
    @FindBy(id= "searchInput")
    private WebElement searchInput;

    @FindBy(css = "#search-form > fieldset > button")
    private WebElement searchBtn;

    @FindBy(id ="www-wikipedia-org")
    private WebElement mainPageBody;

    @FindBy(id ="content")
    private WebElement articleContent;

    @FindBy(id = "firstHeading")
    private WebElement articleTitle;

    WikipediaSteps(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step
    public void search(String searchTerm) {
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        searchBtn.click();
        new WebDriverWait(driver, 5)
                .withMessage("Failed to open article")
                .until(ExpectedConditions.visibilityOf(articleContent));
    }

    @Step("Open https://www.wikipedia.org")
    public void openMainPage() {
        driver.get("https://www.wikipedia.org");
        driver.manage().timeouts().pageLoadTimeout(5, SECONDS);
        new WebDriverWait(driver, 5)
                .withMessage("Failed to open wikipedia.org")
                .until(ExpectedConditions.visibilityOf(mainPageBody));
    }

    public String getPageTitle(){
        return articleTitle.getText();
    }

    @Attachment
    @Step("Make screen shot of results page")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
