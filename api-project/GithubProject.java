package API_Project;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.*;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.junit.jupiter.api.Order;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;


public class GithubProject {
    RequestSpecification requestSpec;
    String sshKey;
    String keyid;

    @BeforeClass
    public void setUp(){

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAuth(oauth2("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"))
                .setBaseUri("https://api.github.com").build();


    }

    @Test(priority = 1)
    public void PostKey(){
        String Base_URL = "/user/keys";

        sshKey ="{\"title\": \"sshKey\",\"key\":\"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDJQ2tIXpOBGwl6aW9+T/vlb0KQeBcCYDpOTvmOhkoZ0PLz0r3DGTtkmcEpibphn4jcVGVmJ92GYQ1O9OsvIFSQtBV6Bc1sZpltxUKQOWTK2qu1AD6E/Cb7l3ZVy//xndDqp+e04hX8gfNGl8hXFRrxHiq9xoE311wrGfHcf1BTQ9gznDL7fFH3vXzCLEB5OMB6b08R/tpOJ/zDVFiFiHa/CyOeOWRjE4XYkEUnShjdWCpjITnXF0u+TEDB4AHfjms23nLSkCPPo+SqBecMiQxPchaACsMuaqgxu+BQS+HdJCeyQUN7p6vNZ8gAIwVbZluoxj3HLMWpg8gAbs5qivzl\"}";

        //    Response response = given().spec(requestSpec).contentType(ContentType.JSON).body(sshKey).when().post(Base_URL);
        Response response = given().spec(requestSpec).body(sshKey).when().post(Base_URL);

        //String body = response.prettyPrint();
         keyid = response.then().extract().path("id").toString();
         response.then().statusCode(201);

    }

    @Test(priority = 2)
    public void GetKey(){
        String Base_URL = "/user/keys";
        //    Response response = given().spec(requestSpec).contentType(ContentType.JSON).body(sshKey).when().post(Base_URL);
        Response response = given().spec(requestSpec).when().get(Base_URL);

        String body = response.prettyPrint();
        Reporter.log(body);
        response.then().statusCode(200);

    }

    @Test(priority = 3)
    public void DelKey(){
        String Base_URL = "/user/keys/{id}";
       // System.out.println(keyid);
        Response response = given().spec(requestSpec).pathParam("id", keyid).delete(Base_URL);
        response.then().statusCode(204);

    }

}



