package br.com.qualidadedesoftware.configuration;

import java.util.Properties;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public abstract class SeleniumTestCase extends TestCase 
{
	protected Properties prop = new Propriedades().getProp();
	protected static Selenium selenium;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
        selenium = new DefaultSelenium(
        		prop.getProperty("prop.selenium.server.host"), 
        		Integer.parseInt(prop.getProperty("prop.selenium.server.port")), 
        		prop.getProperty("prop.selenium.browser.start.command"),
        		prop.getProperty("prop.selenium.browser.url"));
        selenium.start();
        selenium.windowFocus();
        selenium.windowMaximize();
        selenium.setSpeed(prop.getProperty("prop.selenium.speed"));
        selenium.setTimeout(prop.getProperty("prop.selenium.timeout"));
	}
 
    @Override
    protected void tearDown() throws Exception {
    	super.tearDown();
    	selenium.stop();
    }
}
