package mk.ukim.finki.fuels_application.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditStreet extends AbstractPage{

    private WebElement streetName;

    private WebElement streetLat;

    private WebElement streetLong;

    private WebElement addStreetSubmit;

    public AddOrEditStreet(WebDriver driver) {
        super(driver);
    }

    public static ShowAllStreetsPage addStreet(WebDriver driver, String streetName, String streetLat,
                                             String streetLong) {
        get(driver, "/showAndAddStreet/addNewStreet");
        System.out.println(driver.getCurrentUrl());
        AddOrEditStreet addOrEditStreet = PageFactory.initElements(driver, AddOrEditStreet.class);
        addOrEditStreet.streetName.sendKeys(streetName);
        addOrEditStreet.streetLat.sendKeys(streetLat);
        addOrEditStreet.streetLong.sendKeys(streetLong);

        addOrEditStreet.addStreetSubmit.click();
        return PageFactory.initElements(driver, ShowAllStreetsPage.class);
    }

    public static ShowAllStreetsPage editStreet(WebDriver driver, WebElement editButton,
                                                String streetName, String streetLat, String streetLong) {

        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditStreet addOrEditStreet = PageFactory.initElements(driver, AddOrEditStreet.class);
        addOrEditStreet.streetName.sendKeys(streetName);
        addOrEditStreet.streetLat.sendKeys(streetLat);
        addOrEditStreet.streetLong.sendKeys(streetLong);

        addOrEditStreet.addStreetSubmit.click();
        return PageFactory.initElements(driver, ShowAllStreetsPage.class);
    }
}
