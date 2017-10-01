package old80house;

public class Monster {

    private int gooey, hp, xp, damage, x, y, z;
    private String loot, name;

    Monster(String name, int xp, int hp, int damage, int gooey, int x, int y, int z) {
        this.name = name;
        this.xp = xp;
        this.hp = hp;
        this.damage = damage;
        this.gooey = gooey;

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getGooey() {
        return gooey;
    }

    public void setGooey(int gooey) {
        this.gooey = gooey;
    }

    public String getName() {
        return this.name;
    }

    public void setXp() {

    }

    public int getXp() {
        return this.xp;
    }

    public void setHp() {

    }

    public int getHp() {
        return this.hp;
    }

    public void setDmg() {

    }

    public int getDmg() {
        return this.damage;
    }

    public void setLoot() {

    }

    public String getLoot() {
        return this.loot;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

}
