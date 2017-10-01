package old80house.map;

import java.io.File;

public class Room {

    private File soundName1;            //first and second sound to be played upon entering a room. dependant on firsttime boolean
    private File soundName2;
    private boolean ghost, secret = true;
    private String roomstory;           //same as soundname
    private String roomstory2;
    private String roomSecret;          //special txt for room if player has flashlight
    private String rn;                  //roomname

    private boolean hm; //hasmonster
    private int gooey;                  //will be moved to items
    private String item1;               //items to be put in rooms. Max items are 2
    private String item2;
    private String itemSecret;          //special item found if player has flashlight
    private boolean firsttime = true;   //first time player enters the room or has he been here before

    // booleans for where a room has doors, and wether they are locked
    private boolean north, nLock;
    private boolean south, sLock;
    private boolean west, wLock;
    private boolean east, eLock;
    private boolean up;
    private boolean down;

    // Constructor
    Room(String x, boolean ghost, String item1, String item2, String itemSecret, int gooey, boolean n, boolean nLock, boolean s, boolean sLock, boolean w, boolean wLock, boolean e, boolean eLock, boolean up, boolean down, File soundName1, File soundName2, String story, String story2, String roomSecret) {
        rn = x;
        this.gooey = gooey;
        north = n;
        this.nLock = nLock;
        south = s;
        this.sLock = sLock;
        west = w;
        this.wLock = wLock;
        east = e;
        this.eLock = eLock;
        this.ghost = ghost;
        roomstory = story;
        roomstory2 = story2;
        this.up = up;
        this.down = down;
        this.soundName1 = soundName1;
        this.soundName2 = soundName2;
        this.item1 = item1;
        this.item2 = item2;
        this.itemSecret = itemSecret;
        this.roomSecret = roomSecret;

    }

    public String returnRoomName() {
        return rn;
    }

    public void roomPlay() {
        if (firsttime) {
            System.out.println(roomstory);

        } else {
            System.out.println(roomstory2);
        }
    }

    public void setFirstTime() {
        firsttime = false;
    }

    public boolean hasDoor(String a) {
        switch (a) {
            case "n":
                return north;
            case "s":
                return south;
            case "e":
                return east;
            case "w":
                return west;
            case "u":
                return up;
            case "d":
                return down;
        }
        return false;
    }

    public boolean hasGhost() {
        return ghost;
    }

    public File getSound() {
        if (firsttime) {
            return soundName1;
        }
        return soundName2;
    }

    public int getGooey() {
        int x = gooey;
        gooey = 0;
        return x;
    }

    public boolean firstTime() {
        return firsttime;
    }

    public String getItem1() {

        return item1;
    }

    public String getItem2() {

        return item2;
    }

    public boolean isLock(String a) {
        switch (a) {
            case "n":
                return nLock;

            case "s":
                return sLock;

            case "w":
                return wLock;

            case "e":
                return eLock;
            default:
                return false;

        }
    }

    public String getRoomSecret() {
        if (secret) {
            return roomSecret;

        } else {
            return "You find nothing else";
        }

    }

    public String getRoomSecretItem() {
        if (secret) {
            return itemSecret;
        } else {
            return null;
        }
    }

    public void roomUnlock() {
        nLock = false;
    }

    public void setSecret() {
        secret = false;
    }

    public boolean getSecret() {                 //this is used from the save
        return secret;
    }

    public void setSecret(boolean a) {           //this is used from the load
        secret = a;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public void setItemSecret(String itemSecret) {
        this.itemSecret = itemSecret;
    }

    public void setGooey(int gooey) {
        this.gooey = gooey;
    }

}
