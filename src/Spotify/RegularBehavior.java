package Spotify;

public class RegularBehavior implements UserBehavior{
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String Title, User Owner) throws InvalidOperationException {
        throw new InvalidOperationException("Regular users are not able to create playlist!");
    }

    @Override
    public void playMusic(Music music) throws InvalidOperationException {
        if(playingLimit <= 0)
            throw new InvalidOperationException("Play limit exceeded. Please upgrade to premium");
        music.play();
        playingLimit--;
    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior(new PremiumBehavior(month));
        System.out.println(owner.getUsername() + " is now a premium user for " + month + " months");
    }

    public int getPlayingLimit() {
        return playingLimit;
    }

    public void resetPlayingLimit(){
        this.playingLimit = 5;
    }
}
