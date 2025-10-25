package dataaccess;
import exception.ResponseException
public interface GameDAO {
    void clearAllGames();
    HashMap<Integer, GameModel> getGameData();
    int createGame(String gameName);
    HashMap<Integer, GameModel> listGames();
    void joinGame(String username, String playerColor, Integer gameID) throws ResponseException;

}
