package service;

import constants.Denomination;
import entity.Atm;
import entity.Storage;
import exception.AtmException;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{
    private final Atm atm;

    public UserServiceImpl(Atm atm) {
        this.atm = atm;
    }

    @Override
    public int getBalance() {
        return atm.getStorageList().stream()
                .map(d -> d.getDenomination().getValue() * d.getAmount())
                .mapToInt(i -> i)
                .sum();
    }

    @Override
    public void takeMoney(Denomination denomination, int amount) {
        for (Storage storage: atm.getStorageList()) {
            if (storage.getDenomination() == denomination)
                storage.increase(amount);
            else
                atm.addNewStorage(denomination, amount);
        }
    }

    @Override
    public void giveMoney(int moneyValue) {
        if (moneyValue > getBalance())
            throw  new AtmException("Недостаточно денеженых средств в банкомате");
        else
            countAndGiveMoney(moneyValue);
    }

    private void countAndGiveMoney(int moneyValue){
        List<Denomination> denominationList =
                atm.getStorageList()
                        .stream()
                        .map(d -> d.getDenomination())
                        .collect(Collectors.toList());
    }
}
