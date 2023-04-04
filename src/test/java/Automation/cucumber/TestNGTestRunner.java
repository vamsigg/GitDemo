package Automation.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Automation/cucumber",glue="Automation/stepdefinitions"
,monochrome=true,tags="@error",plugin={"html:target/cucumber.html"})//for understanding report  //selected formate
public class TestNGTestRunner extends AbstractTestNGCucumberTests{ //cucumber able to understand and run testng//for cucumber not required

}

//src/test/java/Automation/cucumber/errorvalidation.feature---->to run one particular featurefile
//out of all feature files it will filter by only the error tag










//WHILE RUNNING THROUGH MAVEN OR JENKINS WITH PROFILE IT IS RUNNING ALL THE TESTS ????