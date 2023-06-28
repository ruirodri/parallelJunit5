package com.everis;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Exemplo de utilização do Healenium
 */
public class BrowserTest {

    static String browser = "chrome";

    /**
     * Executa o teste
     * 
     * @throws IOException
     */
    @ParameterizedTest
    @Tag("ui")
    @CsvFileSource(resources = "/data2.csv", numLinesToSkip = 1)
    public void testParallellGoogle(String p1, String p2, String p3) throws IOException {

        MutableCapabilities capabilities = initCapabilities(getBrowserName());
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        driver.get("http://www.google.com");
        WebElement el = driver.findElement(By.name("q"));
        el.clear();
        el.sendKeys(p1 + " " + p2 + " " + p3 + " " + capabilities.getBrowserName());
        Utils.generateScreenshot(driver);
        driver.quit();
    }

    private synchronized String getBrowserName() {
        browser = (browser.equals("chrome") ? "firefox" : "chrome");
        return new String(browser);
    }

    private MutableCapabilities initCapabilities(String oBrowser) {
        if (oBrowser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true); 
            return options;
        } else {
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true); 
            return options;
        }
    }

}
