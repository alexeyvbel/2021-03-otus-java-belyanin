package ru.otus.main;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class runTest {

    @Test
    public void doTest(){
        System.out.println("Выполняется тестирование");
    }

    @Before
    public void doBefore(){
        System.out.println("Метод во выполенения теста");
    }

    @After
    public void doAfter(){
        System.out.println("Метод после выполнения теста");
    }
}
