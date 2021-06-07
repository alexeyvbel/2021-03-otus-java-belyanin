package ru.otus.main;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class main {

    public static void main(String[] args) {
        Class<?> runTest = args[0].getClass();

        runTest.isAnnotationPresent(Before.class);
        runTest.isAnnotationPresent(Test.class);
        runTest.isAnnotationPresent(After.class);

    }
}
