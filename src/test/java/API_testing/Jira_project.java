package API_testing;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
public class Jira_project {

	public static void main(String[] args) {
		// login

		RestAssured.baseURI="http://localhost:8080/";
		SessionFilter session=new SessionFilter();
		String sessionid=given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json").body("{ \"username\": \"lokeshgg0138\", \"password\": \"Johnsnow@0138\" }")
		.filter(session).when().post("rest/auth/1/session").then().log().all().extract().response().toString();
		//Create issue
	/*	String issueid=given().filter(session).log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"	\"fields\": {\r\n" + 
				"        \"project\": {\r\n" + 
				"            \"key\": \"API\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\": \"Automation issue creation\",\r\n" + 
				"         \"description\": \"First automation issue\",\r\n" + 
				"        \"issuetype\": {\r\n" + 
				"            \"name\": \"Bug\"\r\n" + 
				"        }\r\n" + 
				"    \r\n" + 
				"}\r\n" + 
				"}").when().post("rest/api/2/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
		System.out.println(issueid);
		JsonPath js=new JsonPath(issueid);
		int issueno=js.getInt("id");
		System.out.println(issueno);*/
		//add comment
		String expectedcomment="hi how are you?";
		String comment_response=given().filter(session).log().all().pathParam("key", 10010).header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \""+expectedcomment+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").when().post("rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201).extract()
		.response().asString();
		JsonPath js1=new JsonPath(comment_response);
		String commentno=js1.get("id").toString();
		System.out.println(commentno);
		//Add attachment
		given().filter(session).log().all().header("X-Atlassian-Token","no-check").pathParam("key", 10010)
		.header("Content-Type","multipart/form-data").multiPart("file",new File("xyz.docx")).when()
		.post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		//Get issue
		String getresponse=given().filter(session).pathParam("key", 10010).queryParam("fields", "comment").log().all().when().get("rest/api/2/issue/{key}").then().log()
		.all().extract().response().asString();
		JsonPath js2=new JsonPath(getresponse);
		int totalcomment=js2.getInt("fields.comment.comments.size()");
		String actualcomment="";
		for(int i=0;i<totalcomment;i++)
		{
			String commentsubid=js2.get("fields.comment.comments["+i+"].id").toString();
			if(commentsubid.equalsIgnoreCase(commentno))
			{
				actualcomment=js2.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(actualcomment);
				break;
			}
		}
		Assert.assertEquals(actualcomment, expectedcomment);
		

		
	}

}
