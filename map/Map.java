package old80house.map;

import old80house.Player;
import old80house.modules.MediaPlayer;

public class Map {

    MediaPlayer mp;

    private int mapx = 6;
    private int mapy = 7;             //making the size for the 3d array! 
    private int mapz = 3;

    private Room[][][] map = new Room[mapx][mapy][mapz];  //3d array gets created. we want 3d cause we will need an upstairs and a basement

    public Map(MediaPlayer mp) {
        this.mp = mp;
    }

    public void createMap() {
        //heres the framework for how a room is made:
        //new Room ( "Name of the Room"                 ,
        //           boolean if room has a ghost or not ,
        //          String what item to be found in room,
        //          String item 2                       ,
        //           int How much goo can be picked up  ,
        //           {true or false boolean wether a room has a door or stairs, and wheter room is locked} 
        //                    NORTH , nLock,
        //                    SOUTH , sLock,
        //                    WEST  , wLock,
        //                    EAST  , eLock,
        //                    UP    , 
        //                    DOWN  ,
        //          File Sound one when u first enter room             ,
        //          File sound two when its no longer the first time   ,
        //          "Story for the room when you first enter the room" ,
        //          "Story for the room when its not the first time"   )        

        //ground floor rooms
        // Entrance
        Room r = new Room("Entrance", false, "", "", "pills", 0, true, false, true, false, true, false, true, false, false, true, mp.soundHallDoorSlam, mp.empty, "You walk into the dark hallway, closing the front door behind you.\nAs the door closes with a slam, the noise echos through the house followed by a chilling silence. \nMildly nervous you "
                + "now ready to investigate.\nThe hallway has 3 doors facing north and east, with the front door being the southern.\n"
                + "To the west the hallway continues.\nThere is also a stairwell going down, looks dark down there!", "You are back at the front door in the hallway!\nThere is a door to the north and a hallway to the west, and a stairway down to the basement", "With the flashlight you manage to find some pills");   // room 1
        map[3][3][1] = r;                                  // put room in 3d array

        // Bathroom
        r = new Room("Bathroom", false, "syringe", "pills", "pills", 5, false, false, true, false, false, false, false, false, false, false, mp.soundNeonLamp, mp.soundNeonLamp, "Walking out of the dark hallway and into a neat looking bathroom, you flicker on the lights\n"
                + "searching around the room, you find a bottle of pills and a injector under a cupboard.\n"
                + "As you Rise up from the cupboard you look at ur self in the mirror. Suddenly the light starts flickering,\n"
                + "and your mirror image doesnt seem to mirror your movements \n" + "With a shriek you jump away from the mirror and the light stops flickering.\n"
                + "Your mirror image seems to be mirroing you again\n"
                + "This room has only one entrance the way you came to the south\n", "you reenter the bathroom, light is still acting normal, though you decide against looking into the mirror again\nExit is to the south", "With the flashlight you manage to find some pills");
        map[3][2][1] = r;

        //Bedroom
        r = new Room("Bedroom", false, "", "", "pills", 0, false, false, false, false, true, false, false, false, false, false, mp.soundChair, mp.soundChair, "You enter an old bedroom. The bed is an old four poster bed with no sheets or blankets. All the furniture seems to have been thrown around. \n"
                + "In the corner is an old chair facing the door. As you search this room you cant help the feeling of someone watching you from the chair. \n"
                + "The walls are lined with old faded family pictures. Except one picture over the bed, seems to be in perfect condition. Its a picture of an old lady. \n"
                + "She stares off into the distance not really looking at anything. You find a bottle of pills! This room has only one entrance the way you came to the west back into the hallway. \n",
                "As you reenter the bedroom, the painting of the old lady seems to have changed ? \n"
                + "She seems to be looking right at you...\n", "With the flashlight you manage to find some pills");
        map[4][3][1] = r;
        // Main hall

        r = new Room("Mainhall", false, "", "", "bandage", 0, true, true, true, false, true, false, true, false, true, false, mp.soundOldClock, mp.soundOldClock, "You have now entered the main hall. To the right there is a big staircase that leads to the 1st floor. You sense the big wooden clock to the left. \n"
                + "The house seams old and abandoned but still you get the feeling that you are not alone.. \n"
                + "Suddenly you get sense a fast moving object passing your left side! You tremple and nearly falls to the ground. What was that! \n"
                + "It happened so fast you didnt get a clear look at the 'thing' that flew past you. In the main hall, there are entrances to three big rooms.\n", "You reenter the main hallway again\nThis room has several entrances. Theres a stairs up, a door to the north west south, and hallway to the east",
                "With the flashlight you manage to find a dusty old piece of cloth you can use as bandage!");
        map[2][3][1] = r;

        // Room with ghost
        r = new Room("Ghostie", true, "", "", "", 0, false, false, true, false, false, false, false, false, false, false, mp.soundEndBoss, mp.empty, "", "", "");
        map[2][2][1] = r;
        //Study

        r = new Room("studyroom", false, "", "", "syringe", 0, false, false, false, false, false, false, true, false, false, false, mp.soundPiano, mp.empty, "Now your find yourself in the old libray/study. Nice piano music.. but, no one at the piano!?\n"
                + "The room also have two big shelves with pathways in the middle. There are alot of books, shelves, cupboards.\n"
                + "So much knowledge, abandoned.\n", "Back in the study. The piano remains silent\nThis room has only one entrance the way you came to the east",
                "With the flashlight you manage to find a injector!");
        map[1][3][1] = r;

        //Kitchen
        r = new Room("kitchen", false, "", "pills", "", 7, true, false, false, false, false, false, false, false, false, false, mp.soundDripping, mp.soundDripping, "This room looks to be a kitchen with a dinning table, a broken tap is dripping away in the zink.\n"
                + "The Room is very dark, with no windows\n"
                + "Looks like it has not been used in a while. Pots and pans hanging from a rack a moving slightly from a not existing wind, must have been when you came in. \n"
                + "Next to the kitchen zink a pill bottle standing, theres a lot of visible gooey around the kitchen zink. As you scoop up the gooey, the pots and pans seems to stir. \n"
                + "you look over your shoulder to see if someone has entered the room, but the door is firmly shut. Whatever made the pots and pans move, its gone now. \n"
                + "You pick up the pill bottle." + "this room has has only one exit to the north\n", "Reentering the Kitchen you look at the pans and pots. They arent moving this time\nThis room has only one entrance the way you came to the north", "");
        map[2][4][1] = r;

        //first floor rooms
        //1. floor hall
        r = new Room("Firstfloorhall", false, "", "", "", 0, true, false, true, false, false, false, false, false, false, true, mp.soundStairsUp, mp.empty, "this is the first floor hall, there is a door here to the north and one to the south!", "back on the 1. floor hallway, theres a stairs down to the first floor, a door south and a door north!", "");
        map[2][3][2] = r;

        //Childrens room
        r = new Room("Childrens room", false, "", "", "", 6, true, false, false, false, false, false, false, false, false, false, mp.soundChildren, mp.empty, "This room is obviously a childrens room. The floor is riddled with old toys, its extremly cold in here, and for some reason no light seems to be entering from the window. A small voice comes from a dark corner, 'wanna play?", "Reentering the childs room, you notice the room is light now, and warmer!", "");
        map[2][4][2] = r;

        // tiny bathroom
        r = new Room("Tiny bathroom", false, "syringe", "flashlight", "syringe", 4, false, false, true, false, false, false, false, false, false, false, mp.soundSpookeywater, mp.soundSpookeywater, "You walk into a tiny bathroom, srsly its really tiny. Theres a syringe and a flashlight laying on the floor, you pick them both up.\nOnly exit to the south!", "Why did i walk back in here?", "With the flashlight you manage to find a a syringe!");
        map[2][2][2] = r;

        //Basement
        //0. mainroom
        r = new Room("Mainroom", false, "", "", "screwdriver", 4, false, false, true, false, false, false, false, false, true, false, mp.soundScaryEffect, mp.soundScaryEffect, "You walk down into a creepy dark basement. The darkness down here feels unreal. Theres no way of knowing whats down here without a flashlight!, only way to go is up the stairs again!", "still dark as hell", "With the flashlight you find a screwdriver!");
        map[3][3][0] = r;

    }

