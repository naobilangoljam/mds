package com.kangleiit.manipuridictionary.model.InputResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naobi on 21/03/2018.
 */

public class Result {
    @SerializedName("word")
    @Expose
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
