package Test;
import RequestBuilder.NdosiRequest;
import Utils.DatabaseConnection;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class NdosiTests {

    @BeforeClass
    public void setup() throws SQLException {
        DatabaseConnection.dbConnection();
    }


   @Test(priority = 1)
    public static void CreateNewUserTest(){
        NdosiRequest.createNdosiResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");

    }
    @Test(priority = 2)
    public static void LoginAdminTest(){
        NdosiRequest.loginAdminResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
    @Test(priority = 3)
    public static void ApproveUserRegistrationTest(){
        NdosiRequest.approveUserRegistrationResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(priority = 4)
    public static void updateUserRoleTest(){
        NdosiRequest.updateUserRoleResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
    @Test(priority = 5)
    public static void loginUserTest() {
        NdosiRequest.loginNdosiResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
    @Test(priority = 6)
    public static void getUserProfileTest(){
        NdosiRequest.getUserProfileResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
    @Test(priority = 7)
    public static void LoginAdminTestSecond(){
        NdosiRequest.loginAdminResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
    @Test(priority = 8)
    public static void deleteUserTest(){
        NdosiRequest.deleteUserResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

}
