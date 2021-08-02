package ru.otus.framework;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestFramework {
    public void start(String className) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(className);
        System.out.println("Запускаю тест -------- " + clazz.getSimpleName());
        var listBeforeTest = Arrays.stream(clazz.getDeclaredMethods())
                .filter(s->s.isAnnotationPresent(Before.class))
                .collect(Collectors.toList());
        var listTest = Arrays.stream(clazz.getDeclaredMethods())
                .filter(s->s.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());
        var listAfterTest = Arrays.stream(clazz.getDeclaredMethods())
                .filter(s->s.isAnnotationPresent(After.class))
                .collect(Collectors.toList());
        System.out.println("---------------------------------------");
        RunTest runTest = new RunTest();

        for (Method method: listTest) {
            runTest.runListMethod(clazz,listBeforeTest);
            runTest.runMethod(clazz,method);
            runTest.runListMethod(clazz, listAfterTest);
        }
        System.out.println("---------------------------------------");
        System.out.println("Всего успешно было выполнено " + runTest.getTestAll() +
                " тестов, успешно " + runTest.getTestSuccess() +
                ", завершились с ошибкой " + runTest.getTestError());
    }
}
