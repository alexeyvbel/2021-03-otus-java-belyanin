package entity;

import constants.Denomination;

public class Storage {

    public int getAmount() {
        return amount;
    }

    private final Denomination denomination;
    private int amount;

    public Storage(Denomination denomination, int amount) {
        this.denomination = denomination;
        this.amount = amount;
    }

    public int getBalanceOfStorage(){
        return denomination.getValue() * amount;
    }

    public void increase(Integer amount){
        this.amount += amount;
    }

    public void reduce(Integer amount){
        this.amount -= amount;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
