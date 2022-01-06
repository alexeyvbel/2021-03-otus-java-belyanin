package service;

import constants.Denomination;

import java.util.List;

public interface AtmService {

    int getBalance();
    void takeMoney(Denomination denomination, int amount);
    List<Denomination> giveMoney(int moneyValue);

}
