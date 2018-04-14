package com.kangleiit.manipuridictionary.ui.search;

import java.util.ArrayList;

/**
 * Created by Win 7 on 4/10/2018.
 */

public class SectionModel { private String sectionLabel;
    private ArrayList<String> itemArrayList;

    public SectionModel(String sectionLabel, ArrayList<String> itemArrayList) {
        this.sectionLabel = sectionLabel;
        this.itemArrayList = itemArrayList;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }

    public ArrayList<String> getItemArrayList() {
        return itemArrayList;
    }
}