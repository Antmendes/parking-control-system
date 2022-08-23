package project.personal.parkingsystem.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import project.personal.parkingsystem.Controller.DTO.ParkingCreateDTO;
import project.personal.parkingsystem.Controller.DTO.ParkingDTO;
import project.personal.parkingsystem.Controller.Mapper.ParkingMapper;
import project.personal.parkingsystem.Model.Parking;
import project.personal.parkingsystem.Service.ParkingService;


@RestController
@RequestMapping("/")
@Api(tags = "Parking Controller")
public class ParkingController {
	
	
	private final ParkingService parkingService; // injeçao pelo construtor
	private final ParkingMapper parkingMapper;
	
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
		}
		
	
	@GetMapping
	@ApiOperation("Find all parkings")
	public ResponseEntity<List<ParkingDTO>> findAll(){
		 
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		
		return ResponseEntity.ok(result);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		 
		Parking parking = parkingService.findById(id);
		
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
		
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable String id){
		 parkingService.delete(id);
		 
		 return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
		
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.update(id, parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id){
		
		Parking parking = parkingService.checkOut(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	
	
	
		
	


}
