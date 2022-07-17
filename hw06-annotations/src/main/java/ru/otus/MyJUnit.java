package ru.otus;

import java.lang.reflect.InvocationTargetException;
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

    private void invokeMethod(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(obj, null);
    }

    public void runTests(Class clazz) {
        TestMethods methods = getAnnotatedMethods(clazz);
        int succeedTestsCount = 0;
        int failedTestsCount = 0;

        for (Method testMethod : methods.getTestMethods()) {
            Object obj = createClazzInstance(clazz);
            if (obj == null) {
                throw new RuntimeException("Object not created.");
            }
            System.out.println("Starting testing method" + testMethod.getName() + "...");
            try {
                boolean beforeTestsSuccessful = runBeforeMethods(methods, obj);
                if(!beforeTestsSuccessful) continue;
                invokeMethod(testMethod, obj);

                System.out.println("Testing of method " + testMethod.getName() + " finished successfully!");
                succeedTestsCount++;
            }
            catch (Exception e) {
                failedTestsCount++;
                System.out.println("Test of " + testMethod.getName() + " FAILED");
            }
            finally {
                runAfterMethods(methods, obj);
            }
        }
        printStatistics(methods, succeedTestsCount, failedTestsCount);
    }

    private Object createClazzInstance(Class clazz) {
        Object obj = null;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    private void runAfterMethods(TestMethods methods, Object obj) {
        for (Method afterMethod : methods.getAfterMethods()) {
            try {
                invokeMethod(afterMethod, obj);
            }
            catch (Exception e) {
                System.out.println("@After test " + afterMethod.getName() + " FAILED");
            }
        }
    }

    private boolean runBeforeMethods(TestMethods methods, Object obj) {
        boolean beforeMethodsSuccessful = true;
        for (Method beforeMethod : methods.getBeforeMethods()) {
            try {
                invokeMethod(beforeMethod, obj);
            }
            catch (Exception e) {
                System.out.println("@Before test " + beforeMethod.getName() + " FAILED");
                beforeMethodsSuccessful = false;
                break;
            }
        }
        return beforeMethodsSuccessful;
    }

    private void printStatistics(TestMethods methods, int succeedTestsCount, int failedTestsCount) {
        System.out.println("-----------------------------------------");
        System.out.println("TESTING RESULTS:");
        System.out.println("Total tests done: " + methods.getTestMethods().size());
        System.out.println("Successful tests: " + succeedTestsCount);
        System.out.println("Failed tests: " + failedTestsCount);
    }
}
