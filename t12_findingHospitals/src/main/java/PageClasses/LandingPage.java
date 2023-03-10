package PageClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.BaseClass;

public class LandingPage extends BaseClass {

	// LandingPage constructor
	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	// Location text-box web-element
	@FindBy(xpath = "//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/input")
	public WebElement locationInput;

	// Requirement text-box web-element
	@FindBy(xpath = "//*[@id=\"c-omni-container\"]/div/div[2]/div/input")
	public WebElement requirementInput;

	// Corporate wellness button web-element
	@FindBy(xpath = "//span[@class = \"nav-interact\" and text() = \"For Corporates\"]/following-sibling::span[1]")
	public WebElement forCorporatesList;

	// Lab Tests Button Web-element
	@FindBy(xpath = "//*[text() = \"Lab Tests\"]")
	public WebElement labTestsBtn;

	// To Enter location to the location text-box
	public void enterLocation(String location) {
		getElement("LP_location_Xpath").clear();
		enterText("LP_location_Xpath", location);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(getElement("LP_locationSuggestion_Xpath")));
		getElement("LP_locationSuggestion_Xpath").click();
		reportPass("Location " + location + "Entered Successfully");
	}

	// To enter requirement to the requirement text-box
	public void enterRequirement(String req) {
		enterText("LP_req_Xpath", req);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(getElement("LP_reqSuggestion_Xpath")));
		getElement("LP_reqSuggestion_Xpath").click();
		reportPass("Requirement " + req + "Entered Successfully");
	}

	// To navigate to the hospital names page
	public HospitalNamesPage hospitalNamesPageNav() {
		HospitalNamesPage hospitalNamesPage = new HospitalNamesPage(driver, logger);
		PageFactory.initElements(driver, hospitalNamesPage);
		return hospitalNamesPage;
	}

	// To navigate to the corporate wellness page
	public CorporateWellnessPage wellnessPlansNav() {
		forCorporatesList.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(getElement("LP_corporateWellnessButton_Xpath")));
		clickElement("LP_corporateWellnessButton_Xpath");
		CorporateWellnessPage corporateWellnessPage = new CorporateWellnessPage(driver, logger);
		PageFactory.initElements(driver, corporateWellnessPage);
		return corporateWellnessPage;
	}

	// To navigate to the lab tests page
	public LabTestsPage labTestsNav() {
		labTestsBtn.click();
		reportPass("Lab Tests Button clicked successfully");
		LabTestsPage labTestsPage = new LabTestsPage(driver, logger);
		PageFactory.initElements(driver, labTestsPage);
		return labTestsPage;
	}
}
