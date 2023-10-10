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
        WebDriver d = new HtmlUnitDriver();
        try {
            d.get("http://localhost:8080/CoffeeMaker_WebDB");
            assertEquals("CoffeeMaker", d.getTitle());


            d.get("http://localhost:8080/CoffeeMaker_WebDB/add_recipe.jsp");
            assertEquals("CoffeeMaker - Add Recipe", d.getTitle());
            d.findElement(By.name("name")).sendKeys("a");
            d.findElement(By.name("price")).sendKeys("1");
            d.findElement(By.name("amtCoffee")).sendKeys("1");
            d.findElement(By.name("amtMilk")).sendKeys("1");
            d.findElement(By.name("amtSugar")).sendKeys("1");
            d.findElement(By.name("amtChocolate")).sendKeys("1");
            d.findElement(By.name("submit")).click();


            d.get("http://localhost:8080/CoffeeMaker_WebDB/check_inventory.jsp");
            assertEquals("CoffeeMaker - Check Inventory", d.getTitle());
            String inv = d.findElement(By.name("inventory")).getText();


            d.get("http://localhost:8080/CoffeeMaker_WebDB/add_inventory.jsp");
            assertEquals("CoffeeMaker - Add Inventory", d.getTitle());
            d.findElement(By.name("amtCoffee")).sendKeys("1");
            d.findElement(By.name("amtMilk")).sendKeys("1");
            d.findElement(By.name("amtSugar")).sendKeys("1");
            d.findElement(By.name("amtChocolate")).sendKeys("1");
            d.findElement(By.name("submit")).click();


            d.get("http://localhost:8080/CoffeeMaker_WebDB/check_inventory.jsp");
            assertEquals("CoffeeMaker - Check Inventory", d.getTitle());
            assertFalse(inv.equals(d.findElement(By.name("inventory")).getText()));

        } finally {
            d.quit();
        }
    }
}
