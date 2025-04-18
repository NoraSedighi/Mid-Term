package Spotify;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;
    private UserBehavior behavior;
    private ArrayList<PlayList> playlists;
    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) throws InvalidOperationException {
        this.followerList = new ArrayList<>();
        this.followingList = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.behavior = new RegularBehavior();

        setUsername(username);
        setPassword(password);
        allUsers.add(this);
    }

    public void follow (User user) throws InvalidOperationException {
        if (user == this)
            throw new InvalidOperationException("Cannot follow yourself");

        if (followingList.contains(user))
            throw new InvalidOperationException("You are already following this user");

        followingList.add(user);
        user.followerList.add(this);
    }

    public void createPlaylist (String title) throws InvalidOperationException {
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic (Music music) throws InvalidOperationException {
        this.behavior.playMusic(music);
    }

    public void buyPremium (int month){
        this.behavior.buyPremium(this ,month);
    }

    public void setUsername(String username) throws InvalidOperationException {
        if (username.trim().isEmpty())
            throw new InvalidOperationException("Username cannot be empty");

        for(User user : allUsers)
            if (user != this && username.equals(user.getUsername()))
                throw new InvalidOperationException("Username already exists");

        this.username = username;
    }

    public void setPassword(String password) throws InvalidOperationException {
        if(password.length() < 8)
            throw new InvalidOperationException("Password must be at least 8 characters long");
        this.password = password;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
}
