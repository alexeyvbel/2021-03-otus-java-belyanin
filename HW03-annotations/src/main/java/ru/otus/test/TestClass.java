package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestClass {

    @After
    public void doAfter2(){
        System.out.println("Метод после выполнения теста 2");
        throw new RuntimeException();
    }

    @Test
    public void doTest(){
        System.out.println("Выполняется тестирование 1");
    }

    @Before
    public void doBefore(){
        System.out.println("Метод во выполенения теста");
    }

    @After
    public void doAfter(){
        System.out.println("Метод после выполнения теста");
        throw new RuntimeException();
    }

    @Test
    public void doTest2(){
        System.out.println("Выполняется тестирование 2");
    }

    @Before
    public void doBefore2(){
        System.out.println("Метод во выполенения теста 2");
    }


}
