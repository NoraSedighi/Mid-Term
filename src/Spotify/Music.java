package Spotify;

import java.util.ArrayList;
import java.util.List;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer){
        this.title = title;
        this.singer = singer;
        this.numberOfStream = 0;
        allMusics.add(this);
    }

    public void play(){
        System.out.println("Song '" + title + "' by " + singer.getUsername() + " is now playing");
        numberOfStream++;
    }

    public static List<Music> search(String title){
        List<Music> result =  new ArrayList<>();
        for (Music music : allMusics) {
            if (music.title.equals(title))
                result.add(music);
        }

        if (result.isEmpty())
            return null;

        return result;
    }

    public static Music search(String title, User singer){
        for (Music music : allMusics){
            if (music.title.equals(title) && music.singer.equals(singer))
                return music;
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    public static ArrayList<Music> getAllMusics() {
        return allMusics;
    }
}