    public boolean navigateMap(String d, Player player) {
        int x = player.getX();
        int y = player.getY();
        int z = player.getZ();
        switch (d) {
            case "n":
                if (roomLogic(x, y, z, d)) {
                    return false;
                }
                player.setY(--y);
                return true;
            case "s":
                if (roomLogic(x, y, z, d)) {
                    return false;
                }
                player.setY(++y);
                return true;
            case "e":

                if (roomLogic(x, y, z, d)) {
                    return false;
                }
                player.setX(++x);
                return true;
            case "w":
                if (roomLogic(x, y, z, d)) {
                    return false;
                }
                player.setX(--x);
                ;
                return true;
            case "u":
                if (roomLogic(x, y, z, d)) {
                    return false;
                }
                player.setZ(++z);
                return true;
            case "d":
                if (roomLogic(x, y, z, d)) {
                    return false;
                }
                player.setZ(--z);
                return true;
            default:
                return false;
        }
    }

    // Print map
    public void drawMap(Player player) {
        int copylength = 13;                                                               // the length of each string-box in the map 

        for (int i = 0; i < mapy; i++) {
            System.out.println("");
            for (int j = 0; j < mapx; j++) {
                if ((map[j][i][player.getZ()] == null)) {
                    System.out.print("    xxxxxxx    ");

                } else if (j == player.getX() && i == player.getY()) {
                    System.out.print("[" + fixName(player.getName(), copylength) + "]");
                } else if (!(map[j][i][player.getZ()].firstTime())) {
                    String name = fixName(map[j][i][player.getZ()].returnRoomName(), copylength);      //fixName is a method used to make sure
                    // a string is a certain length
                    System.out.print("[" + name + "]");

                } else {
                    System.out.print("    xxxxxxx    ");
                }
            }
        }
        System.out.println("\n");
        System.out.print("You are currently in the " + map[player.getX()][player.getY()][player.getZ()].returnRoomName() + "     ");

    }

