package repository;

import enums.VehicleType;
import models.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {
    private Map<String, Vehicle> vehicleMap = new HashMap<>();

    public VehicleRepository() {
        initialise();
    }

    private void initialise() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setNumberPlate("ABCD-1234");
        vehicle1.setVehicleType(VehicleType.CAR);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setNumberPlate("BCDE-2345");
        vehicle2.setVehicleType(VehicleType.BIKE);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setNumberPlate("CDEF-3456");
        vehicle3.setVehicleType(VehicleType.SPECIAL_VEHICLE);
        vehicleMap.put(vehicle1.getNumberPlate(), vehicle1);
        vehicleMap.put(vehicle2.getNumberPlate(), vehicle2);
        vehicleMap.put(vehicle3.getNumberPlate(), vehicle3);
    }

    public Vehicle getVehicleByVehicleNumber(String vehicleNumber, VehicleType vehicleType) {
        Vehicle vehicle = vehicleMap.get(vehicleNumber);
        if(vehicle == null) {
            vehicle = new Vehicle();
            vehicle.setNumberPlate(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicleMap.put(vehicleNumber, vehicle);
        }
        return vehicle;
    }
}
