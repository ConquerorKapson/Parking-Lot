package Interfaces;

import enums.VehicleType;
import models.ParkingSlot;

public interface SlotAssignmentStrategy {
    public ParkingSlot getParkingSlot(int parkingLotId, VehicleType vehicleType);
}
