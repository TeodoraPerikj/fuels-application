package mk.ukim.finki.fuels_application.selenium;

import mk.ukim.finki.fuels_application.model.Fuel;
import mk.ukim.finki.fuels_application.model.Role;
import mk.ukim.finki.fuels_application.model.Street;
import mk.ukim.finki.fuels_application.model.User;
import mk.ukim.finki.fuels_application.service.AuthService;
import mk.ukim.finki.fuels_application.service.FuelService;
import mk.ukim.finki.fuels_application.service.StreetService;
import mk.ukim.finki.fuels_application.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    AuthService authService;

    @Autowired
    FuelService fuelService;

    @Autowired
    StreetService streetService;


    @Autowired
    UserService userService;


    private HtmlUnitDriver driver;

    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static String pageLink = "pageLink";
    private static String imageLink = "imageLink";

    private static String option = "Дозволувам споделување на мојата локација";

    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {
            regularUser = userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }


    @Test
    public void testScenario() throws Exception {

        LoginPage loginPage = LoginPage.openLogin(this.driver);
        AcceptPage acceptPage = LoginPage.doLogin(this.driver, loginPage,
                adminUser.getUsername(), admin);

        HomePage homePage = AcceptPage.doAcceptPage(this.driver, acceptPage, option);
        homePage.assertElements(0 );

        ShowAllFuelsPage showAllFuelsPage = HomePage.showAllFuelsPage(this.driver, homePage);
        showAllFuelsPage.assertElements(0,0,1,0);

        showAllFuelsPage = AddOrEditFuel.addFuel(this.driver, imageLink, "f1",
              "153.12", "88.9",pageLink);

        showAllFuelsPage.assertElements(1,1,1,1);

        showAllFuelsPage = AddOrEditFuel.addFuel(this.driver, imageLink, "f2",
                "150.2", "90.44",pageLink);

        showAllFuelsPage.assertElements(2,2,1,2);

        showAllFuelsPage.getDeleteButtons().get(1).click();
        showAllFuelsPage.assertElements(1,1,1,1);

        showAllFuelsPage = AddOrEditFuel.addFuel(this.driver, imageLink, "f2",
                "150.2", "90.44",pageLink);

        showAllFuelsPage.assertElements(2,2,1,2);

        ShowAllStreetsPage showAllStreetsPage = HomePage.showAllStreetsPage(this.driver, homePage);
        showAllStreetsPage.assertElements(0,0,1,0);

        showAllStreetsPage = AddOrEditStreet.addStreet(this.driver,"s1",
                "155.15", "89.9");

        showAllStreetsPage.assertElements(1,1,1,1);

        showAllStreetsPage = AddOrEditStreet.addStreet(this.driver, "s2",
                "154.15", "89.9");

        showAllStreetsPage.assertElements(2,2,1,2);

        showAllStreetsPage.getDeleteButtons().get(1).click();
        showAllStreetsPage.assertElements(1,1,1,1);

        showAllStreetsPage = AddOrEditStreet.addStreet(this.driver, "s2",
                "154.15", "89.9");

        showAllStreetsPage.assertElements(2,2,1,2);

        loginPage = LoginPage.logout(this.driver);

        acceptPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);

        homePage = AcceptPage.doAcceptPage(this.driver, acceptPage, option);
        homePage.assertElements(2);

        ShowFuelsPage showFuelsPage = HomePage.doHomePage(this.driver, homePage, "s2");
        showFuelsPage.assertElements(2);

        ShowMapPage showMapPage = ShowFuelsPage.doShowFuelsPage(this.driver, showFuelsPage);

        loginPage = LoginPage.logout(this.driver);
    }


}
