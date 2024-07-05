package repository;

import models.Operator;

public class OperatorRepository {
    public Operator getOperatorByOperatorId(int id) {
        Operator operator = new Operator();
        operator.setId(id);
        operator.setName("BlackOps");
        operator.setEmpId("BLACK-OPS-II");
        return operator;
    }
}
