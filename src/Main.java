import controller.TicketController;
import dto.responseDto.TicketResponseDto;
import repository.*;
import service.TicketService;

public class Main {
    public static void main(String[] args) {
        VehicleRepository vehicleRepository = new VehicleRepository();
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        OperatorRepository operatorRepository = new OperatorRepository();
        TicketRepository ticketRepository = new TicketRepository();


        TicketService ticketService = new TicketService(vehicleRepository,
                gateRepository, parkingLotRepository, operatorRepository, ticketRepository);
        TicketController ticketController = new TicketController(ticketService);
        TicketResponseDto ticketResponseDto = ticketController.issueTicket(1,1,"abcd-1234");
        System.out.println("Ticket Response DTO: " + ticketResponseDto.toString());
    }
}