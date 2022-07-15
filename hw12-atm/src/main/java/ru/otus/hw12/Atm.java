package ru.otus.hw12;

import java.util.HashMap;
import java.util.Map;

public class Atm {

    Map<Banknotes, Integer> containingBanknotes;
    int currentTotalAmount;

    public Atm()    {
        containingBanknotes = new HashMap<>();
    }

    public void addMoney(Banknotes banknote) {
        if (!containingBanknotes.keySet().contains(banknote)) {
            containingBanknotes.put(banknote, 1);
        }
        else {
            containingBanknotes.put(banknote, containingBanknotes.get(banknote)+1);
        }
    }

    public HashMap<Banknotes, Integer> withdrawMoney(int amount) {
        HashMap<Banknotes, Integer> banknotesToWithdraw = new HashMap<>();
        int remainingAmount = amount;

        for (Map.Entry<Banknotes, Integer> thisBanknote:
        containingBanknotes.entrySet()) {
            currentTotalAmount += thisBanknote.getKey().value * thisBanknote.getValue();
        }
        if (amount <= 0) {
            throw new RuntimeException("Incorrect amount. Please try again.");
        }
        if (amount > currentTotalAmount) {
            throw new RuntimeException("Not enough money in ATM. Please enter the amount less than "
                    + currentTotalAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._5000_RUB) && remainingAmount / 5000 != 0
                && containingBanknotes.get(Banknotes._5000_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._5000_RUB) >= remainingAmount / 5000) {
                banknotesToWithdraw.put(Banknotes._5000_RUB, remainingAmount / 5000);
            }
            else {
                banknotesToWithdraw.put(Banknotes._5000_RUB, containingBanknotes.get(Banknotes._5000_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._5000_RUB) * 5000;
            System.out.println(remainingAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._2000_RUB) && remainingAmount / 2000 != 0
                && containingBanknotes.get(Banknotes._2000_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._2000_RUB) >= remainingAmount / 2000) {
                banknotesToWithdraw.put(Banknotes._2000_RUB, remainingAmount / 2000);
            }
            else {
                banknotesToWithdraw.put(Banknotes._2000_RUB, containingBanknotes.get(Banknotes._2000_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._2000_RUB)  * 2000;
            System.out.println(remainingAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._1000_RUB) && remainingAmount / 1000 != 0
                && containingBanknotes.get(Banknotes._1000_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._1000_RUB) >= remainingAmount / 1000) {
                banknotesToWithdraw.put(Banknotes._1000_RUB, remainingAmount / 1000);
            }
            else {
                banknotesToWithdraw.put(Banknotes._1000_RUB, containingBanknotes.get(Banknotes._1000_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._1000_RUB) * 1000;
            System.out.println(remainingAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._500_RUB) && remainingAmount / 500 != 0
                && containingBanknotes.get(Banknotes._500_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._500_RUB) >= remainingAmount / 500) {
                banknotesToWithdraw.put(Banknotes._500_RUB, remainingAmount / 500);
            }
            else {
                banknotesToWithdraw.put(Banknotes._500_RUB, containingBanknotes.get(Banknotes._500_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._500_RUB) * 500;
            System.out.println(remainingAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._200_RUB) && remainingAmount / 200 != 0
                && containingBanknotes.get(Banknotes._200_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._200_RUB) >= remainingAmount / 200) {
                banknotesToWithdraw.put(Banknotes._200_RUB, remainingAmount / 200);
            }
            else {
                banknotesToWithdraw.put(Banknotes._200_RUB, containingBanknotes.get(Banknotes._200_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._200_RUB) * 200;
            System.out.println(remainingAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._100_RUB) && remainingAmount / 100 != 0
                && containingBanknotes.get(Banknotes._100_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._100_RUB) >= remainingAmount / 100) {
                banknotesToWithdraw.put(Banknotes._100_RUB, remainingAmount / 100);
            }
            else {
                banknotesToWithdraw.put(Banknotes._100_RUB, containingBanknotes.get(Banknotes._100_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._100_RUB) * 100;
            System.out.println(remainingAmount);
        }
        if (containingBanknotes.containsKey(Banknotes._50_RUB) && remainingAmount / 50 != 0
                && containingBanknotes.get(Banknotes._50_RUB) > 0) {
            if (containingBanknotes.get(Banknotes._50_RUB) >= remainingAmount / 50) {
                banknotesToWithdraw.put(Banknotes._50_RUB, remainingAmount / 50);
            }
            else {
                banknotesToWithdraw.put(Banknotes._50_RUB, containingBanknotes.get(Banknotes._50_RUB));
            }
            remainingAmount = remainingAmount - banknotesToWithdraw.get(Banknotes._50_RUB) * 50;
            System.out.println(remainingAmount);
        }
        if (remainingAmount != 0) {
            throw new RuntimeException("This sum cannot be withdrawn. Please enter the sum divisible by 50. " +
                    "Current remaining amount is " + remainingAmount);
        } else return banknotesToWithdraw;
    }

    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.addMoney(Banknotes._5000_RUB);
        atm.addMoney(Banknotes._200_RUB);
        System.out.println(atm.withdrawMoney(5107).toString());
    }
}
