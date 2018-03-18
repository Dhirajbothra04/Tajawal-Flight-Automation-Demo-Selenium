package com.Tajawal.Flights.Tajawal_Flights_Automation_Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class ReviewAndDetailsScreen extends BaseFunctions {

	private final static Logger LOG = LoggerFactory.getLogger(ReviewAndDetailsScreen.class);

	@FindBy(css = "h3[class = 'panel-title traveller-details__review-title'")
	private WebElement ReviewTitle;

	@FindBy(css = "#flights-summary-travelers-form-title-0")
	private WebElement SelectFirstTravelersTitleDropDown;

	@FindBy(css = "#flights-summary-travelers-form-title-1")
	private WebElement SelectSecondTravelersTitleDropDown;

	@FindBy(css = "#flights-summary-travelers-form-first-name-0")
	private WebElement SelectFirstTravelersFirstName;

	@FindBy(css = "#flights-summary-travelers-form-first-name-1")
	private WebElement SelectSecondTravelersFirstName;

	@FindBy(css = "#flights-summary-travelers-form-middle-name-0")
	private WebElement SelectFirstTravelersMiddleName;

	@FindBy(css = "#flights-summary-travelers-form-middle-name-1")
	private WebElement SelectSecondTravelersMiddleName;

	@FindBy(css = "#flights-summary-travelers-form-last-name-0")
	private WebElement SelectFirstTravelersLastName;

	@FindBy(css = "#flights-summary-travelers-form-last-name-1")
	private WebElement SelectSecondTravelersLastName;

	@FindBy(css = "#flights-summary-travelers-form-birthDay-0")
	private WebElement SelectTravelersDOB_Day;

	@FindBy(css = "#flights-summary-travelers-form-birthMonth-0")
	private WebElement SelectTravelersDOB_Month;

	@FindBy(css = "#flights-summary-travelers-form-birthYear-0")
	private WebElement SelectTravelersDOB_Year;

	@FindBy(css = "#flights-summary-travelers-form-countryId-0")
	private WebElement SelectTravelersNationalityDropDown;

	@FindBy(css = "#flights-summary-travelers-form-contact-email")
	private WebElement ContactEmailField;

	@FindBy(css = "#flights-summary-travelers-form-contact-phone")
	private WebElement ContactPhoneNumberField;

	@FindBy(css = "#flights-summary-go-to-payment-cta")
	private WebElement ContinueToPaymentButton;

	public ReviewAndDetailsScreen() {
		PageFactory.initElements(driver, this);
		try {
			prop.load(new FileInputStream("../Tajawal_Flights_Automation_Demo/Resources/config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectTtile1() throws InterruptedException {

		waitUntillElementVisible(ReviewTitle);
		// Assert.assertEquals("REVIEW YOUR FLIGHT DETAILS",
		// ReviewTitle.getText());
		LOG.info("We are on Traveller Details Screen");
		sleep(2);

		String Title1 = prop.getProperty("Title1");
		Select ddTitle = new Select(SelectFirstTravelersTitleDropDown);

		switch (Title1) {

		case "Mr":
			ddTitle.selectByValue("Mr");
			break;

		case "Premium Economy":
			ddTitle.selectByValue("Ms");
			break;

		case "Business":
			ddTitle.selectByValue("Mrs");
			break;

		default:
			ddTitle.selectByValue("Mr");

		}
		sleep(1);

	}

	public void selectTtile2() throws InterruptedException {

		String Title1 = prop.getProperty("Title2");
		Select ddTitle = new Select(SelectSecondTravelersTitleDropDown);

		switch (Title1) {

		case "Mr":
			ddTitle.selectByValue("Mr");
			break;

		case "Premium Economy":
			ddTitle.selectByValue("Ms");
			break;

		case "Business":
			ddTitle.selectByValue("Mrs");
			break;

		default:
			ddTitle.selectByValue("Mr");

		}
		sleep(1);

	}

	public void enterFirstTravelersName() {

		String FirstName1 = prop.getProperty("FirstName1");
		String MiddleName1 = prop.getProperty("MiddleName1");
		String LastName1 = prop.getProperty("LastName1");

		SelectFirstTravelersFirstName.clear();
		SelectFirstTravelersFirstName.click();
		SelectFirstTravelersFirstName.sendKeys(FirstName1);

		SelectFirstTravelersMiddleName.clear();
		SelectFirstTravelersMiddleName.click();
		SelectFirstTravelersMiddleName.sendKeys(MiddleName1);

		SelectFirstTravelersLastName.clear();
		SelectFirstTravelersLastName.click();
		SelectFirstTravelersLastName.sendKeys(LastName1);

	}

	public void enterSecondTravelersName() {

		String FirstName2 = prop.getProperty("FirstName2");
		String MiddleName2 = prop.getProperty("MiddleName2");
		String LastName2 = prop.getProperty("LastName2");

		SelectSecondTravelersFirstName.clear();
		SelectSecondTravelersFirstName.click();
		SelectSecondTravelersFirstName.sendKeys(FirstName2);

		SelectSecondTravelersMiddleName.clear();
		SelectSecondTravelersMiddleName.click();
		SelectSecondTravelersMiddleName.sendKeys(MiddleName2);

		SelectSecondTravelersLastName.clear();
		SelectSecondTravelersLastName.click();
		SelectSecondTravelersLastName.sendKeys(LastName2);

	}

	public void enterContactDetails() {
		Assert.assertTrue(ContactEmailField.isDisplayed());
		Assert.assertTrue(ContactPhoneNumberField.isDisplayed());

		ContactEmailField.clear();
		ContactEmailField.click();
		ContactEmailField.sendKeys("TestDemoTajawal@gmail.com");

		ContactPhoneNumberField.clear();
		ContactPhoneNumberField.click();
		ContactPhoneNumberField.sendKeys("7897897890");

	}

	public void continueToPayment() {
		Assert.assertTrue(ContinueToPaymentButton.isDisplayed());
		ContinueToPaymentButton.click();

	}

}
