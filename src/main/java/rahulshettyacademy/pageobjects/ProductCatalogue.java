package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue  extends AbstractComponent {
	
	// PageFactory is only used for (driver.find......... elements)
	
	WebDriver driver;
		
	//1st this constructor will be initialised.....
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	
    @FindBy(css=".mb-3")
   List<WebElement> products;
    
    @FindBy(css=".ng-animating")
    WebElement spinner;
    
    By productsBy= By.cssSelector(".mb-3");
    By addToCart= By.cssSelector(".card-body button:last-of-type");
   By  toastMaessage= By.cssSelector("#toast-container");
    
    
   public List<WebElement> getProductList() {
	   
	   waitForElementToAppear(productsBy);
	   return products;
   }
    
   public WebElement getProductByName(String productName) {
	   
	   WebElement prod=	getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	      return prod;
		// this prod variable stores product  with name "zara coat 3"..
		
   }
   
   // now need to add to cart btton...
           public void addProductToCart(String productName) throws InterruptedException {
	   
        	WebElement prod=   getProductByName(productName);
        	   prod.findElement(addToCart).click();
        	   waitForElementToAppear(toastMaessage);
        	   waitForElementToDissapear(spinner);
   }
   
}
