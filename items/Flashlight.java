package old80house.items;

public class Flashlight implements Tools {

    private String temp = "you turn on the flashlight";

    @Override
    public boolean has() {
        return true;
    }

    @Override
    public String useText() {
        return temp;
    }

    @Override
    public int use() {
        return 0;
    }

}
