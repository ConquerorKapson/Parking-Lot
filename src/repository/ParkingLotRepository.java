package repository;

import enums.FloorStatus;
import enums.ParkingLotStatus;
import enums.SlotStatus;
import enums.VehicleType;
import models.Floor;
import models.Gate;
import models.ParkingLot;
import models.ParkingSlot;

import java.util.*;

public class ParkingLotRepository {
    private final Map<VehicleType, Integer>vehicleSlot = new HashMap<>();
    private final Map<Integer, ParkingLot> parkingLotMap = new HashMap<>();

    public ParkingLotRepository() {
        initializeData();
    }
    private void initializeData() {
        Map<Integer, Map<VehicleType, Integer>> slotsCountMap = new HashMap<>();
        Map<VehicleType, Integer> vehicleSlot = new HashMap<>();
        vehicleSlot.put(VehicleType.BIKE, 20);
        vehicleSlot.put(VehicleType.CAR, 20);
        slotsCountMap.put(1, vehicleSlot);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1); // Example parking lot ID
        parkingLot.setParkingLotStatus(ParkingLotStatus.OPEN);
        parkingLot.setGate(new Gate());

        List<Floor> floorList = new ArrayList<>();
        Floor f1 = new Floor();
        f1.setId(1);
        f1.setFloorNumber(1);
        f1.setFloorStatus(FloorStatus.UNFILLED);

        ParkingSlot ps1 = new ParkingSlot();
        ps1.setId(1);
        ps1.setFloor(f1);
        ps1.setParkingSlotStatus(SlotStatus.UNOCCUPIED);

        List<ParkingSlot> parkingSlotList = new ArrayList<>();
        parkingSlotList.add(ps1);
        f1.setParkingSlotList(parkingSlotList);
        floorList.add(f1);

        parkingLot.setFloorList(floorList);

        parkingLot.setSupportedVehicleTypeList(Arrays.asList(VehicleType.BIKE, VehicleType.CAR));

        parkingLot.setSlotsCountMap(slotsCountMap);
        parkingLotMap.put(1, parkingLot);
    }

    public ParkingLot getParkingLotById(int parkingLotId) {
        return parkingLotMap.get(1);
    }
}
