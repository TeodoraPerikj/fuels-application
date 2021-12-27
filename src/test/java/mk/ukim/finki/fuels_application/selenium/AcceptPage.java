package mk.ukim.finki.fuels_application.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AcceptPage extends AbstractPage{

    private WebElement acceptButton;
    
    private WebElement accept;

    public AcceptPage(WebDriver driver) {
        super(driver);
    }

    public static AcceptPage openAcceptPage(WebDriver driver) {
        get(driver, "/acceptShowingLocation");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, AcceptPage.class);

    }

    public static HomePage doAcceptPage(WebDriver driver, AcceptPage acceptPage, String option) {

        acceptPage.accept.click();
        acceptPage.accept.findElement(By.xpath("//option[. = '" + option + "']")).click();

        acceptPage.acceptButton.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, HomePage.class);
    }
}
