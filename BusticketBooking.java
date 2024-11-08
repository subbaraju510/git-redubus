package StepDefinitions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.SimpleDateFormat;
import java.util.Date;
public class BusticketBooking {
	By sourceField=By.id("src");
	By destinationinput=By.id("dest");
	 WebDriver driver=new FirefoxDriver();
	WebDriverWait wait;
	
	@Given("user is on the bus booking homepage")
	public void user_is_on_the_bus_booking_homepage() {
		System.out.println("in side step-user open the browser");
		   String projectpath=System.getProperty("user.dir");
		   System.out.println("projectpath==="+projectpath);
		   System.setProperty("webdriver.firefox.driver","C:\\Program Files\\geckodriver" );
		   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		   driver.get("https://www.redbus.com/");
		  driver.manage().deleteAllCookies(); //every time before opening the url delete coockies
		   driver.manage().window().maximize();
	}

	@When("user enters the origin city as {string}")
	public void user_enters_the_origin_city_as(String source) throws InterruptedException {
		
	
		driver.findElement(sourceField).click();
        driver.findElement(sourceField).sendKeys(source);
        Thread.sleep(2000); // Adjust this if needed
		System.out.println("user enters the origin city aswwwww");
		Thread.sleep(2000);
		List<WebElement>totallist=driver.findElements(By.xpath("//div[@class=\"autoFill autosuggest-ul rdc-src\"]//li"));
		int totallistsize=totallist.size();
		for(int i=0; i<totallistsize; i++) {
			String alllocations=totallist.get(i).getText();
			System.out.println("locations"+alllocations);
			
		}
		
		Thread.sleep(2000);
	}

	@And("user enters the destination city as {string}")
	public void user_enters_the_destination_city_as(String destination) throws InterruptedException {
		//WebElement destinationinput = driver.findElement(By.id("dest"));
		driver.findElement(destinationinput).click();
		driver.findElement(destinationinput).sendKeys(destination);
        Thread.sleep(2000); 
	  
	}

	@And("user selects the travel date as")
	public void user_selects_the_travel_date_as() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		 String date1= dateFormat.format(date);
		 
		 // Print the Date
		String[] currentdate=date1.split("/");
		int bookingdate=Integer.parseInt(currentdate[1])+1;
		
		WebElement dateInput = driver.findElement(By.id("onward_cal"));
		dateInput.click();
	WebElement selectdata=driver.findElement(By.xpath("//div[@class=\"DayTiles__CalendarDaysBlock-sc-14em0t0-0 eUrtce\"]//span[text()="+bookingdate+"]"));
		
	selectdata.click();
		
	}

	@And("user clicks on the Search button")
	public void user_clicks_on_the_button() {
		WebElement searchButton = driver.findElement(By.id("search_butn"));
        searchButton.click();
    }
	

	@Then("available buses should be displayed")
	public void available_buses_between_and_should_be_displayed() {
		WebElement availablebus=driver.findElement(By.xpath("//span[text()='found']"));
		String checkavailablebus=availablebus.getText();
		
		 String[] busesplit=checkavailablebus.split(" ");
		 String Expecteresult=busesplit[2];
		 Assert.assertEquals("found",Expecteresult);
	    
	}
	@When("Filtering results based on time, boarding points")
	public void Filtering_results_based_on_time_boarding_points() throws InterruptedException {
		WebElement departuretime=driver.findElement(By.xpath("//ul[@class=\"dept-time dt-time-filter\"]//label[text()='6 am to 12 pm']"));
		departuretime.click();
		WebElement bustype=driver.findElement(By.xpath("//label[text()=\"SEATER\"]"));
		bustype.click();
		WebElement arrivaltime=driver.findElement(By.xpath("//ul[@class=\"dept-time at-time-filter\"]//label[text()=\"12 pm to 6 pm\"]"));
		arrivaltime.click();
		
	}
	@And("Seat selection process")
	public void Seat_selection_process() throws InterruptedException {
		WebElement Viewseatsbutton=driver.findElement(By.xpath("(//div[text()='View Seats'])[1]"));
		Viewseatsbutton.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@title='close']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='lower-canvas canvas-wrapper']/canvas")).click();
		
		Thread.sleep(2000);
		WebElement boardingpoint=driver.findElement(By.xpath("//div[@class=\"radio-unchecked\"]"));
		boardingpoint.click();
		///Thread.sleep(2000);
		WebElement dropingpoint=driver.findElement(By.xpath("(//ul[@data-value=\"dp\"]/li/div/div)[1]"));
		
		dropingpoint.click();
		Thread.sleep(2000);
		WebElement proceedtobook=driver.findElement(By.xpath("//button[contains(text(),'Proceed to book')]"));
		proceedtobook.click();
	}
	
	@When("^user enters passenger details (.*) and (.*) and (.*) and (.*) and (.*)$")
	public void user_enters_passenger_details(String passengername, String age, String Stateofresidance, String Email, String Phonenumber ) throws InterruptedException { 
            WebElement nameInput = driver.findElement(By.name("Name_0"));
            WebElement agesel= driver.findElement(By.id("div_22_0"));
            agesel.click();
            WebElement ageInput = driver.findElement(By.name("Age_0"));
            WebElement stateresidance=driver.findElement(By.id("201"));
            WebElement emailid=driver.findElement(By.id("seatno-05"));
            WebElement phnumber=driver.findElement(By.id("seatno-06"));
           // WebElement email= driver.findElement(By.xpath("div_22_0"));
            WebElement dontaddfreecancellation= driver.findElement(By.xpath("//div[text()=\"Don't add Free Cancellation\"]"));
            WebElement redbusassurance= driver.findElement(By.xpath("//label[text()=\"Don’t add redBus Assurance\"]"));
            WebElement proceedtopay= driver.findElement(By.xpath("//input[@class=\"button main-btn gtm-continueBooking\"]"));
            
            
            
            nameInput.sendKeys(passengername);
            ageInput.sendKeys(age);
            stateresidance.sendKeys(Stateofresidance,Keys.ARROW_DOWN);
            emailid.sendKeys(Email);
            phnumber.sendKeys(Phonenumber);
            dontaddfreecancellation.click();
            redbusassurance.click();
            Thread.sleep(2000);
            proceedtopay.click();
            Thread.sleep(2000);
	}
	
	@When("Handling payment without completing the transaction")
	public void Handling_payment_without_completing_the_transaction() throws InterruptedException {
		WebElement paymethod=driver.findElement(By.xpath("//div[@class=\"pi-title\"]"));
		String Confirmationpaymentpage=paymethod.getText();
		System.out.println("Confirmationpaymentpage:"+Confirmationpaymentpage);
		Thread.sleep(2000);
		
}
}
