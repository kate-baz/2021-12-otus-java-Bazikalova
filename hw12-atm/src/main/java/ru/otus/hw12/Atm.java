package ru.otus.hw12;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Atm {

    private Map<Banknotes, Integer> containingBanknotes;

    public Atm()    {
        containingBanknotes = new HashMap<>();
    }

    public void addMoney(Banknotes banknote) {
        if (!containingBanknotes.containsKey(banknote)) {
            containingBanknotes.put(banknote, 1);
        }
        else {
            containingBanknotes.put(banknote, containingBanknotes.get(banknote)+1);
        }
    }

    public HashMap<Banknotes, Integer> withdrawMoney(int amount) {
        HashMap<Banknotes, Integer> banknotesToWithdraw = new HashMap<>();
        int currentTotalAmount = 0;
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
        List<Banknotes> sortedBanknotes = containingBanknotes.keySet()
                .stream()
                .sorted(Comparator.comparingInt(o -> ((Banknotes)o).value).reversed())
                .toList();
        for (Banknotes banknote: sortedBanknotes) {
            int availableBanknotes = containingBanknotes.get(banknote);
            int maxBanknote = remainingAmount / banknote.value;
            int banknoteCount = Math.min(availableBanknotes, maxBanknote);
            if (banknoteCount > 0) {
                banknotesToWithdraw.put(banknote, banknoteCount);
                remainingAmount -= banknote.value * banknoteCount;
            }
        }
        if (remainingAmount != 0) {
            throw new RuntimeException("This sum cannot be withdrawn. " +
                    "Current remaining amount is " + remainingAmount);
        } else {
            for (Banknotes withdrawnBanknote : banknotesToWithdraw.keySet()) {
                int oldBanknoteCount = containingBanknotes.get(withdrawnBanknote);
                int newBanknoteCount = oldBanknoteCount - banknotesToWithdraw.get(withdrawnBanknote);
                containingBanknotes.put(withdrawnBanknote, newBanknoteCount);
            }
            return banknotesToWithdraw;
        }
    }
}
