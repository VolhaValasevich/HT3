package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.utils.Utils;

public class RepoSettingsPage extends AbstractPage {

    private final String BASE_URL = "http://www.github.com/";
    private final Logger logger = LogManager.getRootLogger();
    private JavascriptExecutor executor;

    @FindBy(xpath = "//button[contains(text(), 'Delete this repository')]")
    private WebElement buttonDelete;

    @FindBy(xpath = "//input[@aria-label = 'Type in the name of the repository to confirm that you want to delete this repository.']")
    private WebElement inputConfirmation;

    @FindBy(xpath = "//button[contains(text(), 'I understand the consequences, delete this repository')]")
    private WebElement buttonConfirmation;

    @FindBy(xpath = "//div[@id='js-flash-container']/div/div")
    private WebElement messageDeleteSuccessful;

    public RepoSettingsPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
        executor = (JavascriptExecutor)driver;
    }

    public boolean deleteCurrentRepository(String repoName) {
        executor.executeScript("arguments[0].click();", buttonDelete);
        inputConfirmation.sendKeys(repoName);
        executor.executeScript("arguments[0].click();", buttonConfirmation);
        return messageDeleteSuccessful.isDisplayed();
    }

    @Override
    public void openPage()
    {
        //not sure how to navigate to a randomly generated url
        driver.navigate().to(BASE_URL);
    }
}
