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


    public TicketResponseDto issueTicket(int gateId, int operatorId, String numberPlate) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        TicketRequestDto ticketRequestDto = getTicketRequestDto(gateId, operatorId, numberPlate);
        return ticketService.issueTicket(ticketRequestDto);
    }

    private TicketRequestDto getTicketRequestDto(int gateId, int operatorId, String numberPlate) {
        TicketRequestDto ticketRequestDto = new TicketRequestDto();
        ticketRequestDto.setGateId(gateId);
        ticketRequestDto.setOperatorId(operatorId);
        ticketRequestDto.setNumberPlate(numberPlate);
        return ticketRequestDto;
    }


}