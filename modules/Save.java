package old80house.modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import old80house.Monster;
import old80house.Player;
import old80house.map.Room;

public class Save {

    private boolean[] mp = new boolean[4];

    private String monster = "";
    private String room = "";
    private String firstTime = "";
    private String secret = "";
    private String playerGooey;
    private String playerName;
    private String playerHealth;
    private String playerXp;
    private String playerInv = "";
    private int stringCounter = 0;
    private int px, py, pz;
    private int loopCount = 0;
    private Player player1;
    private Room[][][] map;

    public void save(Player player, Room[][][] map, Monster[] ma) throws IOException {
        PrintWriter pw = new PrintWriter("save.txt");
        pw.close();
        monster = "";  //theese 3 lines are just ugly code that makes sure everything is reset with each save... even the txt file

        saveMap(map);
        saveInv(player);
        saveMonster(ma);
        String msg = player.getGooey() + "|" + player.getName() + "|" + player.getHealth() + "|" + player.getXp() + "|" + player.getX() + "|" + player.getY() + "|" + player.getZ() + "|"
                + System.lineSeparator() + playerInv
                + System.lineSeparator() + room
                + System.lineSeparator() + monster;

        Files.write(Paths.get("save.txt"), msg.getBytes(), StandardOpenOption.APPEND);
    }

    public void saveInv(Player player) {
        playerInv = "";
        for (int i = 0; i < player.getSize(); i++) {
            if (!player.lookForItem2(i).equals("Proton gun")) { //when we save the player we dont need to save his gun, we already give it to him when we initialize the player class
                playerInv += player.lookForItem2(i) + "|";
            }

        }

        if (playerInv.length() == 0) {
            playerInv += "|";                //making sure that if inv is empty so that the load order doesnt mess up.
        }
    }

    public void saveMap(Room[][][] map) {
        room = "";
        for (int z = 0; z < 3; z++) {                               //each room only has 6 info slots that needs to get saved. we do not save locks.

            for (int y = 0; y < 7; y++) {
                for (int x = 0; x < 6; x++) {
                    if (map[x][y][z] != null) {
                        room += map[x][y][z].getItem1() + "|" + map[x][y][z].getItem2() + "|" + map[x][y][z].getRoomSecretItem() + "|" + map[x][y][z].getGooey() + "|" + map[x][y][z].firstTime() + "|"
                                + map[x][y][z].getSecret() + "|";

                    }
                }

            }
        }
    }

    public void saveMonster(Monster[] ma) {

        for (int i = 0; i < ma.length; i++) {                                        //simple truth false info wether the monster in the array is alive or not
            if (ma[i] != null) {
                monster += "true" + "|";
            } else {
                monster += "false" + "|";
            }

        }

    }

