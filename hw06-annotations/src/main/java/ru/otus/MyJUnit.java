package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyJUnit {
    private static List<Method> beforeTests = new ArrayList<>();
    private static List<Method> afterTests = new ArrayList<>();
    private static List<Method> testMethods = new ArrayList<>();
    private static int failedTestsCount;
    private static int succeedTestsCount;

    private static void getAnnotatedMethods(Class clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Before.class))
                beforeTests.add(m);
            if (m.isAnnotationPresent(After.class))
                afterTests.add(m);
            if (m.isAnnotationPresent(Test.class))
                testMethods.add(m);
        }
    }

    public void runTests(Class clazz) {
        getAnnotatedMethods(clazz);
        for (Method eachTestMethod : testMethods) {
            System.out.println("Starting testing method" + eachTestMethod.getName() + "...");
            try {
                Object obj = clazz.getConstructor().newInstance();
                for (Method eachBeforeMethod : beforeTests) {
                    try {
                        eachBeforeMethod.setAccessible(true);
                        eachBeforeMethod.invoke(obj, null);
                    }
                    catch (Exception e) {
                        System.out.println("@Before test " + eachBeforeMethod.getName() + " FAILED");
                    }
                }
                eachTestMethod.setAccessible(true);
                eachTestMethod.invoke(obj, null);
                System.out.println("Testing of method " + eachTestMethod.getName() + " finished successfully!");
                succeedTestsCount++;
                for (Method eachAfterMethod : afterTests) {
                    try {
                        eachAfterMethod.setAccessible(true);
                        eachAfterMethod.invoke(obj, null);
                    }
                    catch (Exception e) {
                        System.out.println("@After test " + eachAfterMethod.getName() + " FAILED");
                    }
                }
            }
            catch (Exception e) {
                failedTestsCount++;
                System.out.println("Test of " + eachTestMethod.getName() + " FAILED");
            }
        }
        printStatistics();
    }

    private static void printStatistics() {
        System.out.println("-----------------------------------------");
        System.out.println("TESTING RESULTS:");
        System.out.println("Total tests done: " + testMethods.size());
        System.out.println("Successful tests: " + succeedTestsCount);
        System.out.println("Failed tests: " + failedTestsCount);
    }
}
