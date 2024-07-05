package Interfaces.implementations.slotAssignment;

import Interfaces.SlotAssignmentStrategy;
import enums.FloorStatus;
import enums.ParkingLotStatus;
import enums.VehicleType;
import models.Floor;
import models.ParkingLot;
import models.ParkingSlot;
import repository.ParkingLotRepository;

import java.util.Map;

public class RandomSlotAssignmentStrategyImpl implements SlotAssignmentStrategy {

    private ParkingLotRepository parkingLotRepository;
    public RandomSlotAssignmentStrategyImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingSlot getParkingSlot(int parkingLotId, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);

        if(parkingLot.getParkingLotStatus().equals(ParkingLotStatus.OPEN)) {
            Map<Integer, Map<VehicleType, Integer>> slotsCountMap = parkingLot.getSlotsCountMap();
            for(Floor f : parkingLot.getFloorList()) {
                if(FloorStatus.UNFILLED.equals(f.getFloorStatus())) {
                    for(ParkingSlot ps : f.getParkingSlotList()) {
                        int count = slotsCountMap.get(f.getId()).get(vehicleType);
                        if(count > 0) {
                            slotsCountMap.get(f.getId()).put(vehicleType, count-1);
                            return ps;
                        }
                    }
                }
            }
        }
        return null;
    }
}
