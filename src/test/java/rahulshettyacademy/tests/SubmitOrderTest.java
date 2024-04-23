package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName= "ZARA COAT 3";

		@Test(dataProvider="getData",groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
			
						
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));	
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
	    CartPage cartPage=productCatalogue.goToCartPage();
	
	Boolean match=	cartPage.verifyProductDisplay(input.get(productName));	
	Assert.assertTrue(match);
	CheckoutPage checkoutpage= cartPage.goToCheckout();
	checkoutpage.selectCountry("india");
	ConfirmationPage confirmationPage= checkoutpage.submitOrder();   
     String confirmMessage=  confirmationPage.getConfirmationMessage();
  Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
  
	}
	// to check or verify names in the order section
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() throws InterruptedException {
			
			ProductCatalogue productCatalogue=landingpage.loginApplication("kgullu111@gmail.com","12Rose#2323");
		OrderPage orderPage=	productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
		
		public String getScreenshot(String testCaseName) throws IOException {
			TakesScreenshot ts= 	(TakesScreenshot)driver;
		File source=	ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//" +testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return ("user.dir")+"//reports//" +testCaseName + ".png";
		}
		
		@DataProvider
		public Object[][] getData() throws IOException {
    
			
//			HashMap<String,String> map= new HashMap<String,String> ();
//			map.put("email","kgullu111@gmail.com" );
//			map.put("password", "12Rose#2323");
//			map.put("productName", "ZARA COAT 3");
//			
//			HashMap<String,String> map1= new HashMap<String,String> ();
//			map1.put("email","kgullu1995@gmail.com" );
//			map1.put("password", "12Rose#2323");
//			map1.put("productName", "ADIDAS ORIGINAL");
			
		List<HashMap<String, String>>data=	getJsonDataToMap("D:\\pareekshn\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
			return	new Object[][] {{data.get(0)}, {data.get(1)} };
		}
		}