    public Room getRoom(int x, int y, int z) {
        return map[x][y][z];
    }

    // Helps you stay on grid
    public boolean roomLogic(int x, int y, int z, String compass) {

        switch (compass) {
            case "n":
                if (map[x][y - 1][z] == null && (map[x][y][z].hasDoor(compass))) {
                    System.out.println("This would exit the house. Your not a quitter are you? Get back in and finnish the job !");
                    return true;
                }
                break;
            case "s":
                if (map[x][y + 1][z] == null && (map[x][y][z].hasDoor(compass))) {
                    System.out.println("This would exit the house. Your not a quitter are you? Get back in and finnish the job !");
                    return true;
                }
                break;
            case "e":
                if (map[x + 1][y][z] == null && (map[x][y][z].hasDoor(compass))) {
                    System.out.println("This would exit the house. Your not a quitter are you? Get back in and finnish the job !");
                    return true;
                }
                break;
            case "w":
                if (map[x - 1][y][z] == null && (map[x][y][z].hasDoor(compass))) {
                    System.out.println("This would exit the house. Your not a quitter are you? Get back in and finnish the job !");
                    return true;
                }
                break;
        }

        if (!(map[x][y][z].hasDoor(compass))) {
            System.out.println("There is no door here");
            return true;
        } else if (map[x][y][z].isLock(compass)) {
            System.out.println("The door is locked here, you need something to unlock it, like a screwdriver!");
            return true;

        } else if (y >= mapy || x >= mapx || x <= 0 || y <= 0) {
            System.out.println("out of array");
            return true;
        }

        return false;

    }

    public String fixName(String txt, int length) {

        while (txt.length() < length) {             //Making sure the name of the room on the map is 'length' long !
            String temp = " ";
            temp += txt;
            txt = temp;
            txt += " ";
        }
        if (txt.length() > length) {
            txt = txt.substring(0, length);
        }
        return txt;
    }

    public Room[][][] getLot() {            // returns the whole map array for saving.
        return map;
    }

    public void setMap(Room[][][] map) {    // inserts the whole map array from load.
        this.map = map;
    }
}
