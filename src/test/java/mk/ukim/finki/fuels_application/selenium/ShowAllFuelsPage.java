package mk.ukim.finki.fuels_application.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ShowAllFuelsPage extends AbstractPage{

    @FindBy(css = "tr[class=fuel]")
    private List<WebElement> fuelRows;

    @FindBy(className = "editButton")
    private List<WebElement> editButtons;

    @FindBy(className = "deleteButton")
    private List<WebElement> deleteButtons;

    @FindBy(className = "addButton")
    private List<WebElement> addButtons;

    public ShowAllFuelsPage(WebDriver driver) {
        super(driver);
    }

    public void assertElements(int editNum, int deleteNum, int addNum, int fuelsNum){
        Assert.assertEquals("rows do not match", fuelsNum, this.getFuelRows().size());
        Assert.assertEquals("delete do not match", deleteNum, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editNum, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addNum, this.getAddButtons().size());
    }

}
