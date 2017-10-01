package old80house.items;

public class Pills implements HealthBoost {

    private int x = 20; // Health size
    private String y = "The pills helped!";

    @Override
    public int use() {
        return this.x;
    }

    @Override
    public String useText() {
        return y;
    }

}
