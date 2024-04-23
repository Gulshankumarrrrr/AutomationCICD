package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	
	//1st this constructor will be initialised.....
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
    //WebElement userEmails= driver.findElement(By.id("userEmail"));
	//PageFactory
    // below thing work as same of above sentence.....
	
    @FindBy(id="userEmail")
    WebElement useremail;
    
    @FindBy(id="userPassword")
    WebElement passwordEle;
    
    @FindBy(id="login")
     WebElement submit;
    
    //for incorrect useremail or password case.....
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;


	public ProductCatalogue loginApplication(String email, String password) throws InterruptedException {
	//	waitForElementToAppear();
		Thread.sleep(5000);
		useremail.sendKeys(email);
    passwordEle.sendKeys(password);
    submit.click();
    ProductCatalogue productCatalogue= new ProductCatalogue(driver);
    return productCatalogue;
    }
    
    public String getErrorMessage() {
    	waitForWebElementToAppear(errorMessage);
    return	errorMessage.getText();
    }
    
    public void goTo() {
    
    	
    	
    }

		
    
    
}
