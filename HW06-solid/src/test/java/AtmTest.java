import constants.Denomination;
import entity.Atm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AtmService;

import java.util.List;

public class AtmTest {
    private AtmService atm;

    @BeforeEach
    void setUp(){
        atm = new Atm();
    }

    @Test
    @DisplayName("Проверка значения баланса банкомата")
    void test1(){
        atm.takeMoney(Denomination.TEN, 2);
        atm.takeMoney(Denomination.TEN, 2);
        atm.takeMoney(Denomination.FIFTY, 10);
        atm.takeMoney(Denomination.HUNDRED, 10);
        atm.takeMoney(Denomination.TWO_HUNDRED, 10);
        atm.takeMoney(Denomination.THOUSAND, 10);
        atm.takeMoney(Denomination.TWO_THOUSAND, 10);
        atm.takeMoney(Denomination.FIVE_HUNDRED, 10);
        List<Denomination> listDenomination = atm.giveMoney(7990);
        Assertions.assertEquals(atm.getBalance(),38540 - 7990);
    }

}
