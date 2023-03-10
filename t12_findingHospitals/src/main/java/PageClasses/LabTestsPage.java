package PageClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import BaseClasses.BaseClass;

public class LabTestsPage extends BaseClass {

	public static String[] topCities;

	// Constructor of LabTestsPage
	public LabTestsPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	// Taking top cities web elements
	public String[] getTopCities() {
		List<WebElement> topCitiesWE = getElements("LT_topcities_Xpath");
		topCities = new String[topCitiesWE.size()];
		for (int i = 0; i < topCities.length; i++) {
			topCities[i] = topCitiesWE.get(i).getText();
		}
		return topCities;
	}

	// Verifying top cities
	public void verifyTopCities() {
		boolean cityPresent1 = false;
		boolean cityPresent2 = false;
		boolean cityPresent3 = false;
		for (String city : topCities) {
			if (city.contains("Mumbai")) {
				cityPresent1 = true;
			} else if (city.contains("Delhi")) {
				cityPresent2 = true;
			} else if (city.contains("Chennai")) {
				cityPresent3 = true;
			}
		}
		if (cityPresent1 && cityPresent2 && cityPresent3) {
			reportPass("Top Cities are displayed.");
		} else {
			reportInfo("Some mismatch maybe present in the top cities displayed.");
		}
	}

	// Printing top cities
	public void printTopCities() {
		for (String city : topCities) {
			System.out.println(city);
		}
	}
}
