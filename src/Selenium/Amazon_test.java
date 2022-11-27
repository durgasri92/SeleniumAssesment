package Selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Amazon_test {

    public WebDriver driver;

    public static void main(String[] args) throws Exception {


        Amazon_test call = new Amazon_test();
        call.launchapplication();
        call.listofitems();
        call.closeApplication();

    }





    public void launchapplication() {

        System.setProperty("webdriver.chrome.driver"
                , "C:\\Users\\KRISHNA CHAITANYA\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");
        System.out.println("Amazon application launched successfully");

    }


    public void listofitems() {
      //click All button to open menu

        driver.findElement(By.xpath("//div[@class='nav-left']/a")).click();

        //print all the links count in All menu
        int submenus = driver.findElements(By.xpath("//ul[@class='hmenu hmenu-visible']/li")).size();

        System.out.println("The Links present in All menu==>"+submenus);
        //to print the text of submenus
        for(int i=1;i<submenus;i++)
        {
           String SubLinkname = driver.findElement(By.xpath("//ul[@class='hmenu hmenu-visible']/li[" + i + "]")).getText();
            System.out.println("The sub link name when position" + " " +i+" "+"is"+" "+SubLinkname);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.findElement(By.xpath("//div[@class='nav-sprite hmenu-close-icon']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        //Search for a similar item by giving input
       driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("screw driver single piece");
       //click on search
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        //capture name of products displayed
        List<WebElement> nameofitems = driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));

        System.out.println("Number of products available are::"+nameofitems.size());

        //get the cost of products
        List<WebElement> priceofitem = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        //Array for the list of itemnames
        List<String> names = new ArrayList<>();
        //Array for list of prices of product
        List<String> prices = new ArrayList<>();

        // get the name of each product using for each loop
        for (WebElement ele1 : nameofitems)
            names.add(ele1.getAttribute("innerHTML"));
        //get the price
        for (WebElement ele2 : priceofitem)
            prices.add(ele2.getAttribute("innerHTML"));
        for (int i = 0; i < nameofitems.size(); i++) {
            System.out.println("Product Name is : " + names.get(i) + " " + " " + "and Price is : " + prices.get(i));
        }




        }

    public void closeApplication()
    {
        driver.quit();
    }


}
