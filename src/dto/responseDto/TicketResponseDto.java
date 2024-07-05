package dto.responseDto;

import java.util.Date;

public class TicketResponseDto {
    private static int nextId = 1;
    private int id;
    private String ticketNumber;
    private Date entryTime;
    private String vehicleNumber;
    private String parkingSlotAssigned;
    private String gateEntry;
    private String ticketGeneratedBy;
    private String TicketGenerationStatus;
    private String failureReason = null;

    public TicketResponseDto() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getParkingSlotAssigned() {
        return parkingSlotAssigned;
    }

    public void setParkingSlotAssigned(String parkingSlotAssigned) {
        this.parkingSlotAssigned = parkingSlotAssigned;
    }

    public String getGateEntry() {
        return gateEntry;
    }

    public void setGateEntry(String gateEntry) {
        this.gateEntry = gateEntry;
    }

    public String getTicketGeneratedBy() {
        return ticketGeneratedBy;
    }

    public void setTicketGeneratedBy(String ticketGeneratedBy) {
        this.ticketGeneratedBy = ticketGeneratedBy;
    }

    public String getTicketGenerationStatus() {
        return TicketGenerationStatus;
    }

    public void setTicketGenerationStatus(String ticketGenerationStatus) {
        TicketGenerationStatus = ticketGenerationStatus;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
