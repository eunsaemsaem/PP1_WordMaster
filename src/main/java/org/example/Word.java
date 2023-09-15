package org.example;

public class Word {

    private int id;
    private int level;
    private String word;
    private String meaning;

    Word(){}
    Word (int id, int level, String word, String meaning) {
        this.id = id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public java.lang.String toString() { //숫자, 난이도 별표시, 영단어 오른쪽 정렬, 한글 뜻 왼쪽 정렬

        String slevel = "";
        for (int i = 0; i < level; i++) {
            slevel += "*";
        }

        String str = String.format("%-3s", slevel) + String.format("%15s", word) +"  "+ meaning; //양수 = 오른쪽 정렬 (음수 = 왼쪽 정렬)

        return str;
    }

    public String toFileString() {
        return this.level + "," + this.word + "," + this.meaning;
    }
}
