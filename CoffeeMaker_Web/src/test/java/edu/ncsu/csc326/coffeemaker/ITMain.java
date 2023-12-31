package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ITMain extends TestCase {
    public void test() throws Exception {
        // WebDriver d = new FirefoxDriver(); // for debugging
        WebDriver d = new HtmlUnitDriver();
        try {
            d.get("http://localhost:8080/CoffeeMaker_Web");
            assertEquals("CoffeeMaker", d.getTitle());

            d.get("http://localhost:8080/CoffeeMaker_Web/add_inventory.jsp");
            assertEquals("CoffeeMaker - Add Inventory", d.getTitle());
            d.findElement(By.name("amtCoffee")).sendKeys("1");
            d.findElement(By.name("amtMilk")).sendKeys("1");
            d.findElement(By.name("amtSugar")).sendKeys("1");
            d.findElement(By.name("amtChocolate")).sendKeys("1");
            d.findElement(By.name("submit")).click();

            d.get("http://localhost:8080/CoffeeMaker_Web/check_inventory.jsp");
            assertEquals("CoffeeMaker - Check Inventory", d.getTitle());
            assertEquals("Coffee: 16 Milk: 16 Sugar: 16 Chocolate: 16", d.findElement(By.name("inventory")).getText());
        } finally {
            d.quit();
        }
    }
}
