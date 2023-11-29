package Utils;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends PageObject {

    protected WebDriver driver;
    protected Duration timeOut = Duration.ofSeconds(20);
    protected WebDriverWait wait;

    //constructor
    public BasePage(WebDriver driver) {this.driver = driver;}

    //Espera Explicita
    public WebElement esperaExplicita(By locator){
        wait = new WebDriverWait(this.driver, timeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //Click
    public void click (By locator){
        Actions action = new Actions(driver);
        action.moveToElement(esperaExplicita(locator)).click().perform();
    }

    //Texto
    public void addText(By locator, String text){
        this.driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator){
        return this.driver.findElement(locator).getText();
    }

    public String getType(By locator){
       return this.driver.findElement(locator).getAttribute("type");
    }


    public boolean elementExist (By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public List<WebElement> findRows(By locator){
        return driver.findElements(locator);
    }

    public List<WebElement> findColumns(WebElement webElement, By locator){
        return webElement.findElements(locator);
    }


}
