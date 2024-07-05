package repository;

import enums.VehicleType;
import models.Vehicle;

public class VehicleRepository {
    public Vehicle getVehicleByVehicleNumber(String vehicleNumber) {
        Vehicle vehicle = new Vehicle();
        vehicle.setNumberPlate(vehicleNumber);
        vehicle.setVehicleType(VehicleType.CAR);
        return vehicle;
    }
}
