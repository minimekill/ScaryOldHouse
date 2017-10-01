package old80house;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import old80house.items.Flashlight;
import old80house.items.Pills;
import old80house.items.ScrewDriver;
import old80house.items.Syringe;
import old80house.map.Map;
import old80house.map.Room;
import old80house.modules.Combat;
import old80house.modules.Highscore;
import old80house.modules.ImageViewer;
import old80house.modules.MediaPlayer;
import old80house.modules.Save;

public class Controller {

    ImageViewer img = new ImageViewer();
    MediaPlayer mp = new MediaPlayer();
    Save save = new Save();
    Highscore h = new Highscore();
    Player player1;
    Room room;
    Map map = new Map(mp);
    Scanner scan = new Scanner(System.in);
    private String breaker = "------------------------------------";
    private String input;
    private ArrayList<String> inv;
    private boolean moved, trigger1 = true;              //a check if the player moved or not
    private boolean isAlive = true;
    private String sout = "\nType [n] for north, [e] for east, [s] for south or [w] for west to move\nType [u] for going up stairs or [d] for down stairs.\nType [h] for help";

    // Create monsters and array
    Monster[] MonsterArray = new Monster[4];
    Monster sg = new Monster("SnotGhost", 15, 65, 20, 2, 2, 3, 1);
    Monster cg = new Monster("ChildGhost", 10, 50, 25, 15, 2, 4, 2);
    Monster wg = new Monster("WhifeGhost", 25, 80, 30, 8, 5, 3, 1);
    Monster eb = new Monster("EndBossGhost", 75, 100, 50, 20, 2, 2, 1);

    void gameStart() throws IOException {
        img.showImage("GameCover.jpg");
        init();

        // Game is running
        play();
        end(false);
    }

    // Create player and map
    void init() throws IOException {
        System.out.println("Would you like to start a new game or continue a previous one?\nType [load] or [new]");

        while (true) {

            input = scan.next();
            if (input.equals("load") || input.equals("new")) {
                break;
            }
            System.out.println(input + " was not recognised. Use [load] or [new]");

        }

        if (input.equals("load")) {
            map.createMap();                        //we still need to create the map even though we are loading. all loading is doing is changing the values from the txt file.
            save.load(map.getLot());
            map.setMap(save.getMap());
            this.player1 = save.getPlayer();
            boolean[] mpb = save.getMonster();

            if (mpb[0] == true) {
                MonsterArray[0] = sg;
            } else {
                MonsterArray[0] = null;
            }
            if (mpb[1] == true) {
                MonsterArray[1] = cg;
            } else {
                MonsterArray[1] = null;
            }
            if (mpb[2] == true) {
                MonsterArray[2] = wg;
            } else {
                MonsterArray[2] = null;
            }
            if (mpb[3] == true) {
                MonsterArray[3] = eb;
            } else {
                MonsterArray[3] = null;
            }

            System.out.println("Welcome back " + player1.getName() + ". Press enter to continue your adventure!");
            pressAnyKeyToContinue();

        } else {
            // Add monsters to its array
            MonsterArray[0] = sg;
            MonsterArray[1] = cg;
            MonsterArray[2] = wg;
            MonsterArray[3] = eb;

            System.out.println("Please type your name");
            String name = scan.next();

            player1 = new Player(name, 100, 0, 0);                 // creates the player with health and gooey
            map.createMap();

            mp.PlaySound(mp.soundTheme, true);                  // plays a sound in MediaPlayer
            System.out.println("WELCOME! " + player1.getName());
            System.out.println("The year is 1984. You have been send from Ghostbusters headquarters to investigate a case of paranormal activity, in an old victorian house"
                    + "\nThe call came from a neighbour, mr Cromwell, Who has reported strange noises and moans."
                    + "\nYou are pulling a gloryfied nightshift, something thats appointed mostly to the lucky new employees, such as yourself. Remember to gather ghost gooey-slime."
                    + "\nYou have just arrived standing at the entrance of the house");
            System.out.println(breaker);
            System.out.println("Press enter to walk inside!");
            pressAnyKeyToContinue();                            // calling a method that waits for any input
            mp.stopMusic();

        }
    }

