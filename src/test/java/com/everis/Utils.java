package com.everis;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    /**
     * Método que gera um screenshot da tela
     * 
     * @param driver
     * @throws IOException
     */
    public static void generateScreenshot(WebDriver driver) throws IOException {
        Date horaAtual = new Date();
        synchronized (Utils.class) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("evidence/screen-" + formato.format(horaAtual) + ".png"));
    }

    /**
     * Método que identifica um campo na tela por xpath e o preenche com o valor
     * informado
     * 
     * @param xpath
     * @param valor
     */
    public static void fillByXpath(WebDriver driver, String xpath, String valor) {
        WebElement theElement = driver.findElement(By.xpath(xpath));
        theElement.clear();
        theElement.sendKeys(valor);
    }

    public static WebElement getWebElementByXpath(WebDriver driver, String xpath) {
        return (WebElement) (driver.findElement(By.xpath(xpath)));
    }

}
