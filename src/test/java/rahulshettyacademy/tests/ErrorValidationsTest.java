package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

// , retryAnalyzer=Retry.class
	@Test(groups={"ErrorHandling"})
		public void LoginErrorValidation() throws IOException, InterruptedException {
			
		
		String productName= "ZARA COAT 3";		
		ProductCatalogue productCatalogue=landingpage.loginApplication("kgullu111@gmail.com", "1Rose#2323");	
		Assert.assertEquals("Incorrect email  password.", landingpage.getErrorMessage());
	}
		
		@Test
		
		public void productErrorValidation() throws IOException, InterruptedException{
			
			String productName= "ZARA COAT 3";		
			ProductCatalogue productCatalogue=landingpage.loginApplication("kgullu111@gmail.com", "12Rose#2323");	
			List<WebElement> products=productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
		    CartPage cartPage=productCatalogue.goToCartPage();
		    
			Boolean match=	cartPage.verifyProductDisplay(productName);	
			 Assert.assertFalse(match);
			
		}
	
}
