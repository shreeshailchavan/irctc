package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService{
    private User user;      //for gloabal level usage
    private List<User> userList;
    private static final String USERS_PATH = "../localdb/users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user) throws IOException {
        this.user = user;
        userList = loadUsers();
    }

    public UserBookingService()throws IOException{
        userList = loadUsers();
    }


    public List<User> loadUsers()throws IOException{
        File users = new File(USERS_PATH);
        return  objectMapper.readValue(users,new TypeReference<List<User>>(){});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(u -> {
            return u.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(),u.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signup(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch(IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile()throws IOException{
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile,userList);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId){
        Optional<Ticket> foundTicket = user.getTicketsBooked().stream().filter(t -> {
            return t.getTicketId().equals(ticketId);}).findFirst();
        if(foundTicket.isEmpty())
            return false;
        foundTicket.ifPresent(ticket -> user.getTicketsBooked().remove(ticket));
        user.setTicketsBooked(user.getTicketsBooked());
        try {
            saveUserListToFile();
            return true;
        }
        catch(IOException ex){
            return false;
        }
    }
}



