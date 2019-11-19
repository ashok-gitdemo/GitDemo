package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import APITesting.com.org.AdvancedClasses.InfoAdv;
import APITesting.com.org.AdvancedClasses.PostArray;
import APITesting.com.org.Classes.ComplexClass;
import APITesting.com.org.Classes.Info;
import APITesting.com.org.Classes.PostStrGtr;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class JsonServerRequests {

	// @Test
	public void Test_01() {
		Response resp = given().when().get("http://localhost:3000/posts");
		System.out.println("Get request response: " + resp.asString());
	}

	// @Test
	public void Test_02() {
		Response resp = given().body(" {\"id\": \"2\", \"title\":\"dummy\", \"author\":\"Ashok\"} ").when()
				.contentType(ContentType.JSON).post("http://localhost:3000/posts");
		System.out.println("Get request response: " + resp.asString());
	}

	// @Test
	public void Test_03() {
		PostStrGtr psg = new PostStrGtr();
		psg.setID("4");
		psg.setTitle("GOT");
		psg.setAuthor("Kumar");
		Response resp = given().when().contentType(ContentType.JSON).body(psg).post("http://localhost:3000/posts");
		System.out.println("Post request response: " + resp.asString());
	}

	// @Test
	public void Test_04() {
		PostStrGtr psg = new PostStrGtr();
		psg.setID("4");
		psg.setTitle("Updated title");
		psg.setAuthor("name updated");
		Response resp = given().when().contentType(ContentType.JSON).body(psg).put("http://localhost:3000/posts/4");
		System.out.println("Put request response: " + resp.asString());
	}

	// @Test
	public void Test_05() {
		PostStrGtr psg = new PostStrGtr();
		// psg.setID("2");
		psg.setTitle("Updated title via patch request");
		// psg.setAuthor("name updated");
		Response resp = given().when().contentType(ContentType.JSON).body(psg).patch("http://localhost:3000/posts/2");
		System.out.println("Put request response: " + resp.asString());
	}

	// @Test
	public void Test_06() {
		Response resp = given().when().delete("http://localhost:3000/posts/4");
		System.out.println("Put request response: " + resp.asString());
	}

	// @Test
	public void Test_07() {
		Info info = new Info();
		info.setEmail("tester@gmail.com");
		info.setPhone("65875987");
		info.setAddress("India");

		ComplexClass cc = new ComplexClass();
		cc.setId("2");
		cc.setTitle("GOT");
		cc.setAuthor("author");
		cc.setInfo(info);

		Response resp = given().when().contentType(ContentType.JSON).body(cc).post("http://localhost:3000/posts/");
		System.out.println("Put request response: " + resp.asString());
	}

	@Test
	public void Test_08() {
		InfoAdv info1 = new InfoAdv();
		info1.setEmail("new@gmail.com");
		info1.setPhone("658734235987");
		info1.setAddress("Aus");
		
		InfoAdv info2 = new InfoAdv();
		info2.setEmail("old@gmail.com");
		info2.setPhone("34235987");
		info2.setAddress("Eng");
		
		PostArray pa = new PostArray();
		pa.setId("2");
		pa.setTitle("newTitle");
		pa.setAuthor("Subik");
		pa.setInfo(new InfoAdv[]{info1, info2});
		
		Response resp = given().when().contentType(ContentType.JSON).body(pa).
						post("http://localhost:3000/posts/");
		
		System.out.println(resp.asString());
	}
}
