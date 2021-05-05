package homework;

import java.util.ArrayList;
import java.util.List;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private List<Customer> list = new ArrayList<Customer>();
    private int i = 0;

    public void add(Customer customer) {
        list.add(customer);
        i = list.size();
    }

    public Customer take() {
        i--;
        return list.get(i);// это "заглушка, чтобы скомилировать"
    }
}
