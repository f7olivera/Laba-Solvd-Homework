package com.solvd.itcompany.interfaces;

import com.solvd.itcompany.exceptions.NegativeAmountException;

public interface IFinance {
    public void earn(int amount) throws NegativeAmountException;
    public void spend(int amount) throws NegativeAmountException;
}