    public void load(Room[][][] map) {

        try {
            this.map = map;
            Scanner db = new Scanner(new File("save.txt"));                                 //the load is split up into 4 parts, playerinfo playerinventory, roominfo and monstercount

            String line = db.next();

            //   System.out.println(line + " 1");
            loadPlayer(line);

            line = db.next();
            //   System.out.println(line + " 2");
            player1 = new Player(playerName, Integer.parseInt(playerHealth), Integer.parseInt(playerGooey), Integer.parseInt(playerXp));
            player1.setX(px);
            player1.setY(py);
            player1.setZ(pz);
            if (line.length() != 0) {
                loadInv(line);
            }
            line = db.next();
            // System.out.println(line + " 3");
            this.map = loadMap(line);
            stringCounter = 0;
            loopCount = 0;

            line = db.next();
            //System.out.println(line + " 4");  //shows the lines of the save.txt
            loadMonster(line);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadPlayer(String line) {
        for (int j = 0; j < line.length(); j++) {
            String temp = line.substring(j, j + 1);
            if (temp.equals("|") && loopCount == 0) {
                playerGooey = line.substring(0, j);
                loopCount++;
                stringCounter = j + 1;
            } else if (temp.equals("|") && loopCount == 1) {
                playerName = line.substring(stringCounter, j);
                loopCount++;
                stringCounter = j + 1;
            } else if (temp.equals("|") && loopCount == 2) {
                playerHealth = line.substring(stringCounter, j);
                loopCount++;
                stringCounter = j + 1;
            } else if (temp.equals("|") && loopCount == 3) {
                playerXp = line.substring(stringCounter, j);
                loopCount++;
                stringCounter = j + 1;
            } else if (temp.equals("|") && loopCount == 4) {
                px = Integer.parseInt(line.substring(stringCounter, j));
                loopCount++;
                stringCounter = j + 1;
            } else if (temp.equals("|") && loopCount == 5) {
                py = Integer.parseInt(line.substring(stringCounter, j));
                loopCount++;
                stringCounter = j + 1;
            } else if (temp.equals("|") && loopCount == 6) {
                pz = Integer.parseInt(line.substring(stringCounter, j));
                loopCount++;
                stringCounter = j + 1;
            }

        }
    }

    public void loadInv(String line) {
        stringCounter = 0;
        for (int k = 0; k < line.length(); k++) {
            String temp = line.substring(k, k + 1);
            if (temp.equals("|")) {
                player1.putInv(line.substring(stringCounter, k));
                stringCounter = k + 1;

            }

        }
    }

    public Room[][][] loadMap(String line) {
        stringCounter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 6; k++) {

                    if (map[k][j][i] != null) {
                        loopCount = 0;

                        for (int l = stringCounter; l < line.length(); l++) {        //loop in a loop in a loop in a loop...4 x loop !!!!!!!!!!!!

                            String temp = line.substring(l, l + 1);

                            if (temp.equals("|") && loopCount == 0) {
                                map[k][j][i].setItem1(line.substring(stringCounter, l));
                                loopCount++;
                                stringCounter = l + 1;

                            } else if (temp.equals("|") && loopCount == 1) {
                                map[k][j][i].setItem2(line.substring(stringCounter, l));
                                loopCount++;
                                stringCounter = l + 1;
                            } else if (temp.equals("|") && loopCount == 2) {
                                map[k][j][i].setItemSecret(line.substring(stringCounter, l));
                                loopCount++;

                                stringCounter = l + 1;
                            } else if (temp.equals("|") && loopCount == 3) {

                                map[k][j][i].setGooey(Integer.parseInt(line.substring(stringCounter, l)));
                                loopCount++;
                                stringCounter = l + 1;
                            } else if (temp.equals("|") && loopCount == 4) {
                                if (line.substring(stringCounter, l).equals("false")) {

                                    map[k][j][i].setFirstTime();
                                }
                                loopCount++;
                                stringCounter = l + 1;
                            } else if (temp.equals("|") && loopCount == 5) {
                                if (line.substring(stringCounter, l).equals("false")) {
                                    map[k][j][i].setSecret(false);
                                }
                                loopCount++;
                                stringCounter = l + 1;
                                //  System.out.println("loading " + k + " " + j + " " + i);   //showing what rooms is being done
                            }
                            if (loopCount > 5) {
                                break;
                            }

                        }

                    }

                }

            }

        }
        return map;
    }

    public void loadMonster(String line) {
        loopCount = 0;
        stringCounter = 0;
        int tempCount = 0;
        for (int i = 0; i < line.length(); i++) {
            String temp = line.substring(i, i + 1);
            if (temp.equals("|")) {
                if (line.substring(stringCounter, i).equals("true")) {
                    mp[tempCount] = true;
                    stringCounter = i + 1;
                    tempCount++;
                } else {
                    mp[tempCount] = false;
                    stringCounter = i + 1;
                    tempCount++;
                }
            }
        }

    }

    public Player getPlayer() {
        return player1;
    }

    public boolean[] getMonster() {
        return mp;
    }

    public Room[][][] getMap() {
        return map;
    }

}
