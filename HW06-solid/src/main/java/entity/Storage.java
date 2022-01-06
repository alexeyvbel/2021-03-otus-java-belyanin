package entity;

import constants.Denomination;
import exception.AtmException;

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

    public void increase(int amount){
        if (checkAmount(amount))
            this.amount += amount;
    }

    public void reduce(int amount){
        if (checkAmount(amount))
            this.amount -= amount;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    private boolean checkAmount(int amount) {
        if (amount <= 0){
            throw  new AtmException("Значение банкнот не может быть отрицательным");
        }
        return true;
    }
}
