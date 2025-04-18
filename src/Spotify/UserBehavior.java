package Spotify;

public interface UserBehavior {
    void createPlaylist (String title, User owner) throws InvalidOperationException;
    void playMusic (Music music) throws InvalidOperationException;
    void buyPremium (User owner,int month);
}
