import Spotify.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            testUserCreation();

            testMusicFeatures();

            testFollowSystem();

            testRegularUserBehavior();

            testPremiumFeatures();

            testPlaylistOperations();

            System.out.println("\n---- All tests completed successfully ----");

        } catch (Exception e) {
            System.out.println("\nTest failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testUserCreation() throws InvalidOperationException {
        System.out.println("\n---- Test 1: User Creation ----");

        User user1 = new User("john_doe", "password123");
        User user2 = new User("jane_smith", "securepass123");
        User artist1 = new User("The Weeknd", "blindinglights");
        System.out.println("Created valid users: john_doe, jane_smith, The Weeknd");

        testInvalidCreation("", "emptyname");
        testInvalidCreation("short", "pass");
        testInvalidCreation("john_doe", "newpass");
        testInvalidCreation("david", "");
    }

    private static void testInvalidCreation(String username, String password) {
        try {
            new User(username, password);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testMusicFeatures() throws InvalidOperationException {
        System.out.println("\n---- Test 2: Music Features ----");

        User artist = new User("Halsey", "the-great-impersonator");
        Music song1 = new Music("Without Me", artist);
        Music song2 = new Music("Bad At Love", artist);
        Music song3 = new Music("Without Me", new User("Eminem", "thetruerapgod"));

        List<Music> withoutMeSongs = Music.search("Without Me");
        System.out.println("Found " + withoutMeSongs.size() + " songs with title 'Without Me'");

        Music specificSong = Music.search("Bad At Love", artist);
        System.out.println("Found specific song: " + specificSong.getTitle() + " by " + specificSong.getSinger().getUsername());
    }

    private static void testFollowSystem() throws InvalidOperationException {
        System.out.println("\n---- Test 3: Follow System ----");

        User user1 = new User("user1", "password123");
        User user2 = new User("user2", "password123");
        User artist = new User("artist1", "password123");

        user1.follow(artist);
        user2.follow(artist);
        user2.follow(user1);
        System.out.println(artist.getUsername() + " followers: " + artist.getFollowerList().size());
        System.out.println(user1.getUsername() + " following: " + user1.getFollowingList().size());


        testInvalidFollow(user1, user1);
        testInvalidFollow(user1, artist);
    }

    private static void testInvalidFollow(User follower, User toFollow) {
        try {
            follower.follow(toFollow);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testRegularUserBehavior() throws InvalidOperationException {
        System.out.println("\n---- Test 4: Regular User Behavior ----");

        User regularUser = new User("regular", "password123");
        User artist1 = new User("Harry Styles", "justletmeadoreyou");
        User artist2 = new User("Taylor Swift", "imtheproblemitsme");
        Music song1 = new Music("Two Ghosts", artist1);
        Music song2 = new Music("Is it over now?", artist2);
        Music song3 = new Music("Perfect", artist1);
        Music song4 = new Music("Style", artist2);

        try {
            regularUser.createPlaylist("My Playlist");
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nTesting play limit (5 max):");
        try {
            for (int i = 1; i <= 2; i++)
                regularUser.playMusic(song1);
            regularUser.playMusic(song2);
            regularUser.playMusic(song3);
            regularUser.playMusic(song4);
            regularUser.playMusic(song1);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void testPremiumFeatures() throws InvalidOperationException {
        System.out.println("\n---- Test 5: Premium Features ----");

        User premiumUser = new User("blair", "manhattanB");
        premiumUser.buyPremium(12);
        System.out.println("Upgraded user to premium for 12 months");
        premiumUser.setBehavior(premiumUser.getBehavior());

        User artist = new User("Adele", "manhattanB");
        Music song = new Music("Someone Like You", artist);

        premiumUser.createPlaylist("Premium Playlist");
        System.out.println("Premium user created playlist successfully");

        System.out.println("\nTesting unlimited plays:");
        premiumUser.playMusic(song);
        for (int i = 1; i <= 10; i++)
            System.out.println("Played song " + i + " times");
    }

    private static void testPlaylistOperations() throws InvalidOperationException {
        System.out.println("\n---- Test 6: Playlist Operations ----");

        User owner = new User("Nora", "music&me");
        owner.buyPremium(2);

        Music song1 = new Music("Angels Like You", owner);
        Music song2 = new Music("Snuff", owner);
        Music song3 = new Music("All too well", owner);
        Music song4 = new Music("Lovely", owner);
        Music song5 = new Music("Only You", owner);

        owner.createPlaylist("My Playlist");
        PlayList playlist = owner.getPlaylists().get(0);

        playlist.addMusic(song1, "music&me");
        playlist.addMusic(song2, "music&me");
        playlist.addMusic(song3, "music&me");
        playlist.addMusic(song4, "music&me");
        playlist.addMusic(song5, "music&me");
        System.out.println("Added 4 songs to playlist");

        try {
            playlist.addMusic(song1, "music&me");
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            playlist.addMusic(song1, "wrongpass");
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        playlist.removeMusic(song5, "music&me");
        System.out.println("Removed 'Only You' from playlist");

        try {
            playlist.removeMusic(song5, "music&me");
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        playlist.editTitle("Chill Vibes", "music&me");
        System.out.println("Renamed playlist to: " + playlist.getTitle());

        try {
            playlist.editTitle("Wrong Rename", "wrongpass");
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }

        List<Music> results = playlist.searchInPlaylist("Snuff");
        System.out.println("\nSearch results in playlist: " + results.size() + " songs found");

        System.out.println("\nPlaying playlist:");
        playlist.playPlaylist();

        System.out.println("\nShuffle playing playlist:");
        playlist.shufflePlaylist();
    }
}