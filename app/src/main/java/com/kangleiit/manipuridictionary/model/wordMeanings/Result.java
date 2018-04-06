package com.kangleiit.manipuridictionary.model.wordMeanings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naobi on 21/03/2018.
 */

public class Result {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("wordtype")
    @Expose
    private String wordtype;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("meaning_eng_man")
    @Expose
    private String meaningEngMan;
    @SerializedName("meaning_mm")
    @Expose
    private String meaningMm;
    @SerializedName("antonyms")
    @Expose
    private String antonyms;
    @SerializedName("synonyms")
    @Expose
    private String synonyms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordtype() {
        return wordtype;
    }

    public void setWordtype(String wordtype) {
        this.wordtype = wordtype;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getMeaningEngMan() {
        return meaningEngMan;
    }

    public void setMeaningEngMan(String meaningEngMan) {
        this.meaningEngMan = meaningEngMan;
    }

    public String getMeaningMm() {
        return meaningMm;
    }

    public void setMeaningMm(String meaningMm) {
        this.meaningMm = meaningMm;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

}
