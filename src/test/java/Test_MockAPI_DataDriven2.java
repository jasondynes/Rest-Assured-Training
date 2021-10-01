import io.restassured.http.*;
import org.json.simple.*;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class Test_MockAPI_DataDriven2 {

    @DataProvider
    public Object[][] DataForPost(){
        // this example is static hard coded data
        /*Object[][] data = new Object[2][3];
        data[0][0] = "Albert";
        data[0][1] = "Einstein";
        data[0][2] = 2;
        data[1][0] = "Thomas";
        data[1][1] = "Edison";
        data[1][2] = 1;
        return data;
        */
        // more flexible implementation to above is below
        return new Object[][]{
                {"Graham", "Bell", 1},
                {"Henry", "Ford", 2}
        };
    }

    @Test(dataProvider = "DataForPost", priority = 2)
    public void test_post(String firstName, String lastName, int subjectId){
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("subjectId", subjectId);

        baseURI = "http://localhost:3000/";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Content-Type", "Application/json")
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();
    }
}
