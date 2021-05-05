package homework;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final Map<Customer, String> map = new HashMap<>();
    private Customer customerKey = null;

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return map.entrySet().stream().min(Comparator.comparingLong(lg -> lg.getKey().getScores())).get(); // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        // это "заглушка, чтобы скомилировать"
        if(customerKey == null && !map.entrySet().contains(customer)){
            customerKey = new Customer(map.entrySet().stream().findFirst().get().getKey().getId(),map.entrySet().stream().findFirst().get().getKey().getName(),map.entrySet().stream().findFirst().get().getKey().getScores());
            return map.entrySet().stream().findFirst().get();
        }
        if(customerKey.equals(customer)){
            for (Map.Entry<Customer, String> entry: map.entrySet()) {
                if (entry.getKey().getScores() > customerKey.getScores() && entry.getKey().getId() != customerKey.getId()) {
                    return entry;
                }
            }
        }
        return null;
    }

    public void add(Customer customer, String data) {
        map.put(customer,data);
    }
}
