package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * PLayerLobby class is the application tier class that keeps
 * track of all of the users that are connected to the site.
 * Whether they are connected to a game already or not, the use
 * of the PlayerLobby class gives you the ability to see who's on
 * the site. Also, when a user signs in, he/she is run through a check
 * as to whether or not that username is already in the lobby. If not,
 * that user is added to the collection users.
 *
 * @author Dan Wang
 * @author Emily Lederman
 * @author Kevin Paradis
 * @author Nathan Farrell
 */
public class PlayerLobby {
    //Results for checking a valid username
    public enum InputResult {ACCEPTED, INVALID, EMPTY, TAKEN}

    private static final Logger LOG = Logger.getLogger(PlayerLobby.class.getName());

    //Map which holds all Player Objects
    private Map<String, Player> playerLobby = new HashMap<>();
    private int numOfUsers = 0;


    /**
     * When the user hits the signin button, that user is run through a check
     * as to whether or not he/she's username is in the lobby/is valid input/Empty,
     * if not then tha user's Player Instance is created and added to the lobby.
     *
     * @param username Desired username for the new user
     * @return boolean as to whether sign in was successful
     */
    public InputResult signIn(String username){
        InputResult result = InputResult.INVALID;
        if(!username.isEmpty()){ //Checks that username is not empty
            if(!isValidUsername(username)){ //Checks if the username is valid input
                LOG.fine(username+" is invalid! Cannot use \"s in a username.");
                return result;
            }
            else {
                if(!nameTaken(username)){ // Checks if username is in lobby
                    Player newPlayer = new Player(username);

                    this.playerLobby.put(username, newPlayer);
                    this.numOfUsers++;
                    LOG.fine(username + " was added to the lobby.");

                    this.playerLobby.put(username, newPlayer);
                    this.numOfUsers++;


                    result = InputResult.ACCEPTED;
                }
                else{ //Username has already been taken
                    LOG.fine(username+" already in lobby!");
                    result = InputResult.TAKEN;
                }
            }
        }
        else{ //Username was left blank
            result = InputResult.EMPTY;
        }
        return result;

    }

    /**
     * Removes user from the player lobby.
     *
     * @param username name of user to remove
     */
    public void signOut(String username){
        this.playerLobby.remove(username);
        this.numOfUsers--;
    }

    /**
     * Gets the number of users in the lobby.
     *
     * @return number of Players in lobby
     */
    public int getNumOfUsers(){
        return this.numOfUsers;
    }

    /**
     * Returns the Map that represents the lobby of players.
     *
     * @return playerLobby
     */
    public Map<String, Player> getPlayerLobby(){
        return this.playerLobby;
    }

    /**
     * Checks whether or not the user is in the lobby.
     *
     * @param username username to check.
     * @return boolean of whether or not username is in the lobby.
     */
    private boolean nameTaken(String username){
        Set<String> usernames = this.playerLobby.keySet();
        return usernames.contains(username);
    }

    /**
     * Checks to see if username is valid. Valid usernames do not contain
     * double quotation marks.
     *
     * @param username name to verify
     * @return boolean whether the username is accepted by the webapp
     */
    private boolean isValidUsername(String username){
        if (username.contains("\"")) {
            return false;
        }
        else {
            return true;
        }
    }

}
