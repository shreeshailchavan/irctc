package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService{
    private User user;
    private List<User> userList;
    private static final String USERS_PATH = "../localdb/users.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users,new TypeReference<List<User>>(){});
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
            saveUserToFile();
            return Boolean.TRUE;
        }catch(IOException){
            return Boolean.FALSE;
        }
    }
}

