package ru.otus;

import java.lang.reflect.Method;


public class MyJUnit {

    private TestMethods getAnnotatedMethods(Class clazz) {
        TestMethods methods = new TestMethods();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Before.class))
                methods.addBeforeMethod(m);
            if (m.isAnnotationPresent(After.class))
                methods.addAfterMethod(m);
            if (m.isAnnotationPresent(Test.class))
                methods.addTestMethod(m);
        }
        return methods;
    }

    public void runTests(Class clazz) {
        TestMethods methods = getAnnotatedMethods(clazz);
        int succeedTestsCount = 0;
        int failedTestsCount = 0;
        for (Method eachTestMethod : methods.getTestMethods()) {
            System.out.println("Starting testing method" + eachTestMethod.getName() + "...");
            try {
                Object obj = clazz.getConstructor().newInstance();
                for (Method eachBeforeMethod : methods.getBeforeMethods()) {
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
                for (Method eachAfterMethod : methods.getAfterMethods()) {
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
        printStatistics(methods, succeedTestsCount, failedTestsCount);
    }

    private void printStatistics(TestMethods methods, int succeedTestsCount, int failedTestsCount) {
        System.out.println("-----------------------------------------");
        System.out.println("TESTING RESULTS:");
        System.out.println("Total tests done: " + methods.getTestMethods().size());
        System.out.println("Successful tests: " + succeedTestsCount);
        System.out.println("Failed tests: " + failedTestsCount);
    }
}