    void play() {
        mp.PlaySound(mp.soundScary, true);                //loops the music!

        while (isAlive) {
            trigger();                                       // sets a trigger to change something
            System.out.println(breaker);
            room = map.getRoom(player1.getX(), player1.getY(), player1.getZ());
            File sound = room.getSound();
            room.roomPlay();
            mp.PlaySound(sound, false);
            player1.setGooey(room.getGooey());              // gives player the gooey found in the room
            if (!room.getItem1().equals("") && room.firstTime()) {
                player1.putInv(room.getItem1());
            }
            if (!room.getItem2().equals("") && room.firstTime()) { //gives player loot from both room item slots
                player1.putInv(room.getItem2());
            }
            room.setFirstTime();                               // sets room firsttime boolean to false

            for (int i = 0; i < MonsterArray.length; i++) {    // Looks through monster array to check if player has found a ghost!

                if (MonsterArray[i] != null) {
                    Monster monster = MonsterArray[i];
                    if (monster.getX() == player1.getX() && monster.getY() == player1.getY() && monster.getZ() == player1.getZ()) {
                        System.out.println("Theres a " + monster.getName() + " in this room, press enter to start the fight!");
                        pressAnyKeyToContinue();

                        Combat c = new Combat(player1, monster, mp);
                        c.Fight();

                        MonsterArray[i] = null;

                    }
                }

            }

            if (player1.getHealth() <= 0) {
                try {
                    end(false);         // ends game if player dies
                } catch (IOException ex) {
                    System.out.println("game wasnt able to end...");
                }
            }
            if (player1.getX() == 2 && player1.getY() == 2 && player1.getZ() == 1) {
                try {
                    end(true);
                } catch (IOException ex) {
                    System.out.println("game wasnt able to win");
                }
            }
            while (!moved) {          // a loop that keeps you locked until you move legally
                scannerMove();          //checks if whats being typed is allowed
                moved = map.navigateMap(input, player1); // calculates the movement in the 3d array
            }
            moved = false;
        }
        
    }

    void end(boolean won) throws IOException {

        if (player1.getHealth() <= 0) {
            System.out.println("You died a horrible death.\n"
                    + "At least you managed to gather " + player1.getGooey() + " gooey!");
        } else {
            System.out.println("YOU WON");
        }
        mp.stopMusic();
        mp.PlaySound(mp.soundTheme, true);
        h.FileWriter(player1.getName()); // Write name to txt
        h.FileWriter(String.valueOf(player1.getGooey()));
        h.FileReader();
        System.out.println("--------------------------------- \n"
                + "Wanna play again " + player1.getName() + "?  y/n");

        scannerCheck2();
        if (input.equals("y")) {
            mp.stopMusic();

            gameStart();
        } else {
            System.exit(0);
        }

    }

