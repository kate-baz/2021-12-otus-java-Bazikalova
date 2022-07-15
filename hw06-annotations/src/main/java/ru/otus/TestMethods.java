package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestMethods {
    private List<Method> testMethods;

    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    TestMethods() {
        testMethods = new ArrayList<>();
        beforeMethods = new ArrayList<>();
        afterMethods = new ArrayList<>();
    }

    TestMethods(List<Method> testMethods, List<Method> beforeMethods, List<Method>afterMethods) {
        this.afterMethods = afterMethods;
        this.beforeMethods = beforeMethods;
        this.testMethods = testMethods;
    }

    public void addTestMethod(Method test) {
        testMethods.add(test);
    }

    public void addAfterMethod(Method after) {
        afterMethods.add(after);
    }

    public void addBeforeMethod(Method before) {
        beforeMethods.add(before);
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }
}
