package service;

import constants.Denomination;
import entity.Storage;

import java.util.List;

public interface AtmService {

    List<Storage> getStorageList();
    int getAmountByDenomination(Denomination denomination);
    void removeDenominationFromStorage(Denomination denomination);

}
