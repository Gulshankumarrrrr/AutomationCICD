package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		 launchApplication();
	}
	
	@Given("^Logged in with username(.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) throws InterruptedException {
		 productCatalogue=landingpage.loginApplication(username,password);
			
	}
	
	@When("^I add product(.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		
		
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=	cartPage.verifyProductDisplay(productName);	
		Assert.assertTrue(match);
		CheckoutPage checkoutpage= cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		 confirmationPage= checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void messafe_is_displayed_on_ConfirmationPage(String string) {
		
		  String confirmMessage=  confirmationPage.getConfirmationMessage();
		  Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		  driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void message_is_displayed(String strArg1) throws Throwable {
		
		
		Assert.assertEquals(strArg1, landingpage.getErrorMessage());
		driver.close();
	}
}
