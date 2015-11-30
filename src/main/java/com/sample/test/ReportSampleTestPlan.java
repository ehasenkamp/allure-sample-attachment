package com.sample.test;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sample.interfaces.Report;
import com.sample.reporter.AllureReport;
import com.sample.reporter.StepBuilder;

public class ReportSampleTestPlan {

	Report report = new AllureReport();

	@Test(groups = { "open-allure" })
	public void sampleReportFail() {
		loadAndClick();
		Assert.fail("forced failure");
	}

	@Test(groups = { "open-allure" })
	public void sampleReportPass() {
		loadAndClick();
	}

	private void loadAndClick() {
		WebDriver driver = new FirefoxDriver();

		String endpoint = "http://allure.qatools.ru/";
		driver.get(endpoint);

		report.log(StepBuilder.name("Loaded ").screenshot(driver).build());

		By demoReport = By.cssSelector("body > div > ul > li:nth-child(2) > a");

		report.log(StepBuilder.name("Clicked " + demoReport)
				.captureWebElement(driver, demoReport).build());

		driver.findElement(demoReport).click();

		driver.quit();
	}

}
