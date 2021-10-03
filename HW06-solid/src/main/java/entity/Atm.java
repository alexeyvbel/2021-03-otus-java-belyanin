package entity;

import constants.Denomination;

import java.util.ArrayList;
import java.util.List;

public class Atm {

    private List<Storage> storageList;

    public Atm() {

        this.storageList = new ArrayList<>();
    }

    public List<Storage> getStorageList() {
        return storageList;
    }

    public void addNewStorage(Denomination denomination, int amount) {
        this.storageList = storageList;
    }

}
