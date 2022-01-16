package com.example.taegyung.englishvocav3;

/** DB와 화면간의 데이터를 전송하고 전송받을 클래스 정의
 * Created by TaeGyung on 2022-01-14.
 */

public class MyNoteCommand {

    long lId;
    String strEngishWord;
    String strKoreanWord;

    public MyNoteCommand(long lId, String strEngishWord, String strKoreanWord) {
        this.lId = lId;
        this.strEngishWord = strEngishWord;
        this.strKoreanWord = strKoreanWord;
    }

    public long getlId() {
        return lId;
    }

    public void setlId(long lId) {
        this.lId = lId;
    }

    public String getStrEngishWord() {
        return strEngishWord;
    }

    public void setStrEngishWord(String strEngishWord) {
        this.strEngishWord = strEngishWord;
    }

    public String getStrKoreanWord() {
        return strKoreanWord;
    }

    public void setStrKoreanWord(String strKoreanWord) {
        this.strKoreanWord = strKoreanWord;
    }

}
