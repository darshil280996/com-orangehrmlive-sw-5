package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class UsersTest extends BaseTest {

    //Declare
    HomePage homePage;
    LoginPage loginPage;
    AdminPage adminPage;
    ViewSystemUsersPage viewSystemUsersPage;
    AddUserPage addUserPage;
    DashboardPage dashboardPage;


    @BeforeMethod(alwaysRun = true)
    public void inIt() {

        // Initialization
        homePage = new HomePage();
        loginPage = new LoginPage();
        adminPage = new AdminPage();
        viewSystemUsersPage = new ViewSystemUsersPage();
        addUserPage = new AddUserPage();
        dashboardPage = new DashboardPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void adminShouldAddUserSuccessFully() {

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        // click on admin tab
        adminPage.setClickOnAdmin();

        // verify "System user" text
        String actualText = viewSystemUsersPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");

        // click on "add" button
        viewSystemUsersPage.setClickingAddButton();

        // verify "add user" text
        String actualMessage = addUserPage.setVerifyAddUserText();
        String expectedMessage = "Add User";
        Assert.assertEquals(actualMessage, expectedMessage, "Add User is not displayed");

        // Select user role "Admin"
        addUserPage.setSelectAdmin();

        // enter Employee Name "Ananya Dash"
        addUserPage.setEnterEmployeeName("Ananya Dash");

        //	enter Username
        viewSystemUsersPage.setEnterUsername("Anaya.Dash");

        //	Select status "Disable"
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //	enter password
        homePage.setEnterPassword("admin123");

        //	enter Confirm Password
        addUserPage.setConfirmPassword("admin123");

        //	click On "Save" Button
        addUserPage.setClickOnSaveButton();
    }

    @Test(groups = {"smoke", "regression"})
    public void searchTheUserCreatedAndVerifyIt() {

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //click On "Admin" Tab
        adminPage.setClickOnAdmin();

        //Verify "System Users" Text
        String actualText = viewSystemUsersPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");

        //Enter Username
        viewSystemUsersPage.setEnterUsername("Ananya.Dash");

        //Select User Role
        addUserPage.setSelectUserRole();

        //Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //Click on "Search" Button
        viewSystemUsersPage.setEnterSearch();

        //Verify the User should be in Result list.
        String actualResult = viewSystemUsersPage.setVerifyTheResult();
        String expectedResult = "Anaya.Dash";
        Assert.assertEquals(actualResult, expectedResult, "Ananya.Dash is not displayed");

    }

    @Test(groups = {"regression"})
    public void verifyThatAdminShouldDeleteTheUserSuccessFully(){

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //click On "Admin" Tab
        adminPage.setClickOnAdmin();

        //Verify "System Users" Text
        String actualText = viewSystemUsersPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");

        //Enter Username
        viewSystemUsersPage.setEnterUsername("Ananya.Dash");

        //Select User Role
        addUserPage.setSelectUserRole();

        //Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //Click on "Search" Button
        viewSystemUsersPage.setEnterSearch();

        //Verify the User should be in Result list.
        String actualResult = viewSystemUsersPage.setVerifyTheResult();
        String expectedResult = "Anaya.Dash";
        Assert.assertEquals(actualResult, expectedResult, "Ananya.Dash is not displayed");

        //Click on Check box
        viewSystemUsersPage.setTickOnCheckBox();

        //Click on Delete Button
        viewSystemUsersPage.setClickOnDeleteButton();

        //Popup will display
        viewSystemUsersPage.notifyAll();

        //Click on Ok Button on Popup
        viewSystemUsersPage.acceptAlert();

        //verify message "Successfully Deleted"
        String actualMessage = viewSystemUsersPage.getTextFromAlert();
        String expectedMessage = "Successfully Deleted";
        Assert.assertEquals(actualMessage, expectedMessage, "Successfully Deleted message is not displayed");

    }

    @Test (groups = { "Regression" })
    public void searchTheDeletedUserAndVerifyTheMessageRecordFound() {

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //	click On "Admin" Tab
        adminPage.setClickOnAdmin();

        //	Verify "System Users" Text
        String actualText = viewSystemUsersPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");
        //	Enter Username
        viewSystemUsersPage.setEnterUsername("Ananya.Dash");
        //	Select User Role
        addUserPage.setSelectAdmin();
        //	Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();
        //	Click on "Search" Button
        viewSystemUsersPage.setEnterSearch();
        //	verify message "No Records Found"
    }


}
