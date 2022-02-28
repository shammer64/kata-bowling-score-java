package com.shammer.coaching.bowling;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = "com.pluralsight.bdd.steps",
        features = "classpath:features"
)
public class CucumberTests {
}
