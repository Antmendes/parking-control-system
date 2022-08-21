package project.personal.parkingsystem.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import project.personal.parkingsystem.Exception.ParkingNotFoundException;
import project.personal.parkingsystem.Model.Parking;

@Service
public class ParkingService {
	
	
	private static Map<String, Parking> parkingMap = new HashMap();
	
	
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList()); // java 8
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public Parking findById(String id) {
		
	    Parking parking =  parkingMap.get(id);
		 if(parking == null) {
			 throw new ParkingNotFoundException(id);
		 }
		 return parking;
	}

	public Parking create(Parking parkingCreate) {
		
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid, parkingCreate);
		
		return parkingCreate;
		
	}

	public void delete(String id) {
	    findById(id);
		parkingMap.remove(id);
		
	}

	public Parking update(String id,Parking parkingCreate) {
		 Parking byId = findById(id);
		 byId.setColor(parkingCreate.getColor());
		 parkingMap.replace(id, byId);
		 return byId;
	}

	public Parking exit(String id) {
		//recuperar o estacionamento
		//atualizar data de saida
		//calcular o valor
		return null;
	}

}