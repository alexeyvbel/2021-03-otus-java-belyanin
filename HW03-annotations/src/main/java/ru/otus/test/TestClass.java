package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestClass {

    @After
    public void doAfter2(){
        System.out.println("Метод doAfter2");
        throw new RuntimeException();
    }

    @Test
    public void doTest(){
        System.out.println("Метод doTest 1" + 1/0);
    }

    @Before
    public void doBefore(){
        System.out.println("Метод doBefore");
    }

    @After
    public void doAfter(){
        System.out.println("Метод doAfter");
        throw new RuntimeException();
    }

    @Test
    public void doTest2(){
        System.out.println("Метод doTest2");
    }

    @Before
    public void doBefore2(){
        System.out.println("Метод doBefore2");
    }


}
