package API_testing;

import io.restassured.path.json.JsonPath;

public class json_utility {
	public static JsonPath json(String a)
	{
		JsonPath js1=new JsonPath(a);
		return js1;
	}

}
