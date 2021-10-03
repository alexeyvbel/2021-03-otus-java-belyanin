package service;

import constants.Denomination;

public interface UserService {

    int getBalance();

    void takeMoney(Denomination denomination, int amount);

    void giveMoney(int moneyValue);

}
