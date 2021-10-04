package entity;

import constants.Denomination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Atm {

    private List<Storage> storageList;

    public Atm() {
        this.storageList = new ArrayList<>();
        Arrays.stream(Denomination.values())
                .forEach(denomination -> this.addNewStorage(denomination,0));
    }

    public List<Storage> getStorageList() {
        return storageList;
    }

    public int getAmountByDenomination(Denomination denomination){
        return this.storageList.stream()
                .filter(s->s.getDenomination() == denomination)
                .findFirst().get().getAmount();
    }

    public void removeDenominationFromStorage(Denomination denomination){
        this.storageList.stream()
                .forEach(s->{
                    if (s.getDenomination() == denomination){
                        s.reduce(1);
                    }
                });
    }

    private void addNewStorage(Denomination denomination, int amount) {
        this.storageList.add(new Storage(denomination, amount));
    }

}
