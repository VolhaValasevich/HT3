package com.epam.ta.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.ta.utils.Utils;

public class CreateNewRepositoryPage extends AbstractPage
{
	private final String BASE_URL = "http://www.github.com/new";
	private final Logger logger = LogManager.getRootLogger();
	private JavascriptExecutor executor;

	@FindBy(id = "repository_name")
	private WebElement inputRepositoryName;

	@FindBy(id = "repository_description")
	private WebElement inputRepositoryDescription;

	@FindBy(xpath = "//form[@id='new_repository']//button[@type='submit']")
	private WebElement butttonCreate;

	@FindBy(className = "empty-repo-setup-option")
	private WebElement labelEmptyRepoSetupOption;

	@FindBy(xpath = "//a[@data-pjax='#js-repo-pjax-container']")
	private WebElement linkCurrentRepository;

	@FindBy(xpath = "//button[@aria-label='Star this repository']")
	private WebElement buttonStarCurrentRepository;

	@FindBy(xpath = "//button[@aria-label='Unstar this repository']")
	private WebElement buttonUnstarCurrentRepository;

	@FindBy(xpath = "//a[contains(text(),'New issue')]")
    private WebElement linkCreateIssue;

	@FindBy(xpath = "//a[contains(@data-selected-links, 'repo_settings')]")
    private WebElement linkSettings;

	public CreateNewRepositoryPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
        executor = (JavascriptExecutor)driver;
	}

	public boolean isCurrentRepositoryEmpty()
	{
		return labelEmptyRepoSetupOption.isDisplayed();
	}

	public String createNewRepository(String repositoryName, String repositoryDescription)
	{
		String repositoryFullName = repositoryName + Utils.getRandomString(6);
		inputRepositoryName.sendKeys(repositoryFullName);
		inputRepositoryDescription.sendKeys(repositoryDescription);
		executor.executeScript("arguments[0].click();", butttonCreate);
		//butttonCreate.click();
		return repositoryFullName;
	}

	public boolean starCurrentRepository() {
        executor.executeScript("arguments[0].click();", buttonStarCurrentRepository);
		//buttonStarCurrentRepository.click();
		return buttonUnstarCurrentRepository.isDisplayed();
	}

	public boolean unstarCurrentRepository() {
        executor.executeScript("arguments[0].click();", buttonUnstarCurrentRepository);
		//buttonUnstarCurrentRepository.click();
		return buttonStarCurrentRepository.isDisplayed();
	}

	public void clickLinkSettings() {
	    executor.executeScript("arguments[0].click();", linkSettings);
    }

    public void clickLinkCreateIssue() {
        executor.executeScript("arguments[0].click();", linkCreateIssue);
    }

	public String getCurrentRepositoryName()
	{
		return linkCurrentRepository.getText();
	}

	@Override
	public void openPage()
	{
		driver.navigate().to(BASE_URL);
	}

}
