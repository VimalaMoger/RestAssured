package cucumber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue={"stepDefinition"}, plugin = {"json:target/cucumber.json"})//, tags = "@addBook", dryRun = false)
public class RunnerTest {

}