package runners;

import org.testng.annotations.Listeners;

import Hooks.TestNGListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Listeners(TestNGListener.class)
@CucumberOptions(features="src/test/resources/features", glue="stepdefinitions")
public class TestRunner extends AbstractTestNGCucumberTests{

}
