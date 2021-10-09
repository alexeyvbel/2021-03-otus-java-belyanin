import constants.Denomination;
import entity.Atm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AtmService;
import service.UserService;
import service.UserServiceImpl;

public class AtmTest {
    private UserService userService;
    private AtmService atm;

    @BeforeEach
    void setUp(){
        atm = new Atm();
        userService = new UserServiceImpl(atm);
    }

    @Test
    @DisplayName("Проверка значения баланса банкомата")
    void test1(){
        userService.takeMoney(Denomination.TEN, 10);
        userService.takeMoney(Denomination.FIFTY, 10);
        userService.takeMoney(Denomination.HUNDRED, 10);
        userService.takeMoney(Denomination.TWO_HUNDRED, 10);
        userService.takeMoney(Denomination.THOUSAND, 10);
        userService.takeMoney(Denomination.TWO_THOUSAND, 10);
        userService.takeMoney(Denomination.FIVE_HUNDRED, 10);
        userService.giveMoney(7990);
        Assertions.assertEquals(userService.getBalance(),38600 - 7990);
    }

}
