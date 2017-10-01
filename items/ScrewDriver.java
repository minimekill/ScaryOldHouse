package old80house.items;

public class ScrewDriver implements Tools, Weapons {

    private int damage = 20;
    String temp = "You turn on the... screwdriver... is that even possible?";

    @Override
    public boolean has() {
        return true;    //use the flashlight
    }

    @Override
    public int use() {
        return this.damage;
    }

    @Override
    public String useText() {
        return temp;
    }

}
