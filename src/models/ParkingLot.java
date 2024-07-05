package models;

import Interfaces.FeeCalculationStrategy;
import Interfaces.SlotAssignmentStrategy;
import enums.ParkingLotStatus;
import enums.VehicleType;

import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int id;
    private String location;
    private List<Floor> floorList;
    private List<VehicleType> supportedVehicleTypeList;
    private List<FeeCalculationStrategy> feeCalculationStrategyList;
    private List<SlotAssignmentStrategy> slotAssignmentStrategyList;
    private ParkingLotStatus parkingLotStatus;
    private Gate gate;
    private Map<Integer, Map<VehicleType, Integer>> slotsCountMap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    public List<VehicleType> getSupportedVehicleTypeList() {
        return supportedVehicleTypeList;
    }

    public void setSupportedVehicleTypeList(List<VehicleType> supportedVehicleTypeList) {
        this.supportedVehicleTypeList = supportedVehicleTypeList;
    }

    public List<FeeCalculationStrategy> getFeeCalculationStrategyList() {
        return feeCalculationStrategyList;
    }

    public void setFeeCalculationStrategyList(List<FeeCalculationStrategy> feeCalculationStrategyList) {
        this.feeCalculationStrategyList = feeCalculationStrategyList;
    }

    public List<SlotAssignmentStrategy> getSlotAssignmentStrategyList() {
        return slotAssignmentStrategyList;
    }

    public void setSlotAssignmentStrategyList(List<SlotAssignmentStrategy> slotAssignmentStrategyList) {
        this.slotAssignmentStrategyList = slotAssignmentStrategyList;
    }

    public ParkingLotStatus getParkingLotStatus() {
        return parkingLotStatus;
    }

    public void setParkingLotStatus(ParkingLotStatus parkingLotStatus) {
        this.parkingLotStatus = parkingLotStatus;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Map<Integer, Map<VehicleType, Integer>> getSlotsCountMap() {
        return slotsCountMap;
    }

    public void setSlotsCountMap(Map<Integer, Map<VehicleType, Integer>> slotsCountMap) {
        this.slotsCountMap = slotsCountMap;
    }


}
