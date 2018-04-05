package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepo", "auto-generated test repo"));
		Assert.assertTrue(steps.currentRepositoryIsEmpty());
		// do not use lots of asserts
	}

	@Test(description = "Login to Github")
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

	@Test(description = "Star and unstar repository")
	public void oneCanStarAndUnstarRepository()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepository("testRepo", "auto-generated test repo");
		Assert.assertTrue(steps.starCurrentRepository(), "[Failed to star a repository. The 'Unstar' button does not appear.]");
		Assert.assertTrue(steps.unstarCurrentRepository(), "[Failed to unstar a repository. The 'Star' button does not appear.]");
	}

	@Test(description = "Submit new issue")
	public void oneCanSubmitNewIssue() {
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepository("testRepo", "auto-generated test repo");
		Assert.assertTrue(steps.submitNewIssue("testIssue", "auto-generatet issue comment"),
				"[Failed to submit an issue.]");
	}

    @Test(description = "Delete repository")
	public void oneCanDeleteRepository() {
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepository("testRepo", "auto-generated test repo");
		Assert.assertTrue(steps.deleteRepository(), "[Failed to delete repository.]");
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
