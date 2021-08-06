package ru.otus;

import ru.otus.framework.TestFramework;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String className = "ru.otus.test.TestClass";
        TestFramework framework = new TestFramework();
        framework.start(className);
    }

}
