package project.personal.parkingsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.personal.parkingsystem.Model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String>{

}
