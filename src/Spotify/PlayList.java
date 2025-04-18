package Spotify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayList {
    private String title;
    private ArrayList<Music> playlist;
    private User owner;

    public PlayList(String title, User owner){
        this.title = title;
        this.playlist = new ArrayList<>();
        this.owner = owner;
        owner.getPlaylists().add(this);
    }

    public void editTitle(String newTitle, String password) throws InvalidOperationException{
        if ( !owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password");

        this.title = newTitle;
    }

    public void addMusic(Music music, String password) throws InvalidOperationException{
        if ( !owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password");

        if (playlist.contains(music))
            throw new InvalidOperationException("Music already exists in playlist");

        playlist.add(music);
    }

    public void removeMusic(Music music, String password) throws InvalidOperationException{
        if ( !owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password");

        if (!playlist.contains(music))
            throw new InvalidOperationException("Music not found in playlist");

        playlist.remove(music);
    }

    public List<Music> searchInPlaylist(String searchTerm){
        List<Music> results = new ArrayList<>();
        for (Music music : playlist){
            if (music.getTitle().contains(searchTerm) || music.getSinger().getUsername().contains(searchTerm)){
                results.add(music);
            }
        }

        if (results.isEmpty())
            return null;

        return results;
    }

    public void playPlaylist(){
        for (Music music : playlist)
            music.play();
    }

    public void shufflePlaylist(){
        ArrayList<Music> shuffled = new ArrayList<>(playlist);
        Collections.shuffle(shuffled);

        for (Music music : shuffled)
            music.play();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Music> getPlaylist() {
        return playlist;
    }

    public User getOwner() {
        return owner;
    }
}
