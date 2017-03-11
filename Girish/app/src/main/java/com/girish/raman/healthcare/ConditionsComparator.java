package com.girish.raman.healthcare;

import com.girish.raman.healthcare.model.Condition;

import java.util.Comparator;

public class ConditionsComparator implements Comparator<Condition> {

    @Override
    public int compare(Condition o1, Condition o2) {
        if (o1.getProbability() > o2.getProbability()) return 1;
        else if (o1.getProbability() < o2.getProbability()) return -1;
        return 0;
    }
}
