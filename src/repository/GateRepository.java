package repository;

import enums.GateStatus;
import enums.GateType;
import models.Gate;
import models.Operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {

    private final Map<Integer, Gate> gateRepo = new HashMap<>();

    public GateRepository() {
        initialise();
    }

    private void initialise() {
        Gate gate = new Gate();
        gate.setId(1);
        gate.setGateNumber(1);
        gate.setGateStatus(GateStatus.OPEN);
        gate.setOperator(new Operator());
        gate.setGateType(GateType.ENTRY);

        Gate gate2 = new Gate();
        gate.setId(2);
        gate.setGateNumber(2);
        gate.setGateStatus(GateStatus.OPEN);
        gate.setOperator(new Operator());
        gate.setGateType(GateType.EXIT);

        gateRepo.put(1, gate);
        gateRepo.put(2, gate2);
    }

    public Optional<Gate> getGateByGateId(int gateId) {
        return Optional.ofNullable(gateRepo.get(gateId));
    }
}
