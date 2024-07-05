package controller;

import dto.requestDto.TicketRequestDto;
import dto.responseDto.TicketResponseDto;
import models.Ticket;
import service.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    public TicketResponseDto issueTicket(int gateId, int operatorId, String numberPlate, String vehicleType) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        TicketRequestDto ticketRequestDto = getTicketRequestDto(gateId, operatorId, numberPlate, vehicleType);
        return ticketService.issueTicket(ticketRequestDto);
    }

    private TicketRequestDto getTicketRequestDto(int gateId, int operatorId, String numberPlate, String vehicleType) {
        TicketRequestDto ticketRequestDto = new TicketRequestDto();
        ticketRequestDto.setGateId(gateId);
        ticketRequestDto.setOperatorId(operatorId);
        ticketRequestDto.setNumberPlate(numberPlate);
        ticketRequestDto.setVehicleType(vehicleType);
        return ticketRequestDto;
    }


}