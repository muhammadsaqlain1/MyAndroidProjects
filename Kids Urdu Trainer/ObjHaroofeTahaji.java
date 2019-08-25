package com.example.mcs.kidsurdutrainer4;

/**
 * Created by MCS on 3/18/2018.
 */

public class ObjHaroofeTahaji {
    int id;
    String harf,word1,picture1,word2,picture2,word3,picture3;

    public ObjHaroofeTahaji(int id, String harf, String word1, String picture1, String word2, String picture2, String word3, String picture3) {
        this.id = id;
        this.harf = harf;
        this.word1 = word1;
        this.picture1 = picture1;
        this.word2 = word2;
        this.picture2 = picture2;
        this.word3 = word3;
        this.picture3 = picture3;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHarf() {
        return harf;
    }

    public void setHarf(String harf) {
        this.harf = harf;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getWord3() {
        return word3;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }
}
