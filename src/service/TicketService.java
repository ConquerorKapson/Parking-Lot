package service;

import Interfaces.SlotAssignmentStrategy;
import Interfaces.implementations.slotAssignment.RandomSlotAssignmentStrategyImpl;
import dto.requestDto.TicketRequestDto;
import dto.responseDto.TicketResponseDto;
import enums.SlotStrategyEnums;
import enums.TicketGenerationsStatus;
import enums.VehicleType;
import exception.GateNotFoundException;
import exception.InvalidVehicleType;
import exception.ParkingSlotNotFoundException;
import factory.SlotStrategyFactory;
import models.*;
import repository.*;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class TicketService {

    private VehicleRepository vehicleRepository;
    private GateRepository gateRepository;
    private ParkingLotRepository parkingLotRepository;
    private OperatorRepository operatorRepository;
    private TicketRepository ticketRepository;
    private SlotAssignmentStrategy slotAssignmentStrategy;
    private SlotStrategyFactory slotStrategyFactory;
    public TicketService(VehicleRepository vehicleRepository,
                  GateRepository gateRepository,
                  ParkingLotRepository parkingLotRepository,
                  OperatorRepository operatorRepository,
                  TicketRepository ticketRepository,
                  SlotStrategyFactory slotStrategyFactory) {
        this.vehicleRepository = vehicleRepository;
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.operatorRepository = operatorRepository;
        this.ticketRepository = ticketRepository;
        this.slotStrategyFactory = slotStrategyFactory;
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
        } catch (ParkingSlotNotFoundException | InvalidVehicleType | GateNotFoundException e) {
            ticketResponseDto.setFailureReason(e.getMessage());
        } catch (Exception e) {
            ticketResponseDto.setFailureReason("TICKET GENERATION FAILED");
        }
        return ticketResponseDto;
    }

    private void buildTicket(Ticket ticket, TicketRequestDto ticketRequestDto) throws GateNotFoundException, InvalidVehicleType {
        VehicleType vehicleType = VehicleType.fromValue(ticketRequestDto.getVehicleType());
        if(vehicleType == null) {
            throw new InvalidVehicleType("INVALID VEHICLE ENTERED");
        }

        Vehicle vehicle = vehicleRepository.getVehicleByVehicleNumber(ticketRequestDto.getNumberPlate(), vehicleType);
        Optional<Gate> gate = gateRepository.getGateByGateId(ticketRequestDto.getGateId());
        if(gate.isEmpty()) {
            throw new GateNotFoundException("VALID GATE ENTRY NOT FOUND");
        }
        slotAssignmentStrategy = slotStrategyFactory.getSlotAssignmentStrategy(SlotStrategyEnums.RANDOM.name());
        ParkingSlot parkingSlot = slotAssignmentStrategy.getParkingSlot(ticketRequestDto.getParkingLotId(), vehicleType);
        Operator operator = operatorRepository.getOperatorByOperatorId(ticketRequestDto.getOperatorId());
        ticket.setTicketNumber(String.valueOf(ticket.getId()));
        ticket.setEntryTime(Date.from(Instant.now()));
        ticket.setVehicle(vehicle);
        ticket.setParkingSlot(parkingSlot);
        ticket.setGate(gate.get());
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
