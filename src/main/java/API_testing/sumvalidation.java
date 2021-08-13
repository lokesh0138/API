package API_testing;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class sumvalidation {
	@Test
	public void sumvalidate()
	{
		JsonPath js=new JsonPath(add_palce.sumvalidate());
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		int sum=0;
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount=price*copies;
			sum=sum+amount;
			
		}
		Assert.assertEquals(sum, purchaseamount);
	}

}
