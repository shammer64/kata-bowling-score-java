package com.shammer.coaching.bowling;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = "com.shammer.coaching.bowling",
        features = "classpath:features"
)
public class CucumberTests {
}
