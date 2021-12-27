package mk.ukim.finki.fuels_application.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ShowMapPage extends AbstractPage{

    private WebElement finalFuel;

    public ShowMapPage(WebDriver driver) {
        super(driver);
    }

    public static ShowMapPage openShowMapPage(WebDriver driver) {
        get(driver, "/showMap");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, ShowMapPage.class);
    }

    public void assertElement(String finalFuel){
        String fuel = this.getFinalFuel().toString();
        Assert.assertEquals("fuel is not right", finalFuel, fuel);
    }
}