    void scannerMove() {
        System.out.println(sout);
        while (true) {
            input = scan.next();
            switch (input) {
                case "n":
                case "e":
                case "w":
                case "s":
                case "u":
                case "d":
                    return;
                case "h":
                    System.out.println("Here are the actions you can do:\nType [inv] to see your inventory\n"
                            + "Type [hp] to see your current health\nType [use] to see playeractions\ntype [m] to draw a map, your name marks where you are.\nPress [save] to save your progress.");
                    break;
                case "g":
                    System.out.println("You current amount of gooey is " + player1.getGooey());
                    System.out.println(sout);
                    break;
                case "m":
                    map.drawMap(player1);
                    System.out.println(sout);
                    break;
                case "save": {
                    try {
                        save.save(player1, map.getLot(), MonsterArray);
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "inv":
                    showInv();
                    break;
                case "hp":
                    int hp = player1.getHealth();
                    System.out.println(hp + " hp");
                    if (hp >= 85) {
                        System.out.print(" - You feel pretty good!");
                    } else if (hp < 85 && hp >= 40) {
                        System.out.print(" - You feel kinda bad!");
                    } else if (hp < 40 && hp >= 15) {
                        System.out.print(" - You feel horrible!");
                    } else {
                        System.out.print(" - You are about to die!");
                    }

                    break;

                default:
                    System.out.println(input + " is not recognised. try again. Type [n],[s],[e] or [w] to move, or [h] or [g] for help or amount of gooey.");
                    break;
            }

        }

    }

    void scannerCheck2() {
        while (true) {
            input = scan.next();
            if (input.equals("n") || input.equals("y")) {
                return;
            }

            System.out.println(input + " is not recognised. try again. Type north east south or west.");
        }

    }

    public void pressAnyKeyToContinue() {

        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("error" + e);

        }
    }

    public void showInv() {
        inv = player1.invLook();
        String stemp1 = "pills";
        String stemp2 = "gun";
        String stemp3 = "bandage";
        String stemp4 = "syringe";
        String stemp5 = "screwdriver";
        String stemp6 = "flashlight";
        int itemp1 = 0;
        int itemp2 = 0;
        int itemp3 = 0;
        int itemp4 = 0;
        int itemp5 = 0;
        int itemp6 = 0;
        for (int i = 0; i < player1.getSize(); i++) {

            String temp = player1.lookForItem2(i);
            if (temp.equals(stemp1)) {
                stemp1 = temp;
                itemp1++;
            } else if (temp.equals(stemp2)) {
                stemp2 = temp;
                itemp2++;
            } else if (temp.equals(stemp3)) {
                stemp3 = temp;
                itemp3++;
            } else if (temp.equals(stemp4)) {
                stemp4 = temp;
                itemp4++;
            } else if (temp.equals(stemp5)) {
                stemp5 = temp;
                itemp5++;
            } else if (temp.equals(stemp6)) {
                stemp6 = temp;
                itemp6++;
            }
        }
        if (itemp1 + itemp2 + itemp3 + itemp4 + itemp5 + itemp6 == 0) {
            System.out.println("Iventory is empty");
            return;
        }
        if (itemp1 != 0) {
            System.out.println(itemp1 + " " + stemp1 + " [1]");
        }
        if (itemp2 != 0) {
            System.out.println(itemp2 + " " + stemp2 + " [2]");
        }
        if (itemp3 != 0) {
            System.out.println(itemp3 + " " + stemp3 + " [3]");
        }
        if (itemp4 != 0) {
            System.out.println(itemp4 + " " + stemp4 + " [4]");
        }
        if (itemp5 != 0) {
            System.out.println(itemp5 + " " + stemp5 + " [5]");
        }
        if (itemp6 != 0) {
            System.out.println(itemp6 + " " + stemp6 + " [6]");
        }
        System.out.println("press a number to use an item or press [q] to exit the inventory menu");
        use();

    }

    public void use() {

        while (true) {
            input = scan.next();
            switch (input) {
                case "1":
                    if (player1.getInv("pills").equals("pills")) {
                        Pills pills = new Pills();
                        player1.setHealth(player1.getHealth() + pills.use());

                        System.out.println(pills.useText());
                        return;
                    } else {
                        System.out.println("You are out of pills");
                        showInv();
                    }
                    break;
                case "4":
                    if (player1.getInv("syringe").equals("syringe")) {
                        Syringe syringe = new Syringe();
                        int temp = syringe.use();
                        if (temp > 0) {
                            mp.PlaySound(mp.soundMedic, false);
                        } else {
                            mp.PlaySound(mp.soundPain, false);
                        }
                        player1.setHealth(player1.getHealth() + temp);

                        System.out.println(syringe.useText());
                    } else {
                        System.out.println("You got no syringes!");
                    }
                    break;
                case "3":

                case "5":
                    if (player1.lookForItem("screwdriver").equals("screwdriver")) {
                        ScrewDriver sd = new ScrewDriver();
                        room.roomUnlock();
                        System.out.println("You have unlocked the door!");
                        sd.has(); //this does NoTHING!!!
                        System.out.println(sout);
                        return;
                    } else {
                        System.out.println("You do no have a screwdriver!, maybe there is one in the basement!");
                    }
                    break;
                case "6":
                    if (player1.lookForItem("flashlight").equals("flashlight")) {
                        Flashlight flash = new Flashlight();
                        flash.use(); //this does NoTHING!!!

                        System.out.println(flash.useText());
                        System.out.println(room.getRoomSecret());                   //using the flashlight in a room will reveal a secret
                        player1.putInv(room.getRoomSecretItem());
                        room.setSecret();                                           // making sure room cannot give more then one secret item
                        System.out.println(sout);
                        return;
                    } else {
                        System.out.println("You got no flashlight!");
                    }
                    break;
                case "q":
                    System.out.println("You are back in the main menu");
                    System.out.println(sout);
                    return;

            }
        }

    }  // method end

    public void trigger() {
        if (trigger1 && player1.lookForItem("flashlight").equals("flashlight")) {
            for (int i = 0; i < MonsterArray.length; i++) {
                if (MonsterArray[i] != null) {

                    Monster monster = MonsterArray[i];
                    if (monster.getName().equals("WhifeGhost")) {       //moving a ghost into the bedroom once player has a flashlight
                        monster.setX(4);
                        System.out.println("A scream of a woman erubts from somewhere downstairs");
                        mp.PlaySound(mp.soundScaryScream, false);
                        trigger1 = false;                               //stopping the trigger, making the look for null if statement redundant
                    }

                }
            }

        }

    }
}
