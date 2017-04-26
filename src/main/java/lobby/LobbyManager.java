package lobby;

import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In charge of managing Lobbies, the staging area for Games.
 * @author ryan
 *
 */
public class LobbyManager {
  private static Map<String, Lobby> lobbies = new ConcurrentHashMap<>();

  /**
   * Creates a new Lobby.
   */
  public static Lobby addLobby(String name, boolean priv, String password,
      int hostUId) throws IllegalArgumentException {
    if (lobbies.containsKey("name")) {
      throw new IllegalArgumentException("Lobby already exists");
    } else if (priv && password.length() < 1) {
      throw new IllegalArgumentException("Password must be non-empty");
    } else {
      Lobby lobby = lobbies.put(name, new Lobby(name, priv, password, hostUId));
      return lobby;
    }
  }

  /**
   * Cancels an existing Lobby.
   * @param name the name of the lobby to cancel.
   */
  public static void cancelLobby(String name) {
    lobbies.remove(name);
  }

  public static Collection<Lobby> getAllLobbies() {
    return lobbies.values();
  }

  /**
   * Check whether a player is in a Lobby.
   * @param playerId Id of player to check.
   * @return True if player of id is in a lobby, false otherwise.
   */
  public static boolean playerIsInLobby(int playerId) {
    for (Lobby l : lobbies.values()) {
      if (l.containsPlayer(playerId)) {
        return true;
      }
    }
    return false;
  }

  public static void playerJoinLobby(int playerId, String lobbyName,
      String password)
      throws IllegalArgumentException {
    try {
      lobbies.get(lobbyName).join(playerId, password);
    } catch (IllegalArgumentException x) {
      throw x;
    }

  }

  /**
   * Get a Lobby from a player's id.
   * @param playerId Id of player to get lobby of.
   * @return The Lobby the player is in (null if not in lobby).
   */
  public static Lobby getLobbyByPlayerId(int playerId) {
    for (Lobby l : lobbies.values()) {
      if (l.containsPlayer(playerId)) {
        return l;
      }
    }
    return null;
  }

  /**
   * Get a Lobby by its name.
   * @param lobbyName the name to get the lobby of.
   * @return The lobby represented by the name (null if doesn't exist).
   */
  public static Lobby getLobbyByName(String lobbyName) {
    if (lobbies.containsKey(lobbyName)) {
      return lobbies.get(lobbyName);
    } else {
      return null;
    }
  }

  /**
   * Handle websocket message of type ENTER_LOBBY_REQUEST.
   * @param uId userId of person joining lobby.
   * @param message Message that was sent.
   */
  public static void handleEnterLobbyRequest(int uId, JsonObject message) {

  }

  /**
   * Handle websocket message of type LOBBY_ACTION.
   * @param uId userId of person taking action.
   * @param message Message that was sent.
   */
  public static void handleLobbyAction(int uId, JsonObject message) {

  }

}
