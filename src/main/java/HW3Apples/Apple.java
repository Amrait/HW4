package HW3Apples;

/**
 * Created by Олексій on 16.02.2017.
 */
public class Apple {
    private int Amount;
    private String Cultivar;

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getCultivar() {
        return Cultivar;
    }

    public void setCultivar(String cultivar) {
        Cultivar = cultivar;
    }

    public Apple(int amount) {
        this.Amount = amount;
        this.Cultivar = "Макінтош";
    }
}
