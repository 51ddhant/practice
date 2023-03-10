package PageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.BaseClass;

public class HospitalNamesPage extends BaseClass {

	// Constructor HospitalNamesPage class
	public HospitalNamesPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	// Checking the heading of the page to verify correct page is reached or not
	public void checkTitle() {
		try {
			Assert.assertTrue(isElementPresent("HN_heading_PartialLinkText"));
			reportPass("Hospitals in the concerned city are displayed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// For looking up hospitals which are opened 24x7 and have rating of above 4
	// stars
	public void hospitalLookup() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> hospital_247 = getElements("HN_hospitals_24_7_4star_above_Xpath");
		for (WebElement hospital : hospital_247) {
			System.out.println(hospital.getText());
		}
	}
}
