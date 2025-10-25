package dataaccess;
import exception.ResponseException;
import model.AuthModel;
import java.util.ArrayList;
import java util.HashMap;

public interface AuthDAO{
    void clearAllAuth();
    String deleteAuthToken(String authToken) throws ResponseException;
    void mapAuthToken(String username, AuthModel authModel);
    String getUsernameOfAuthToken(String authToken) throws ResponseException;
    HashMap<String, ArrayList<AuthModel>> getAuthData();
}