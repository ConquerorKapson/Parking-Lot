package factory;

import Interfaces.SlotAssignmentStrategy;
import Interfaces.implementations.slotAssignment.CrowdedSlotAssignmentStrategyImpl;
import Interfaces.implementations.slotAssignment.RandomSlotAssignmentStrategyImpl;
import enums.SlotStrategyEnums;
import repository.ParkingLotRepository;

import java.util.HashMap;
import java.util.Map;

public class SlotStrategyFactory {
    private final ParkingLotRepository parkingLotRepository;
    private final Map<String, SlotAssignmentStrategy> slotStrategiesMap = new HashMap<>();

    public SlotStrategyFactory(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
        initialise();
    }

    private void initialise() {
        slotStrategiesMap.put(SlotStrategyEnums.RANDOM.name(), new RandomSlotAssignmentStrategyImpl(parkingLotRepository));
        slotStrategiesMap.put(SlotStrategyEnums.CROWDED.name(), new CrowdedSlotAssignmentStrategyImpl());
        // Add more strategies as needed
    }

    public SlotAssignmentStrategy getSlotAssignmentStrategy(String strategy) {
        return slotStrategiesMap.get(strategy);
    }
}
