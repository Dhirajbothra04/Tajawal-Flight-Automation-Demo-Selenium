package com.Tajawal.Flights.Tajawal_Flights_Automation_Demo;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.Tajawal.Flights.Tajawal_Flights_Automation_Demo.ListenersMethods.class)
public class VerifySortingOnListing extends BaseFunctions {

	HomeScreen HS_Obj = new HomeScreen();
	ListingScreen LS_Obj = new ListingScreen();
	ReviewAndDetailsScreen RS_Obj = new ReviewAndDetailsScreen();

	@BeforeClass
	public void openURL() throws InterruptedException {
		HS_Obj.openUrl();
		// HS_Obj.removePopUp();
		HS_Obj.selectOrigin();
		HS_Obj.selectDestination();
		HS_Obj.openDepartureDateCalendar();
		HS_Obj.generateFutureDate();
		HS_Obj.selectCabinClass();
		HS_Obj.selectOneWayTrip();
		HS_Obj.selectPax();
		HS_Obj.searchFlight();
	}

	@Test(priority = 1)
	public void verifySearchParameters() throws InterruptedException, IOException {

		LS_Obj.verifyOnListingScreen();
		LS_Obj.fetchFlightDetailsAndPrice();
	}

	@Test(dependsOnMethods = { "verifySearchParameters" })
	public void sortLowTohigh() throws InterruptedException, IOException {

		LS_Obj.sortPriceLowToHigh();

		// LS_Obj.fetchFirstFlightPrice();
		// Assert.assertTrue(LS_Obj.verifyCheapestPriceInList());
		// LS_Obj.selectFirstFlightFromListingScreen();
		// RS_Obj.selectTtile();
		// RS_Obj.enterTravelersName();
		// RS_Obj.selectDOB();
		// RS_Obj.selectNationality();
		// RS_Obj.enterTravelerDetails();

	}

}