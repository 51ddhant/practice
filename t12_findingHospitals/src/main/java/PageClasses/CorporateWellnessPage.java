package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.BaseClass;

// Class for the web page of corporate wellness form
public class CorporateWellnessPage extends BaseClass {

	public CorporateWellnessPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(id = "name")
	public WebElement nameTextbox;

	@FindBy(id = "organizationName")
	public WebElement orgNameTextbox;

	@FindBy(id = "contactNumber")
	public WebElement phoneTextbox;

	@FindBy(id = "officialEmailId")
	public WebElement emailTextbox;

	@FindBy(id = "organizationSize")
	public WebElement orgSizeDrpList;

	@FindBy(id = "interestedIn")
	public WebElement interestDrpList;

	@FindBy(xpath = "//*[@id=\"app\"]/div/div/header[2]/div[2]/div/form/button")
	public WebElement submitBtn;

	// To Pass info to the various text-boxes and drop-boxes
	public void enterDetails(String[] data) {
		nameTextbox.sendKeys(data[0]);
		reportPass("Name entered in name textbox");
		orgNameTextbox.sendKeys(data[1]);
		reportPass("Organization name entered in organization name textbox");
		phoneTextbox.sendKeys(data[2]);
		reportPass("Phone Number entered in phone number textbox");
		emailTextbox.sendKeys(data[3]);
		reportPass("Email entered in email textbox");
		Select orgSize = new Select(orgSizeDrpList);
		float sizeData = Float.parseFloat(data[4]);
		if (sizeData <= 500) {
			orgSize.selectByValue("<=500");
		} else if (sizeData > 500 && sizeData <= 1000) {
			orgSize.selectByValue("501-1000");
		} else if (sizeData > 1000 && sizeData <= 5000) {
			orgSize.selectByValue("1001-5000");
		} else if (sizeData > 5000 && sizeData <= 10000) {
			orgSize.selectByValue("5001-10000");
		} else {
			orgSize.selectByValue("10001+");
		}
		reportPass("Organization size selected from dropbox");
		Select interest = new Select(interestDrpList);
		interest.selectByValue(data[5]);
		reportPass("Query type selected from the query type dropbox");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// To click on the "Schedule a demo" Button
	public void submitForm() {
		submitBtn.click();
	}

	// To verify that submit button is activated on entering incorrect data
	public void verifySubmitMessage() {
		if (!isElementEnabled("CW_SubmitButton_Xpath")) {
			reportPass("Submit button is not enabled on entering incorrect data.");
		} else {
			reportFail("Submit Button enabled for wrong data");
		}
	}

}
