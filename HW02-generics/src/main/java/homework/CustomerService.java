package homework;


import java.util.*;

public class CustomerService implements Cloneable{

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final NavigableMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    private Long idCustomer = 0L;

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        NavigableMap<Customer, String> mapClone =  new TreeMap<>(Comparator.comparingLong(Customer::getScores));
        mapClone.putAll(map);
        return mapClone.pollFirstEntry(); // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        // это "заглушка, чтобы скомилировать"
        if (map.containsKey(customer)){
            return map.entrySet().stream().skip(customer.getId()).max(Comparator.comparingLong(lg -> lg.getKey().getScores())).get();
        }
        return map.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        map.put(customer,data);
    }
}
