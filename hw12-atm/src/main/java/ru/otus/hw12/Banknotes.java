package ru.otus.hw12;

public enum Banknotes {
    _50_RUB(50),
    _100_RUB(100),
    _200_RUB(200),
    _500_RUB(500),
    _1000_RUB(1000),
    _2000_RUB(2000),
    _5000_RUB(5000);

    public int value;

    Banknotes(int value){
        this.value = value;
    }
}
