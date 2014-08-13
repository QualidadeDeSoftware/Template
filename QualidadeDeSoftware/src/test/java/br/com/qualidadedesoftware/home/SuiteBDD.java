import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format = { "pretty", "html:target/cucumber-html-report/"},
		glue = {"br.com.qualidadedesoftware.template"},
		tags = {"~@Implementando", "~@Pronto"},
		features = "src/test/java/"
		)
public class SuiteBDD {
	@BeforeClass
    public static void setUp() throws Exception {
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }
}
