package BaseClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LandingPage;
import Utilities.UniqueNameHelper;

// Base Class Containing various functionalities usable in other page classes
public class BaseClass extends BaseTestClass {

	public static Properties prop;
	public WebDriverWait wait;
	public ExtentTest logger;

	// Base Class constructor
	public BaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	// To Load Properties File
	public void loadPropertiesFile() {
		prop = new Properties();

		try {
			prop.load(new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\Config\\configuration.properties"));
			reportInfo("Properties File Loaded Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// To open the Main Page URL
	public LandingPage openURL() {
		try {
			reportInfo("Opening the Website");
			driver.get(prop.getProperty("websiteURL"));
			reportPass(prop.getProperty("websiteURL") + "  Identified Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		LandingPage landingPage = new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;
	}

	// To Identify the WebElement
	public WebElement getElement(String locatorKey) {

		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
				Assert.fail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();

			Assert.fail("Failing the TestCase : " + e.getMessage());
		}
		return element;
	}

	// To Identify the list of WebElements
	public List<WebElement> getElements(String locatorKey) {

		List<WebElement> elements = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				elements = driver.findElements(By.id(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				elements = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				elements = driver.findElements(By.cssSelector(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				elements = driver.findElements(By.linkText(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				elements = driver.findElements(By.partialLinkText(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				elements = driver.findElements(By.name(prop.getProperty(locatorKey)));
				reportInfo("Locator Identified : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
				Assert.fail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
			e.printStackTrace();

			Assert.fail("Failing the TestCase : " + e.getMessage());
		}
		return elements;
	}

	// To Enter text in text boxes
	public void enterText(String locatorKey, String data) {
		try {
			getElement(locatorKey).sendKeys(data);
			reportPass(data + " - Entered Successfully in locator : " + locatorKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	// To click web-elements
	public void clickElement(String locatorKey) {
		try {
			getElement(locatorKey).click();
			reportPass("Element " + locatorKey + " clicked successfully.");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// To check if a particular element is present or not
	public boolean isElementPresent(String locatorKey) {
		try {
			if (getElement(locatorKey).isDisplayed()) {
				reportPass(locatorKey + " :Element is Displayed.");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	// To check if a particular element is selected or not
	public boolean isElementSelected(String locatorKey) {
		try {
			if (getElement(locatorKey).isSelected()) {
				reportPass(locatorKey + " :Element is Selected.");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	// To check if a particular element is enabled or not
	public boolean isElementEnabled(String locatorKey) {
		try {
			if (getElement(locatorKey).isEnabled()) {
				reportPass(locatorKey + " :Element is Enabled.");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	// To take Screenshot
	public void takeScreenshot() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

		String fileName = System.getProperty("user.dir") + "\\Screenshots\\" + UniqueNameHelper.getTimeStamp() + ".png";
		File destFile = new File(fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To log info into reports
	public void reportInfo(String reportString) {
		logger.log(Status.INFO, reportString);
	}

	// To log fail into reports
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenshot();
		Assert.fail(reportString);
	}

	// To log pass into reports
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}
}
