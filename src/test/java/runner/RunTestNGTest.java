package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( plugin = {
        "pretty",
        "html:test-output.html",
        "json:target/cucumber-report.json"
},features = "src/test/resources/featureFiles", glue = "stepDefinitions",tags = "@validateUser")
public class RunTestNGTest extends AbstractTestNGCucumberTests {

}