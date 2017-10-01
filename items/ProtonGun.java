package old80house.items;

public class ProtonGun implements Weapons {

    private int damage = 25;
    private String temp = "You are using the faboulous Proton gun, a Ghostbusters dearest weapon and partner.";

    @Override
    public int use() {
        return this.damage;
    }

    @Override
    public String useText() {
        return this.temp;
    }

    @Override
    public boolean has() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
