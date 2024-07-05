package repository;

import enums.GateStatus;
import enums.GateType;
import enums.VehicleType;
import models.Gate;
import models.Operator;
import models.Vehicle;

public class GateRepository {
    public Gate getGateByGateId(int gateId) {
        Gate gate = new Gate();
        gate.setId(gateId);
        gate.setGateNumber(2);
        gate.setGateStatus(GateStatus.OPEN);
        gate.setOperator(new Operator());
        gate.setGateType(GateType.ENTRY);
        return gate;
    }
}
