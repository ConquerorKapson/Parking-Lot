package service;

import Interfaces.SlotAssignmentStrategy;
import Interfaces.implementations.slotAssignment.RandomSlotAssignmentStrategyImpl;
import dto.requestDto.TicketRequestDto;
import dto.responseDto.TicketResponseDto;
import enums.TicketGenerationsStatus;
import exception.ParkingSlotNotFoundException;
import models.*;
import repository.*;

import java.time.Instant;
import java.util.Date;

public class TicketService {

    private VehicleRepository vehicleRepository;
    private GateRepository gateRepository;
    private ParkingLotRepository parkingLotRepository;
    private OperatorRepository operatorRepository;
    private TicketRepository ticketRepository;
    private SlotAssignmentStrategy slotAssignmentStrategy;
    public TicketService(VehicleRepository vehicleRepository,
                  GateRepository gateRepository,
                  ParkingLotRepository parkingLotRepository,
                  OperatorRepository operatorRepository,
                         TicketRepository ticketRepository) {
        this.vehicleRepository = vehicleRepository;
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.operatorRepository = operatorRepository;
        this.ticketRepository = ticketRepository;
        this.slotAssignmentStrategy = new RandomSlotAssignmentStrategyImpl(parkingLotRepository);
    }

    public TicketResponseDto issueTicket(TicketRequestDto ticketRequestDto) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        Ticket ticket = new Ticket();
        try {
            buildTicket(ticket, ticketRequestDto);
            ticketRepository.save(ticket);
            buildTicketResponseDto(ticketResponseDto, ticket);
            if(TicketGenerationsStatus.FAILED.name().equals(ticketResponseDto.getTicketGenerationStatus())) {
                throw new ParkingSlotNotFoundException(ticketResponseDto.getFailureReason());
            }
        } catch (ParkingSlotNotFoundException parkingSlotNotFoundException) {
            ticketResponseDto.setFailureReason(parkingSlotNotFoundException.getMessage());
        }
        catch (Exception e) {
            ticketResponseDto.setFailureReason("TICKET GENERATION FAILED");
        }
        return ticketResponseDto;
    }

    private void buildTicket(Ticket ticket, TicketRequestDto ticketRequestDto) {
        Vehicle vehicle = vehicleRepository.getVehicleByVehicleNumber(ticketRequestDto.getNumberPlate());
        Gate gate = gateRepository.getGateByGateId(ticketRequestDto.getGateId());
        ParkingSlot parkingSlot = slotAssignmentStrategy.getParkingSlot(ticketRequestDto.getParkingLotId(), vehicle.getVehicleType());
        Operator operator = operatorRepository.getOperatorByOperatorId(ticketRequestDto.getOperatorId());
        ticket.setTicketNumber(String.valueOf(ticket.getId()));
        ticket.setEntryTime(Date.from(Instant.now()));
        ticket.setVehicle(vehicle);
        ticket.setParkingSlot(parkingSlot);
        ticket.setGate(gate);
        ticket.setOperator(operator);
    }

    private void buildTicketResponseDto(TicketResponseDto ticketResponseDto, Ticket ticket) {
        ticketResponseDto.setTicketNumber(ticket.getTicketNumber());
        ticketResponseDto.setEntryTime(ticket.getEntryTime());
        ticketResponseDto.setVehicleNumber(ticket.getVehicle().getNumberPlate());
        ticketResponseDto.setParkingSlotAssigned(String.valueOf(ticket.getParkingSlot().getId()));
        ticketResponseDto.setGateEntry(String.valueOf(ticket.getGate().getGateNumber()));
        ticketResponseDto.setTicketGeneratedBy(ticket.getOperator().getName());
        if(ticket.getParkingSlot() == null) {
            ticketResponseDto.setTicketGenerationStatus(TicketGenerationsStatus.FAILED.name());
        } else {
            ticketResponseDto.setTicketGenerationStatus(TicketGenerationsStatus.SUCCESSFUL.name());
        }
    }

}
