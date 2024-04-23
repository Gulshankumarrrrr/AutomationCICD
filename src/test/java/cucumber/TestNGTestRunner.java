package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//cucumber depends on either TestNg or junit depending on the assesrtions we used in the  framework we have used .....
@CucumberOptions(features="src/test/java/cucumber", glue="rahulshettyacademy.stepDefinations", monochrome=true, tags= "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

	
}
