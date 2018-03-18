package com.Tajawal.Flights.Tajawal_Flights_Automation_Demo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class ListingScreen extends BaseFunctions {

	private final static Logger LOG = LoggerFactory.getLogger(ListingScreen.class);
	Actions builder = new Actions(driver);
	public Properties prop = new Properties();
	DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
	Date myDate = new Date(System.currentTimeMillis());
	Calendar cal = Calendar.getInstance();
	private static final String ListingData_CSV_FILE = "../Tajawal_Flights_Automation_Demo/CSV-Files_data/ListingData"
			+ formater.format(calendar.getTime()) + ".csv";

	@FindBy(css = "#search-summary-heading__desktop")
	public WebElement SearchSummaryBlock;

	@FindBy(css = ".sort-tabs__tab-text")
	private List<WebElement> SortOptionList;

	@FindBy(css = "span[class = 'text-chambray ab_price_comma bold h2 no-margin price-display']")
	private List<WebElement> AllPricesList;

	@FindBy(id = "flights-results-select-cta-btn-desktop-1")
	private WebElement FirstSelectFlightButton;

	@FindBy(css = ".sort-tabs__sort-option")
	private List<WebElement> sortOptionsList;

	@FindBy(css = "span[class = 'block font-weight-600 text-chambray']")
	private List<WebElement> searchSummaryListElements;

	@FindBy(id = "flights-filters-airline-leg-0-check-exclude-0")
	private WebElement FirstFlightcarrier;

	@FindBy(css = "span[class = 'ng-pristine ng-untouched ng-valid ng-not-empty']")
	private WebElement FirstFlightcarrierCheckboxselected;

	@FindBy(css = "span[class = 'search-result-item__segment-flight-note ellipsis']")
	private List<WebElement> ListingResultsFlightCarrierName;

	@FindBy(css = "span[class = 'search-result-item__segment-flight-note-no  padding-right-sm block']")
	private List<WebElement> ListingResultsFlightNumber;

	@FindBy(css = "h3[class = 'panel-title traveller-details__review-title'")
	private WebElement ReviewTitle;

	public ListingScreen() {
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

	public boolean verifyOnListingScreen() {

		String OriginCode = prop.getProperty("OriginCode");
		String DestinationCode = prop.getProperty("DestinationCode");
		String cabinClass = prop.getProperty("CabinClass");

		cal.setTime(myDate);
		cal.add(Calendar.DATE, +2);
		String DateSelected = dateFormat.format(cal.getTime());

		System.out.println(DateSelected);

		waitUntillElementVisible(SearchSummaryBlock);
		Assert.assertTrue(SearchSummaryBlock.isDisplayed());
		Assert.assertTrue(searchSummaryListElements.get(0).getText().equalsIgnoreCase(OriginCode));
		Assert.assertTrue(searchSummaryListElements.get(1).getText().equalsIgnoreCase(DestinationCode));
		Assert.assertTrue(searchSummaryListElements.get(2).getText().equalsIgnoreCase(DateSelected));
		Assert.assertTrue(searchSummaryListElements.get(3).getText().equalsIgnoreCase(cabinClass));
		Assert.assertTrue(searchSummaryListElements.get(4).getText().equalsIgnoreCase("2"));
		return true;
	}

	public void sortPriceLowToHigh() throws InterruptedException, IOException {
		builder.moveToElement(SortOptionList.get(0)).perform();
		sleep(2);
		sortOptionsList.get(0).click();
		sleep(2);

		fetchFlightDetailsAndPrice();

	}

	public void fetchFlightDetailsAndPrice() throws IOException {

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(ListingData_CSV_FILE));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("Carrear Name_Flight number_Price"));) {

			for (int i = 0; i < ListingResultsFlightCarrierName.size(); i++) {
				// System.out.println(ListingResultsFlightCarrierName.get(i).getText());
				csvPrinter.printRecord(ListingResultsFlightCarrierName.get(i).getText() + "_"
						+ ListingResultsFlightNumber.get(i).getText() + "_" + AllPricesList.get(i).getText());
			}

			csvPrinter.printRecord("----", "----", "---");
			csvPrinter.flush();
		}

	}

	public void fetchFirstFlightPrice() {

		String firstFlightPriceVar = AllPricesList.get(0).getText();

		System.out.println("First price is " + firstFlightPriceVar);

	}

	public boolean verifyCheapestPriceInList() {

		int cheapestPrice = Integer.parseInt(AllPricesList.get(0).getText());
		boolean isCheapest = true;

		System.out.println(cheapestPrice);

		// for (int i = 0; i < AllPricesList.size(); i++) {
		//
		// System.out.println(Integer.parseInt( AllPricesList.get(i).getText()
		// ));
		//
		// }

		for (int i = 1; i < AllPricesList.size() - 1; i++) {

			if (Integer.parseInt(AllPricesList.get(i).getText()) < cheapestPrice) {
				cheapestPrice = Integer.parseInt(AllPricesList.get(i).getText());
				isCheapest = false;
				break;
			}
		}

		return isCheapest;
	}

	public void selectFirstFlightFromListingScreen() throws InterruptedException {

		waitUntillElementClickable(FirstSelectFlightButton);
		if (FirstSelectFlightButton.isDisplayed())
			FirstSelectFlightButton.click();

		try {
			waitUntillElementVisible(ReviewTitle);
		} catch (Exception e) {
			LOG.error("Traveller details screen did not appear for more than 15 secs");
		}

	}

	public void filterFirstFlightcarrier() throws InterruptedException {
		scrollWindow(450);
		builder.moveToElement(FirstFlightcarrier).perform();
		sleep(2);
		Assert.assertTrue(FirstFlightcarrier.isEnabled());

		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('flights-filters-airline-leg-0-check-exclude-0').click();");
		// Assert.assertTrue(FirstFlightcarrierCheckboxselected.isDisplayed());

		scrollWindow(-450);

	}

	public void AllFlightCarrierNames() {

		for (int i = 0; i < ListingResultsFlightCarrierName.size(); i++) {
			System.out.println(ListingResultsFlightCarrierName.get(i).getText());

		}

	}

}
