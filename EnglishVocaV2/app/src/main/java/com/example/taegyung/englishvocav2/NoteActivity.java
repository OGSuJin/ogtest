package com.example.taegyung.englishvocav2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by TaeGyung on 2022-01-14.
 */

public class NoteActivity extends AppCompatActivity {

    // SQLite DB
    // testforeng.db 데이터베이스  생성 또는 연결
    MyDBHelper m_helper;

    // 페이지 넘버
    int iPageNumber = 1;

    // 전 , 후 페이지 버튼
    Button btnPreviousPage;

    // 닫기 버튼

    // 단어카운트 텍스트뷰 정의
//    TextView tvwWordCount;

    // 리스트 뷰 관련 내용
    ListView lvwSavedWord;
    NoteItemAdapter aarAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // 전, 후 페이지 버튼 연결
        btnPreviousPage = (Button)findViewById(R.id.btnPreviousPage);

        // 닫기 버튼 연결

        // testforeng.db 데이터베이스  생성 또는 연결
        m_helper = new MyDBHelper(getApplicationContext(), "testforeng.db", null, 1);

        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        aarAdapter = new NoteItemAdapter() ;

        // listview 생성 및 adapter 지정.
        lvwSavedWord = (ListView) findViewById(R.id.lvwSavedWord);
        lvwSavedWord.setAdapter(aarAdapter) ;

        showMyNote(iPageNumber);

        // 전 버튼 클릭 이벤트 생성
        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iPageNumber -=1;

                // 페이지 감소
                if (iPageNumber < 1 ) {
                    iPageNumber = 1;
                }

                if (iPageNumber > 0 && iPageNumber < 99) {
                    showMyNote(iPageNumber);
                }
            }
        });

    }

    /**
     * 단어장 데이터 보이기
     */
    public void showMyNote(int pagenumber) {

        // db 읽어오기


        // 등록된 건수

        // 어댑터 초기화
        aarAdapter.clearItem();

        // db 내용 출력

            // db 단어 내용 출력


        // listview 갱신

        // 커서 닫기

        // db 닫기
    }

    /**
     * 자체 화면 갱신
     */
    public void refreshActivity() {
        Intent intent = getIntent();

        finish();
        startActivity(intent);
    }
}
