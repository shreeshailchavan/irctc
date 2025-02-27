package ticket.booking.entities;

import java.util.Date;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private Train train;


    public String getTicketInfo(){
        return String.format("Ticked Id : %s belongs to User : %s from %s to %s on %s",this.ticketId,this.userId,this.source,this.destination,this.dateOfTravel);
    }

    public String getTicketId(){
        return this.ticketId;
    }
}
