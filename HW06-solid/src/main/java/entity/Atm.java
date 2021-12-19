package entity;

import constants.Denomination;
import exception.AtmException;
import service.AtmService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Atm implements AtmService {

    private List<Storage> storageList;

    public Atm() {
        this.storageList = new ArrayList<>();
        Arrays.stream(Denomination.values())
                .forEach(denomination -> this.addNewStorage(denomination,0));
    }

    @Override
    public int getBalance() {
        return this.storageList.stream()
                .map(d -> d.getDenomination().getValue() * d.getAmount())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public void takeMoney(Denomination denomination, int amount) {
        for (Storage storage: this.storageList) {
            if (storage.getDenomination() == denomination)
                storage.increase(amount);
            else
                continue;
        }
    }

    @Override
    public List<Denomination> giveMoney(int moneyValue) {
        if (moneyValue > getBalance())
            throw  new AtmException("Недостаточно денеженых средств в банкомате");
        else
            return countAndGiveMoney(moneyValue);
    }

    private List<Denomination> countAndGiveMoney(int moneyValue){

        int sum = moneyValue;
        List<Denomination> listDenomination = new ArrayList();
        List<Denomination> denominationList = Arrays.stream(Denomination.values()).sorted(Comparator.comparingInt(Denomination::getValue).reversed()).collect(Collectors.toList());

        for (Denomination denomination: denominationList) {
            while (denomination.getValue() <= sum && this.getAmountByDenomination(denomination) > 0){
                sum -= denomination.getValue();
                this.removeDenominationFromStorage(denomination);
                listDenomination.add(denomination);
            }
        }

        if (sum > 0){
            throw  new AtmException("Недостаточно мелких купюр в банкомате");
        }

        return listDenomination;
    }

    private int getAmountByDenomination(Denomination denomination){
        return this.storageList.stream()
                .filter(s->s.getDenomination() == denomination)
                .findFirst().get().getAmount();
    }

    private void removeDenominationFromStorage(Denomination denomination){
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
