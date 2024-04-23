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

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage= new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue=landingPage.loginApplication("kgullu111@gmail.com", "12Rose#2323");
	
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	CartPage cartPage=productCatalogue.goToCartPage();
	
	Boolean match=	cartPage.verifyProductDisplay(productName);
		
	// Assert.assertTrue(match);

	CheckoutPage checkoutpage= cartPage.goToCheckout();
	checkoutpage.selectCountry("india");
	checkoutpage.submitOrder();
	ConfirmationPage confirmationPage= checkoutpage.submitOrder();
    
  String confirmMessage=  confirmationPage.getConfirmationMessage();
  //Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
   driver.close();
	}
	
	
	
	

}
