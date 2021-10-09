package service;

import constants.Denomination;
import entity.Storage;
import exception.AtmException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{
    private final AtmService atm;

    public UserServiceImpl(AtmService atm) {
        this.atm = atm;
    }

    @Override
    public int getBalance() {
        return atm.getStorageList().stream()
                .map(d -> d.getDenomination().getValue() * d.getAmount())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public void takeMoney(Denomination denomination, int amount) {
        for (Storage storage: atm.getStorageList()) {
            if (storage.getDenomination() == denomination)
                storage.increase(amount);
            else
                continue;
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

        int sum = moneyValue;

        List<Denomination> denominationList = Arrays.stream(Denomination.values()).sorted(Comparator.comparingInt(Denomination::getValue).reversed()).collect(Collectors.toList());

        for (Denomination denomination: denominationList) {
            while (denomination.getValue() <= sum && atm.getAmountByDenomination(denomination) > 0){
                sum -= denomination.getValue();
                atm.removeDenominationFromStorage(denomination);
            }
        }

        if (sum > 0){
            throw  new AtmException("Недостаточно мелких купюр в банкомате");
        }
    }
}
