package exception;

public class ParkingSlotNotFoundException extends Exception {
    public ParkingSlotNotFoundException(String reason) {
        super(reason);
    }

    public ParkingSlotNotFoundException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
