package repository;

import models.Ticket;

public class TicketRepository {
    public Ticket getTicketByTicketId() {
        return new Ticket();
    }

    public void save(Ticket ticket) {
        //save in DB;
    }
}
