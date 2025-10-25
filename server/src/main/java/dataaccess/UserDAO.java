package dataaccess;
import exception.ResponseException;
import model.UserMoodel;
import java.util.HashMap;

public interface UserDAO {
    void createUser(String username, String password, String emial) throws ResponseException;
    void clearAllUsers();
    UserModel getUser(String username);
    HashMap<String, UserModel> getUserData();
}
