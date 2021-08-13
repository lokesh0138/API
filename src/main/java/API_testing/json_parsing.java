package API_testing;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class json_parsing {
	// Print No of courses returned by API
	static JsonPath js=new JsonPath(add_palce.sumvalidate());
	@Test(priority=1)
	public void coursecount()
	{
		//JsonPath js=new JsonPath(add_palce.sumvalidate());
		int count=js.getInt("courses.size()");
		System.out.println(count);
	
	}
	//Print Purchase Amount
	@Test(priority=2)
	public void purchaseamount()
	{
		//JsonPath js=new JsonPath(add_palce.sumvalidate());
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamount);
	}
	//Print Title of the first course
	@Test(priority=3)
	public void firsttitle()
	{
		System.out.println(js.get("courses[0].title").toString());
		//JsonPath js=new JsonPath(add_palce.sumvalidate());
		
	}
	 //Print All course titles and their respective Prices
	@Test(priority=4)
	public void allcourse_tile()
	{
		//int count=json_parsing.coursecount();
		//json_parsing jp=new json_parsing();
		//int count=jp.coursecount();
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			System.out.println(js.get("courses["+i+"].title"));
			System.out.println(js.getInt("courses["+i+"].price"));
		}
	}
	  //Print no of copies sold by RPA Course
	@Test(priority=5)
	public void RPA_copies()
	{
		int count=js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			String title=js.get("courses["+i+"].title").toString();
			if(title.equalsIgnoreCase("RPA"))
			{
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}	
	}
	//SumValidation 
	@Test(priority=6)
	public void sumvalidation()
	{
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
