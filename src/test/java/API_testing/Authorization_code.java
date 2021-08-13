package API_testing;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import pojo.api;
import pojo.authenticate;
import pojo.webAutomation;
public class Authorization_code {

	public static void main(String[] args) {
		
		//get code
		String[] expected={"Selenium Webdriver Java","Cypress","Protractor"};
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWjSRgRkE998JFT0r3PT9NcpLAWY75mD3TgERt_7ch8wCYwoD_LYdeR0HIw-Hk492Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		//Access token
		String accesstokenres=given().urlEncodingEnabled(false).queryParams("code",code)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accesstokenres);
		JsonPath js1=new JsonPath(accesstokenres);
		String accessToken=js1.getString("access_token");
		
		//output
		authenticate response=	given().queryParam("access_token", accessToken).
				expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(authenticate.class);
		List<api>Apicourse=response.getCourses().getApi();
		ArrayList<String> a=new ArrayList<String>();
		for(int i=0;i<Apicourse.size();i++)
		{
			if(Apicourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				System.out.println(Apicourse.get(i).getPrice());
				break;
					}
		}
		List<String> expectedList=	Arrays.asList(expected);
		List<webAutomation> webauto=response.getCourses().getWebAutomation();
		for(int j=0;j<webauto.size();j++)
		{
			a.add(webauto.get(j).getCourseTitle());
		}
		Assert.assertTrue(a.equals(expectedList));
		
	}

}
