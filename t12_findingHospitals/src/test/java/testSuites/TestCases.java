package testSuites;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.BaseClass;
import BaseClasses.BaseTestClass;
import PageClasses.CorporateWellnessPage;
import PageClasses.HospitalNamesPage;
import PageClasses.LabTestsPage;
import PageClasses.LandingPage;
import Utilities.TestDataReader;

public class TestCases extends BaseTestClass {

	BaseClass bClass;
	LandingPage landingPage;
	HospitalNamesPage hospitalNamesPage;
	LabTestsPage labTestsPage;
	CorporateWellnessPage corporateWellnessPage;
	ExtentTest logger;

	// To test if the hospital look-up functionality is working correctly or not
	@Test(priority = 1, dataProvider = "findingHospitalData")
	public void testHospitalFinder(String city, String req) {
		logger = report.createTest("Hospital Look-up testcase");
		invokeBrowser("chrome");
		bClass = new BaseClass(driver, logger);
		bClass.loadPropertiesFile();
		landingPage = bClass.openURL();
		landingPage.enterLocation(city);
		landingPage.enterRequirement(req);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hospitalNamesPage = landingPage.hospitalNamesPageNav();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		hospitalNamesPage.checkTitle();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hospitalNamesPage.hospitalLookup();
	}

	// To test if the submit button is working correctly or not
	// The submit button should not be activated on entering incorrect data
	@Test(priority = 2, dataProvider = "corporateWellnessData")
	public void testCorporateWellness(String name, String org, String ph, String email, String size,
			String query_type) {
		logger = report.createTest("Corporate Wellness Submit Button functionality verifier testcase");
		invokeBrowser("chrome");
		bClass = new BaseClass(driver, logger);
		bClass.loadPropertiesFile();
		landingPage = bClass.openURL();
		corporateWellnessPage = landingPage.wellnessPlansNav();
		corporateWellnessPage.enterDetails(new String[] { name, org, ph, email, size, query_type });
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		corporateWellnessPage.verifySubmitMessage();
	}

	// To test if the top cities are displayed on entering the lab tests web page
	@Test(priority = 3)
	public void testTopCities() {
		logger = report.createTest("Top cities look-up testcase");
		invokeBrowser("chrome");
		bClass = new BaseClass(driver, logger);
		bClass.loadPropertiesFile();
		landingPage = bClass.openURL();
		labTestsPage = landingPage.labTestsNav();
		labTestsPage.getTopCities();
		labTestsPage.verifyTopCities();
		labTestsPage.printTopCities();
	}

	// Data Provider function for testHospitalFinder test
	@DataProvider(name = "findingHospitalData")
	public Object[][] getCityAndRequirement() {
		return TestDataReader.getTestData("t12_testdata", "findingHospital");
	}

	// Data Provider function for testCorporateWellness test
	@DataProvider(name = "corporateWellnessData")
	public Object[][] getCorporateWellnessData() {
		return TestDataReader.getTestData("t12_testdata", "corporateWellness");
	}

	// To close the driver
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	// To flush the html report after test case completion
	@AfterTest
	public void flushReports() {
		report.flush();
	}

}
