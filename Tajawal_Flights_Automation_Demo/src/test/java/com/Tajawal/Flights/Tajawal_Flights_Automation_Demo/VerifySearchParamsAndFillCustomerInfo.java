package com.Tajawal.Flights.Tajawal_Flights_Automation_Demo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.Tajawal.Flights.Tajawal_Flights_Automation_Demo.ListenersMethods.class)
public class VerifySearchParamsAndFillCustomerInfo extends BaseFunctions {

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
	public void verifySearchParameters() throws InterruptedException {

		LS_Obj.verifyOnListingScreen();
	}

	@Test(dependsOnMethods = { "verifySearchParameters" })
	public void FilterFlightCarrier() throws InterruptedException {
		LS_Obj.filterFirstFlightcarrier();
		LS_Obj.AllFlightCarrierNames();
	}

	@Test(dependsOnMethods = { "FilterFlightCarrier" })
	public void selectFirstFlightFromListing() throws InterruptedException {
		LS_Obj.fetchFirstFlightPrice();
		LS_Obj.selectFirstFlightFromListingScreen();
	}

	@Test(dependsOnMethods = { "selectFirstFlightFromListing" })
	public void entercustomerDetails() throws InterruptedException {
		RS_Obj.selectTtile1();
		RS_Obj.enterFirstTravelersName();
		RS_Obj.selectTtile2();
		RS_Obj.enterSecondTravelersName();
	}

	@Test(dependsOnMethods = { "entercustomerDetails" })
	public void enterContactDetails() throws InterruptedException {
		RS_Obj.enterContactDetails();
	}

	@Test(dependsOnMethods = { "enterContactDetails" })
	public void ContinueToPayment() throws InterruptedException {
		RS_Obj.continueToPayment();
	}

}
