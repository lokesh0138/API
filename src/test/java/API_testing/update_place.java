package API_testing;

public class update_place {
	//public String pid;
/*	public update_place(String place_id) {
		// TODO Auto-generated constructor stub
		this.pid=place_id;
	}*/

	public static String update(String id,String address)
	{
		
		return"{\r\n" + 
				"\"place_id\":\""+id+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"";
	}
}


