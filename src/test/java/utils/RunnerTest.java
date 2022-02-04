package utils;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.lang.invoke.MethodHandles;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps", "utils"},
        tags = "@test",
        plugin = {"pretty"}
)
public class RunnerTest extends AbstractTestNGCucumberTests {

    private static final Logger log = (Logger) LogManager.getLogger(MethodHandles.lookup().lookupClass());
    Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        log.debug("\n----\n" + scenario.getName() + "\n----");
    }

}
