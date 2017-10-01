package old80house.items;

import java.util.Random;

public class Syringe implements HealthBoost {

    private int x; // Health size, should randomize
    private boolean luck;    //wether its a lucky syringe or not
    private String positive = "Whatever was in that thing, you sure feel better now!!";
    private String negative = "Syringe was filled with toilet cleaner... the blue color gave it away.!\n";

    @Override
    public int use() {
        Random r = new Random();
        int temp = r.nextInt(3) + 1;
        if (temp > 1) {
            luck = true;
            x = 75;
        } else {
            luck = false;
            x = -15;
        }

        return this.x;
    }

    @Override
    public String useText() {
        if (luck) {
            return positive;
        } else {
            return negative;
        }

    }

}
