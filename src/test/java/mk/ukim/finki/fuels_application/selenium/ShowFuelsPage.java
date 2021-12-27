package mk.ukim.finki.fuels_application.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ShowFuelsPage extends AbstractPage{

    @FindBy(className = "fuelsShown")
    private List<WebElement> fuels;

    private WebElement chosenFuel;

    private WebElement showMapButton;

    public ShowFuelsPage(WebDriver driver) {
        super(driver);
    }

    public static ShowFuelsPage openShowFuelsPage(WebDriver driver) {
        get(driver, "/showFuels");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, ShowFuelsPage.class);

    }

    public static ShowMapPage doShowFuelsPage(WebDriver driver, ShowFuelsPage showFuelsPage) {
        showFuelsPage.chosenFuel.click();

        showFuelsPage.showMapButton.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, ShowMapPage.class);
    }

    public void assertElements(int fuelsNum){
        Assert.assertEquals("inputs do not match", fuelsNum, this.getFuels().size());
    }
}
