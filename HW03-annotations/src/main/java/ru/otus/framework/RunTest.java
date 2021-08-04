package ru.otus.framework;

import java.lang.reflect.Method;
import java.util.List;

public class RunTest {
    private int testSuccess;
    private int testError;
    private int testAll;

    public int getTestSuccess() {
        return testSuccess;
    }

    public int getTestError() {
        return testError;
    }

    public int getTestAll() {
        return testAll;
    }

    public void runMethod(Object object, Class clazz, Method runMethod){
        try {
            Method method = clazz.getDeclaredMethod(runMethod.getName());
            method.setAccessible(true);
            method.invoke(object);
            System.out.println("Тест выполнен успешно, всего успешно " + ++testSuccess);
        } catch (Exception e) {
            testError ++;
            System.out.println("Тест выполнен с ошибкой!! Всего с ошибкой " + testError);
        }
        testAll = testSuccess + testError;
    }

    public void runListMethod(Object object, Class clazz, List<Method> methods){
        for (Method method: methods) {
            runMethod(object, clazz, method);
        }
    }

}
