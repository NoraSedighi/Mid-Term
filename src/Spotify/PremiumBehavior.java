package Spotify;

public class PremiumBehavior implements UserBehavior {
    private int month;

    public PremiumBehavior(int month){
        this.month = month;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        PlayList playList = new PlayList(title, owner);
        owner.getPlaylists().add(playList);
    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) {
        this.month += month;
        System.out.println("Premium extended. Now " + month + " months remaining");
    }

    public int getMonth() {
        return month;
    }
}
