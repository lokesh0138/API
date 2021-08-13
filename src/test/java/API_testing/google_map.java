package API_testing;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.goole_adress;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
public class google_map {
	/*public static String pid()
	{
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(add_palce.addp())
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
				System.out.println(response);
				JsonPath js=new JsonPath(response);
				String place_id=js.getString("place_id");
				System.out.println(place_id);
				return place_id;
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Add place
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(add_palce.addp())
		.when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", equalTo("Apache/2.4.18 (Ubuntu)")).extract().response().asString();
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String pla_id=js.getString("place_id");
		System.out.println(pla_id);
		String addr="7095 Summer walk, USA";
		// update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(update_place.update(pla_id,addr))
		.when().put("maps/api/place/update/json").then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"))
		.header("Server", equalTo("Apache/2.4.18 (Ubuntu)"));
		//Get place
		/*String get_res=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", pla_id).when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();*/
		
		goole_adress gd=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", pla_id).expect()
		.defaultParser(Parser.JSON).when().get("maps/api/place/get/json")
		.as(goole_adress.class);
		System.out.println(gd.getLocation().getLatitude());
				
		//JsonPath js1=new JsonPath(get_res);
		//JsonPath js1=json_utility.json(get_res);
		//String Actual=js1.getString("address");
		/*given().log().all().queryParam("key", "qaclick123").queryParam("place_id", pla_id).when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo(addr));*/
		//Assert.assertEquals(Actual,addr);
		
		
	}

}
