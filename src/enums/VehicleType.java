package enums;

import java.util.HashMap;
import java.util.Map;

public enum VehicleType {
    CAR("CAR"),
    BIKE("BIKE"),
    SPECIAL_VEHICLE("SPECIAL_VEHICLE");

    String value;

    VehicleType(String value) {
        this.value = value;
    }

    public static final Map<String, VehicleType> vehicleEnumMap = new HashMap<>();

    static {
        for(VehicleType vehicleType : VehicleType.values()) {
            vehicleEnumMap.put(vehicleType.value, vehicleType);
        }
    }

    public static VehicleType fromValue(String value) {
        return vehicleEnumMap.get(value);
    }


}
