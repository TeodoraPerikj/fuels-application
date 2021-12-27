package mk.ukim.finki.fuels_application.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ShowAllStreetsPage extends AbstractPage{

    @FindBy(css = "tr[class=street]")
    private List<WebElement> streetRows;

    @FindBy(className = "editButton")
    private List<WebElement> editButtons;

    @FindBy(className = "deleteButton")
    private List<WebElement> deleteButtons;

    @FindBy(className = "addButton")
    private List<WebElement> addButtons;

    public ShowAllStreetsPage(WebDriver driver) {
        super(driver);
    }

    public void assertElements(int editNum, int deleteNum, int addNum, int streetsNum){
        Assert.assertEquals("rows do not match", streetsNum, this.getStreetRows().size());
        Assert.assertEquals("delete do not match", deleteNum, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editNum, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addNum, this.getAddButtons().size());
    }
}
