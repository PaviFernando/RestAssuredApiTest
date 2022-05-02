package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/featureFiles", glue = "stepDefinitions",tags = "@validateUser")
public class RunTestNGTest extends AbstractTestNGCucumberTests {

}