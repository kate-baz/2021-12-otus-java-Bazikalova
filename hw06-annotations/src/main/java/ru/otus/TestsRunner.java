package ru.otus;

public class TestsRunner {
    public static void main(String[] args) {
        MyJUnit testRunner = new MyJUnit();
        testRunner.runTests(ClassForTesting.class);
    }

}
