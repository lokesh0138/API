package API_testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.location_serialize;
import pojo.serializepojo;

public class serializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		serializepojo sc=new serializepojo();
		sc.setAddress("29, side layout, cohen 09");
		sc.setAccuracy(50);
		sc.setLanguage("French-IN");
		sc.setName("Frontline house");
		sc.setPhone_number("(+91) 983 893 3937");
		sc.setWebsite("https://google.com");
		location_serialize lc=new location_serialize();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		sc.setLocation(lc);
		ArrayList<String> ar=new ArrayList<String>();
		ar.add("shoe park");
		ar.add("shop");
		sc.setTypes(ar);
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		RequestSpecification req=new RequestSpecBuilder().addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).
		setBaseUri("https://rahulshettyacademy.com").build();
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		//Add place
		RequestSpecification requeest=given().spec(req).log().all().body(sc);
		
		Response response=requeest.when().post("maps/api/place/add/json").then().spec(res).extract().response();
		String output=response.asString();
		System.out.println(output);

	}

}
