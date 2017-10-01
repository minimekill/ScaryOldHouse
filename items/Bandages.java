package old80house.items;

public class Bandages implements HealthBoost {

    private final int x = 50; // Health size
    private final String y = "Bandages helped!";

    @Override
    public int use() {
        return this.x;
    }

    @Override
    public String useText() {
        return y;
    }

}
