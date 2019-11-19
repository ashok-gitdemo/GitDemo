package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class WeatherGetRequest {

	//@Test
	public void Test_01() {
		Response resp = given().when().get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		System.out.println(resp.asString());
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		// ResponseSpecification checkStatusCodeAndContentType =
		// new ResponseSpecBuilder().
		// expectStatusCode(200).
		// expectContentType(ContentType.JSON).
		// build();
		// checkStatusCodeAndContentType.request().given().when().get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		//
	}

	//@Test
	public void Test_02() {
		Response resp = given().param("q", "Chennai").param("appid", "b6907d289e10d714a6e88b30761fae22").when()
				.get("https://samples.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);

		if (resp.getStatusCode() == 200) {
			System.out.println("API is working fine");
		} else {
			System.out.println("API is not working");
		}
	}

	//@Test
	public void Test_03() {
		given().param("q", "Chennai").param("appid", "b6907d289e10d714a6e88b30761fae22").when()
				.get("https://samples.openweathermap.org/data/2.5/weather").then().assertThat().statusCode(200);
	}

	
	//@Test
	public void Test_04() {
		String WeatherReport = given().
				param("id", "2643743").
				param("appid", "b6907d289e10d714a6e88b30761fae22").when()
				.get("https://samples.openweathermap.org/data/2.5/weather").
				then().contentType(ContentType.JSON).extract().path("weather[0].description");
		System.out.println("London weather: " +WeatherReport);
	}
	
	//@Test
	public void Test_05() {
		Response resp = given().
				param("id", "2643743").
				param("appid", "b6907d289e10d714a6e88b30761fae22").when()
				.get("https://samples.openweathermap.org/data/2.5/weather");
		
		String actualReport = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");
	    String expectedReports = "scattered clouds";
	    
	    if (actualReport.equalsIgnoreCase(expectedReports)) {
	    	System.out.println("Test case passed");
	    }else{
	    	System.out.println("Test case failed");
	    }
	}
	
	
	//@Test
	public void Test_06() {
		Response resp = given().param("id", "2643743").param("appid", "b6907d289e10d714a6e88b30761fae22")
				.when().get("https://samples.openweathermap.org/data/2.5/weather");
		
		String reportById = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");
		
		System.out.println("Weather report by ID : " + reportById);
		
		String lon = String.valueOf(resp.then().contentType(ContentType.JSON).extract().path("coord.lon"));
		
		System.out.println("Longitude : " +lon);
		
		String lat = String.valueOf(resp.then().contentType(ContentType.JSON).extract().path("coord.lat"));
		
		System.out.println("Latitude : " +lat);
		
		String reportByCoord = given().param("lat", lat).param("lon", lon).param("appid", "b6907d289e10d714a6e88b30761fae22")
				.when().get("https://samples.openweathermap.org/data/2.5/weather").
				then().extract().path("weather[0].description");
		
		System.out.println("Weather report by Coord: " +reportByCoord);
		
		Assert.assertEquals(reportById, reportByCoord);
		
		System.out.println("Weather report by ID : " + reportById);
		
	}
	
	@Test
	public void Test_07(){
		Response resp = given().param("Username", "root").param("Password", "Public123").
				when().get("https://10.104.118.220/webacs/api/v4/op/aaa/tacacsPlusServer");
		
		System.out.println("Response: " +resp.asString());
	}
}
