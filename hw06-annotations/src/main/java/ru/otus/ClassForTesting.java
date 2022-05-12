package ru.otus;


public class ClassForTesting {
    @Before
    private void beforeMethodOne() {
        System.out.println("Successful @Before method");
    }

    @Before
    public void beforeMethodTwo() {
        System.out.println("Failing @Before method");
        throw new RuntimeException();
    }

    @Test
    private void testMethodOne() {
        System.out.println("Successful @Test method");
    }

    @Test
    public void testMethodTwo() {
        System.out.println("Failing @Test method");
        throw new RuntimeException();
    }
    @After
    private void afterMethodOne() {
        System.out.println("Successful @After method");
    }

    @After
    public void afterMethodTwo() {
        System.out.println("Failing @After method");
        throw new RuntimeException();
    }

}
