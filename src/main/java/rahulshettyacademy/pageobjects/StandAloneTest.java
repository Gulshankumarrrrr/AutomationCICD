package rahulshettyacademy.pageobjects;

import static org.testng.AssertJUnit.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage= new LandingPage(driver);
		
		// for login....
		
		driver.findElement(By.id("userEmail")).sendKeys("kgullu111@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("12Rose#2323");
		driver.findElement(By.id("login")).click();
		
		// now getting those items to display and iterate over them to select for add to the cart....
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	
		
	WebElement prod=	products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	// this prod variable stores product  with name "zara coat 3"..
	// now need to add to cart btton...
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	// to resolve the waiting issue that pops afteer adding to cart section..by using explicit wait...
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	//ng-animating
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	
	Thread.sleep(5000);
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	//now need to verify if items added in the dashboard for shopping matches with the items added in the cart ....so fetching lists and iterating over those products..
	
	//List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
//	Boolean match= cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
  //  Assert.isTrue(false, productName);
    
    // for checkout....
    driver.findElement(By.xpath("(//li[@class='totalRow'])[3]")).click();
    
    // now adding shipping information to place order....
    
    Actions a = new Actions(driver);
    Thread.sleep(5000);
  // a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
    a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
    
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='India']")));
    // for placing order button click
   driver.findElement(By.xpath("//button[contains(class,'ta-item')])[2]")).click();
    driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
    
  // String confirmMessage=  driver.findElement(By.cssSelector(".hero-primary")).getText();
  // Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
   driver.close();
	}
	
	
	
	

}
