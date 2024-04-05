package com.bankai.jukebox.views.friends;

import com.bankai.jukebox.Main;
import com.bankai.jukebox.models.Song;
import com.bankai.jukebox.models.User;
import com.bankai.jukebox.pages.HomePage;
import com.bankai.jukebox.utils.database.DatabaseConnection;
import com.bankai.jukebox.utils.database.DatabaseHandler;
import com.bankai.jukebox.utils.database.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FriendsActivityPanelsManager extends JPanel {
    private final ArrayList<FriendsActivityPanel> friendsActivityPanels  = new ArrayList<>();
    private JPanel friendActivityPanel = new JPanel();
    private JLabel friendActivityLabel = new JLabel("    FRIEND ACTIVITY    ");
    private JButton findFriendsButton = new JButton("FIND FRIENDS");

    public FriendsActivityPanelsManager() {
//        findFriendsButton.setSize(250,50);

        findFriendsButton.setMaximumSize(new Dimension(130,30));
        findFriendsButton.setMinimumSize(new Dimension(130,30));
        findFriendsButton.setPreferredSize(new Dimension(130,30));
//        findFriendsButton.setBackground(new Color(22,22,22));
        friendActivityPanel.setLayout(new BorderLayout());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
//        this.setBackground(new Color(22,22,22));
        friendActivityLabel.setFont(new Font("Arial", Font.BOLD, 12));

        friendActivityPanel.add(friendActivityLabel,BorderLayout.CENTER);
        friendActivityPanel.setPreferredSize(new Dimension(250,10));
        friendActivityPanel.setMinimumSize(new Dimension(250,10));
        friendActivityPanel.setMaximumSize(new Dimension(250,50));
//        friendActivityPanel.setBackground(new Color(22,22,22));
        this.add(friendActivityPanel);
        add(Box.createRigidArea(new Dimension(0,20)));
        findFriendsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        findFriendsButton.setForeground(Color.WHITE);
        findFriendsButton.addActionListener(actionEvent -> {
            FindFriendPanel findFriendPanel = new FindFriendPanel(friend -> {
                String friends = Main.user.getFriends();
                if (!friends.contains(friend.getUsername())){
                    friends = friends.concat(friend.getUsername()).concat(Song.HASH_SEPERATOR);
                    Main.user.addFriend(friend);
                    HomePage.databaseHandler.removeUser(Main.user.getUsername());
                    Main.databaseHandler.addUser(Main.user);
                    addFriendToPanel(friend);
                }
            });
        });
        this.add(findFriendsButton);
        add(Box.createRigidArea(new Dimension(0,10)));
    }

    public void addFriendToPanel(User user){
        FriendsActivityPanel friendsActivityPanel = new FriendsActivityPanel(user);
        friendsActivityPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProfileFrame profileFrame = new ProfileFrame(user);
            }
        });
        friendsActivityPanels.add(friendsActivityPanel);
        this.add(friendsActivityPanel);
        validate();
        repaint();
    }

    public void clearFriendsPanel(){

        for(FriendsActivityPanel x: friendsActivityPanels) {
            this.remove(x);
        }
        friendsActivityPanels.clear();
        validate();

    }

    /**
     * travles through database to find user's friend's and give them to the manager
     */
    public void showFriends(){
        for(FriendsActivityPanel x: friendsActivityPanels){
            this.add(x);
            add(Box.createRigidArea(new Dimension(0,0)));
        }
        validate();
        repaint();
    }

    public void updateFriendsList(){
        ArrayList<User> currentFriends = new ArrayList<>();
        ArrayList<User> tmp;
        clearFriendsPanel();
        for (String username : Main.user.getFriends().split(Song.HASH_SEPERATOR)) {
            if (username.isEmpty())
                break;
            DatabaseConnection userC = new DatabaseConnection(username);
            // set a handler to read a special user's songs
            DatabaseHandler handler = new DatabaseHelper(userC.getConnection());
            tmp = Main.databaseHandler.getUserByUsername(username, handler);
            currentFriends.add(tmp.getFirst());
            Main.user.addFriend(tmp.getFirst());
            addFriendToPanel(tmp.getFirst());
            userC.close();
            handler.close();
        }
    }

    public void updateFriendsList(User friend){

        for(FriendsActivityPanel x: friendsActivityPanels) {
            if (x.getUser().equals(friend)){
                this.remove(x);
            }
        }
        addFriendToPanel(friend);
        if (friend.getCurrentSong() != null) {
            System.out.println(friend.getCurrentSong().getTitle());
        }
    }

    private final int getComponentIndex(Component component) {
        if (component != null && component.getParent() != null) {
            Container c = component.getParent();
            for (int i = 0; i < c.getComponentCount(); i++) {
                if (c.getComponent(i) == component)
                    return i;
            }
        }

        return -1;
    }

    public interface AddFriendListener {
        void addFriend(User friend);
    }
}
