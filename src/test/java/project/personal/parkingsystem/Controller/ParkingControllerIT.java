package project.personal.parkingsystem.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import project.personal.parkingsystem.Controller.DTO.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//subir em uma porta aleatoria
class ParkingControllerIT {
	
	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		System.out.println(randomPort);
		RestAssured.port = randomPort;
	}

	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given().when()
		.get("/").then()
		.statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenCreateThenCheckIsCreated() {
		
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("Amarelo");
		createDTO.setLicense("CCC-5555");
		createDTO.setModel("Brasilia");
		createDTO.setState("DF");
		
		RestAssured.given().when().body(createDTO)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.post("/").then()
		.statusCode(HttpStatus.CREATED.value()).body("license",Matchers.equalTo("CCC-5555"))
		.body("color", Matchers.equalTo("Amarelo"));
	}

}
