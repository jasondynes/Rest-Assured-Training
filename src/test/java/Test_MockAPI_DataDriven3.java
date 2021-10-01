import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class Test_MockAPI_DataDriven3 {

    @DataProvider(name = "DeleteData")
    public Object[] DeleteData() {
        // more flexible implementation to above is below
        return new Object[]{
                4,5,6,7
        };
    }

    @Test(priority = 3, dataProvider = "DeleteData")
    public void test_multipleDelete (int userId){
        baseURI = "http://localhost:3000/";
        when()
                .delete("/users/" + userId)
                .then().statusCode(200)
                .log().all();
    }
}
