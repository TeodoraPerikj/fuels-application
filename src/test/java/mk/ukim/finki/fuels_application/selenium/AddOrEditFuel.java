package mk.ukim.finki.fuels_application.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditFuel extends AbstractPage{

    private WebElement imageUrl;

    private WebElement fuelName;

    private WebElement fuelLat;

    private WebElement fuelLong;

    private WebElement pageLink;

    private WebElement addFuelSubmit;

    public AddOrEditFuel(WebDriver driver) {
        super(driver);
    }

    public static ShowAllFuelsPage addFuel(WebDriver driver, String imageUrl, String fuelName,
                                             String fuelLat, String fuelLong, String pageLink) {
        get(driver, "/showAndAddFuel/addNewFuel");
        System.out.println(driver.getCurrentUrl());
        AddOrEditFuel addOrEditFuel = PageFactory.initElements(driver, AddOrEditFuel.class);
        addOrEditFuel.imageUrl.sendKeys(imageUrl);
        addOrEditFuel.fuelName.sendKeys(fuelName);
        addOrEditFuel.fuelLat.sendKeys(fuelLat);
        addOrEditFuel.fuelLong.sendKeys(fuelLong);
        addOrEditFuel.pageLink.sendKeys(pageLink);

        addOrEditFuel.addFuelSubmit.click();
        return PageFactory.initElements(driver, ShowAllFuelsPage.class);
    }

    public static ShowAllFuelsPage editFuel(WebDriver driver, WebElement editButton, String imageUrl,
                                                 String fuelName, String fuelLat, String fuelLong, String pageLink) {

        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditFuel addOrEditFuel = PageFactory.initElements(driver, AddOrEditFuel.class);
        addOrEditFuel.imageUrl.sendKeys(imageUrl);
        addOrEditFuel.fuelName.sendKeys(fuelName);
        addOrEditFuel.fuelLat.sendKeys(fuelLat);
        addOrEditFuel.fuelLong.sendKeys(fuelLong);
        addOrEditFuel.pageLink.sendKeys(pageLink);

        addOrEditFuel.addFuelSubmit.click();
        return PageFactory.initElements(driver, ShowAllFuelsPage.class);
    }

}
