package ru.otus.hw12;

public class AtmDemo {
    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.addMoney(Banknotes._200_RUB);
        atm.addMoney(Banknotes._50_RUB);
        atm.addMoney(Banknotes._200_RUB);
        atm.addMoney(Banknotes._500_RUB);
        atm.addMoney(Banknotes._200_RUB);
        atm.addMoney(Banknotes._200_RUB);
        atm.addMoney(Banknotes._5000_RUB);
        atm.addMoney(Banknotes._200_RUB);

        System.out.println(atm.withdrawMoney(5250).toString());

    }
}
