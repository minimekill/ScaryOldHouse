package old80house;

import java.util.ArrayList;

public class Player {

    private String name;
    private int health, gooey, xp, damage;
    private int level = 0, x = 3, y = 3, z = 1;

    private ArrayList<String> inv = new ArrayList();      //Player backpack. String is changed out with Iteml,

    // Constructor
    public Player(String name, int health, int gooey, int xp) {
        this.name = name;
        this.health = health;
        this.gooey = gooey;
        this.inv.add("Proton gun");
        this.xp = xp;
        damage = 0;
    }

    public String getName() {
        return name;
    }

    public void setGooey(int gooey) {
        this.gooey += gooey;
    }

    public int getGooey() {
        return gooey;
    }

    public String getInv(String a) {
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i) == null) {
                //skips the empty placeholder
            } else if (inv.get(i).equals(a)) {
                String temp = inv.get(i);
                inv.remove(i);
                return temp;
            }

        }
        return "";
    }

    public void putInv(String a) {
        inv.add(a);

    }

    public String lookForItem2(int a) {
        if (inv.get(a) == null) {
            return "";
        } else {
            return inv.get(a);
        }
    }

    public String lookForItem(String a) {
        for (int i = 0; i < inv.size(); i++) {
            if (inv.get(i) == null) {
                return "";
            } else if (inv.get(i).equals(a)) {
                String temp = inv.get(i);
                return temp;
            }

        }
        return "";
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList invLook() {
        return inv;
    }

    public int getSize() {
        return inv.size();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp += xp;

    }

    public void lvlCalc() {
        if (xp > 10) {
            level = 1;
            damage = 5;
            health = health + 10;
        }
        if (xp > 20) {
            level = 2;
            damage = 10;
            health = health + 10;
        }
        if (xp > 40) {
            level = 3;
            damage = 15;
            health = health + 10;
        }
        if (xp > 55) {
            level = 4;
            damage = 25;
            health = health + 20;
        }

    }

    public int getDamage() {
        return damage;
    }
}
