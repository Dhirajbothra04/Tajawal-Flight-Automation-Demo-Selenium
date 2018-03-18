package com.Tajawal.Flights.Tajawal_Flights_Automation_Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class HomeScreen extends BaseFunctions {

	public Properties prop = new Properties();
	private final static Logger LOG = LoggerFactory.getLogger(HomeScreen.class);

	@FindBy(css = "button.ab-message-button:nth-child(4)")
	private WebElement messageHeaderNotNowCTA;

	@FindBy(css = "#common-login-cta")
	private WebElement LoginButton;

	@FindBy(css = "#auto-complete-result-flight-1-origin")
	private List<WebElement> OriginSearchResultsList;

	@FindBy(id = "flights-search-origin-1")
	private WebElement Origin;

	@FindBy(id = "flights-search-destination-1")
	private WebElement Destination;

	@FindBy(css = "#nav-flights-cta")
	private WebElement FlightsCTA;

	@FindBy(css = "#auto-complete-result-flight-1-destination")
	private List<WebElement> DestinationSearchResultsList;

	@FindBy(css = "#flights-search-type-one-way-inp")
	private WebElement OneWayTrip;

	@FindBy(css = "#flights-search-type-round-trip-inp")
	private WebElement RoundTrip;

	@FindBy(css = "#flights-search-departureDate-1")
	private WebElement DepartureDate;

	@FindBy(css = "#flights-search-returnDate-1")
	private WebElement ReturnDate;

	@FindBy(css = ".is-selected")
	private WebElement selectedDate;

	@FindBy(css = ".www-srchf__dat-remove-return")
	private WebElement removeRoundTripOption;

	@FindBy(css = "p.js-fs-active-cabin")
	private WebElement classDropdownArrow;

	@FindBy(name = "cabin")
	private List<WebElement> ClassOptionList;

	@FindBy(css = ".js-fs-pax-title")
	private WebElement numberOfPaxDrodown;

	@FindBy(css = "a[class='js-fs-pax-action-btn'][data-action='add'][data-pax-type='adult']")
	private WebElement addNumberOfPax;

	@FindBy(css = "a[class='js-fs-pax-action-btn'][data-action='remove'][data-pax-type='adult']")
	private WebElement RemoveNumberOfPax;

	@FindBy(css = "#flights-search-cta")
	private WebElement searchFlightButton;

	@FindBy(css = "loader__dot")
	private WebElement HomeScreenSpinner;

	@FindBy(css = "i.fa:nth-child(4)")
	private WebElement popupCLoseButton;

	public HomeScreen() {
		PageFactory.initElements(driver, this);
		loadPropertiesfile();
	}

	public void loadPropertiesfile() {
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

	public void openUrl() throws InterruptedException {

		String URL = prop.getProperty("URL");
		driver.get(URL);
		driver.manage().window().maximize();

		String title = driver.getTitle();
		LOG.info("Title of website is {}", title);
		Assert.assertEquals(title, "tajawal | Book cheap flights, airline tickets and hotels online");

		try {
			waituntillAnyOfTwoElementVisible(popupCLoseButton, messageHeaderNotNowCTA);
		} catch (Exception e) {
			// System.out.println("Exception occurred");
			LOG.info("Pop-up not displayed");
		}

		try {
			messageHeaderNotNowCTA.click();
			sleep(1);
		} catch (Exception e) {
			// System.out.println("Exception occurred");
			LOG.info("Pop-up not displayed");
		}

		try {
			popupCLoseButton.click();
			sleep(1);
		} catch (Exception e) {
			// System.out.println("Exception occurred");
			LOG.info("Pop-up not displayed");
		}

	}

	public void removePopUp() throws InterruptedException {
		sleep(1);

		try {
			messageHeaderNotNowCTA.click();
			sleep(1);
		} catch (Exception e) {
			// System.out.println("Exception occurred");
			LOG.info("Pop-up not displayed");
		}

		try {
			popupCLoseButton.click();
			sleep(1);
		} catch (Exception e) {
			// System.out.println("Exception occurred");
			LOG.info("Pop-up not displayed");
		}

	}

	public void selectOrigin() throws InterruptedException {

		String originCity = prop.getProperty("Origin");
		waitUntillElementClickable(Origin);
		Origin.click();
		Origin.clear();
		Origin.sendKeys(originCity);
		sleep(2);
		waitUntillElementVisible(OriginSearchResultsList.get(0));
		OriginSearchResultsList.get(0).click();
		sleep(2);
	}

	public void selectDestination() throws InterruptedException {

		String destinationCity = prop.getProperty("Destination");
		waitUntillElementClickable(Destination);
		Destination.click();
		Destination.clear();
		Destination.sendKeys(destinationCity);
		sleep(2);
		waitUntillElementVisible(DestinationSearchResultsList.get(0));
		DestinationSearchResultsList.get(0).click();
		sleep(1);
	}

	public void selectOneWayTrip() throws InterruptedException {
		try {
			removeRoundTripOption.click();
			sleep(1);
		} catch (Exception e) {
			LOG.info("One way trip button not displayed");
		}
		sleep(1);

	}

	public void selectRoundTrip() throws InterruptedException {
		RoundTrip.click();
		sleep(2);

	}

	public void openDepartureDateCalendar() throws InterruptedException {
		DepartureDate.click();
		sleep(1);
	}

	public void openReturnDateCalendar() {
		ReturnDate.click();
	}

	public void selectTodayDate() throws InterruptedException {
		// TodayDate.click();
		// sleep(2);

		// numberOfPaxDrodown.click();
		// addNumberOfPaxDrodown.click();
	}

	public void selectCabinClass() throws InterruptedException {

		classDropdownArrow.click();

		String cabinClass = prop.getProperty("CabinClass");

		switch (cabinClass) {

		case "Economy":
			ClassOptionList.get(0).click();
			break;

		case "Premium Economy":
			ClassOptionList.get(1).click();
			break;

		case "Business":
			ClassOptionList.get(2).click();
			break;

		case "First":
			ClassOptionList.get(3).click();
			break;

		default:
			ClassOptionList.get(0).click();

		}
		sleep(1);

	}

	public void selectPax() {
		Assert.assertTrue(numberOfPaxDrodown.isDisplayed());
		numberOfPaxDrodown.click();
		for (int i = 0; i < 8; i++)
			RemoveNumberOfPax.click();
		addNumberOfPax.click();

	}

	public void searchFlight() throws InterruptedException {

		Assert.assertTrue(searchFlightButton.isDisplayed());
		searchFlightButton.click();

		ListingScreen lsObj = new ListingScreen();

		try {
			waitUntillElementVisible(lsObj.SearchSummaryBlock);
		} catch (Exception e) {
			LOG.error("Search Suppery box did  not display for more than 15 secs");
		}

	}

	public void generateFutureDate() {

		selectedDate.click();

	}
}
