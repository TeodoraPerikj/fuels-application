package mk.ukim.finki.fuels_application.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class HomePage extends AbstractPage{

    private WebElement locationButton;

    @FindBy(className = "streetsShown")
    private List<WebElement> streets;

    private WebElement location;

    private WebElement showAllFuels;

    private WebElement showAllStreets;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static HomePage openHomePage(WebDriver driver) {
        get(driver, "/home");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, HomePage.class);

    }

    public static ShowFuelsPage doHomePage(WebDriver driver, HomePage homePage, String street) {
        homePage.location.click();
        homePage.location.findElement(By.xpath("//option[. = '" + street + "']")).click();

        homePage.locationButton.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, ShowFuelsPage.class);
    }

    public void assertElements(int streetsNum){
        Assert.assertEquals("options do not match", streetsNum, this.getStreets().size());
//        Assert.assertEquals("show fuels do not match", showFuelsNum, this.getShowAllFuels().);
//        Assert.assertEquals("show streets do not match", showStreetsNum, this.getShowAllStreets().size());
    }

    public static ShowAllFuelsPage showAllFuelsPage(WebDriver webDriver, HomePage homePage){

        homePage.showAllFuels.click();

        get(webDriver, "/showAndAddFuel");
        System.out.println(webDriver.getCurrentUrl());
        return PageFactory.initElements(webDriver, ShowAllFuelsPage.class);
    }

    public static ShowAllStreetsPage showAllStreetsPage(WebDriver webDriver, HomePage homePage){

        get(webDriver, "/home");
        System.out.println(webDriver.getCurrentUrl());
        homePage.showAllStreets.click();

        get(webDriver, "/showAndAddStreet");
        System.out.println(webDriver.getCurrentUrl());
        return PageFactory.initElements(webDriver, ShowAllStreetsPage.class);
    }
}
