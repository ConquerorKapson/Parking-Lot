import Interfaces.SlotAssignmentStrategy;
import controller.TicketController;
import dto.responseDto.TicketResponseDto;
import factory.SlotStrategyFactory;
import repository.*;
import service.TicketService;

public class Main {
    public static void main(String[] args) {
        VehicleRepository vehicleRepository = new VehicleRepository();
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        OperatorRepository operatorRepository = new OperatorRepository();
        TicketRepository ticketRepository = new TicketRepository();
        SlotStrategyFactory slotStrategyFactory = new SlotStrategyFactory(parkingLotRepository);

        TicketService ticketService = new TicketService(vehicleRepository,
                gateRepository,
                parkingLotRepository,
                operatorRepository,
                ticketRepository,
                slotStrategyFactory);
        TicketController ticketController = new TicketController(ticketService);
        TicketResponseDto ticketResponseDto = ticketController.issueTicket(1,1,"ABCD-1234", "CAR");
        System.out.println("Ticket Response DTO: " + ticketResponseDto.toString());
    }
}