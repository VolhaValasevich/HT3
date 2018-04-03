package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.utils.Utils;

public class CreateNewIssuePage extends AbstractPage {

    private final String BASE_URL = "http://www.github.com/";
    private final Logger logger = LogManager.getRootLogger();
    private JavascriptExecutor executor;

    @FindBy(id = "issue_title")
    private WebElement inputIssueTitle;

    @FindBy(id = "issue_body")
    private WebElement inputIssueComment;

    @FindBy(xpath = "//button[contains(text(), 'Submit new issue')]")
    private WebElement buttonSubmitNewIssue;

    @FindBy(xpath = "//span[@class = 'js-issue-title']")
    private WebElement labelIssueTitle;

    public CreateNewIssuePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
        executor = (JavascriptExecutor)driver;
    }

    public String submitNewIssue(String issueTitle, String issueComment) {
        String issueFullTitle = issueTitle + Utils.getRandomString(6);
        inputIssueTitle.sendKeys(issueFullTitle);
        inputIssueComment.sendKeys(issueComment);
        executor.executeScript("arguments[0].click();", buttonSubmitNewIssue);
        return issueFullTitle;
    }

    public String getIssueTitle() {
        return labelIssueTitle.getText();
    }

    @Override
    public void openPage()
    {
        //not sure how to navigate to a randomly generated url
        driver.navigate().to(BASE_URL);
    }
}
