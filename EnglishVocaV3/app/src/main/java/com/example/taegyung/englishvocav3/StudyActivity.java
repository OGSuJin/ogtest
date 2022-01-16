package com.example.taegyung.englishvocav3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudyActivity extends AppCompatActivity {

    // SQLite DB
    MyDBHelper m_helper;

    // 파일 : 영어 문제(raw/hight01.txt ~ raw/hight08.txt)
    FileTable mFile;

    // 음악 파일 변수
    static SoundPool sdPool;
    static float iSoundVolumn = 1.0f; // 볼륨 ( 0.0f ~ 1.0f )
    static int iDingDongDaeng = 0;    // 딩동댕 소리ID
    static int iTaeng = 0;            // 땡 소리ID

    // 버튼 상단 화면 정의
    Button btnPreview;
    Button btnNext;
    Button btnWordSelected;
    Button btnMyNote;
    Button btnRandom;
    Button btnExit;

    // 버튼 하단 문제화면 정의
    Button btnSubQuestion1;
    Button btnSubQuestion2;
    Button btnSubQuestion3;
    Button btnSubQuestion4;
    Button btnSaveWord;

    // 텍스트뷰 하단 문제 정의
    TextView tvwQuestionCaption;
    TextView tvwQuestion;
    TextView tvwSubQuestion1;
    TextView tvwSubQuestion2;
    TextView tvwSubQuestion3;
    TextView tvwSubQuestion4;

    // 변수 초기화
    int iQuestionNumber = 0;     // 문제번호
    int iMaxQuestionNum = 99;    // 문제수
    int iAnswerButton = 0;       // 답 선택
    int iSelectedAnswer = 0;     // 답 선택 번호
    int iCorrectNumber = 0;      // 맞힌 개수
    int iWrongNumber = 0;        // 틀린 개수
    int iMyNoteVisible = 0;              // 내 노트 버튼 보이기 여부

    // 사운드 변수
    static int iSoundOk = 1;

    // 최초 문제 번호
    int subNumber=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        // testforeng.db 데이터베이스  생성
        m_helper = new MyDBHelper(getApplicationContext(), "testforeng.db", null, 1);

        // 파일 생성
        mFile = new FileTable();

        // 효과음 초기화
        sdPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        // 딩동댕 소리
        iDingDongDaeng = sdPool.load(getApplicationContext(), R.raw.dingdongdaeng, 1);
        // 땡 소리
        iTaeng = sdPool.load(getApplicationContext(), R.raw.taeng, 2);

        // 버튼 위쪽 화면 연결
        btnPreview = (Button)findViewById(R.id.btnPreview);
        btnNext  = (Button)findViewById(R.id.btnNext);
        btnWordSelected = (Button)findViewById(R.id.btnWordSelected);
        btnMyNote = (Button)findViewById(R.id.btnMyNote);
        btnRandom = (Button)findViewById(R.id.btnRandom);
        btnExit = (Button)findViewById(R.id.btnExit);

        // 버튼 아래 화면 연결
        btnSubQuestion1 = (Button)findViewById(R.id.btnSubQuestion1);
        btnSubQuestion2 = (Button)findViewById(R.id.btnSubQuestion2);
        btnSubQuestion3 = (Button)findViewById(R.id.btnSubQuestion3);
        btnSubQuestion4 = (Button)findViewById(R.id.btnSubQuestion4);
        btnSaveWord = (Button)findViewById(R.id.btnSaveWord);

        // 텍스트뷰 화면 연결
        tvwQuestionCaption = (TextView)findViewById(R.id.tvwQuestionCaption);
        tvwQuestion = (TextView)findViewById(R.id.tvwQuestion);
        tvwSubQuestion1 = (TextView)findViewById(R.id.tvwSubQuestion1);
        tvwSubQuestion2 = (TextView)findViewById(R.id.tvwSubQuestion2);
        tvwSubQuestion3 = (TextView)findViewById(R.id.tvwSubQuestion3);
        tvwSubQuestion4 = (TextView)findViewById(R.id.tvwSubQuestion4);

        // 파일에서 읽어서 문제 만들기
        makeQuestion(subNumber);
        // 파일에서 문제 읽어서 텍스트뷰에 최초로 보여주기
        ShowQuestion(iQuestionNumber);

        // btnPreview 클릭했을 때
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iQuestionNumber > 0 && iQuestionNumber < iMaxQuestionNum) {
                    iQuestionNumber -= 1;

                    // 단어장 등록 버튼 안보이게 취급
                    btnSaveWord.setVisibility(view.GONE);

                    // 파일에서 문제 불러와서 텍스트뷰에 다시 보여주기
                    ShowQuestion(iQuestionNumber);
                }
            }
        });

        // btnNext 클릭했을 때
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iQuestionNumber >= 0 && iQuestionNumber < iMaxQuestionNum) {
                    iQuestionNumber += 1;

                    // 디버그 시 로그 보기
                    if (BuildConfig.DEBUG ) {

                        Log.d("btnNext : ", "" + iQuestionNumber);
                        Log.d("btnNext : ", "" + BuildConfig.DEBUG);
                    }

                    // 단어장 등록 버튼 안보이게 취급
                    btnSaveWord.setVisibility(view.GONE);

                    // 파일에서 문제 불러와서 텍스트뷰에 다시 보여주기
                    ShowQuestion(iQuestionNumber);
                }
            }
        });

        // btnWordSelected 클릭했을 때
        btnWordSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // btnMyNote 클릭했을 때
        btnMyNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 단어장 화면 호출
                startActivity(new Intent(getApplicationContext(), NoteActivity.class));

            }
        });

        // btnRandom 클릭했을 때
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 램덤 초기화
                double rand = Math.random();

                // 단어장 등록 버튼 안보이게 취급
                btnSaveWord.setVisibility(view.GONE);

                // 랜덤 범위를 위한 변수 정의
                // 1 ~ iMaxQuestionNum 까지
                int min = 1;                   // 최소 범위값
                int max = iMaxQuestionNum;    // 최대 범위값
                int interval = max - min + 1;  // 간격

                // 랜덤 문제번호 숫자
                // 1 ~ iMaxQuestionNum 까지
                iQuestionNumber = (int) ((rand * interval) + min );

                // 파일에서 문제 불러와서 텍스트뷰에 다시 보여주기
                ShowQuestion(iQuestionNumber);
            }
        });

        // 나가기 버튼
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // StudyActivity 화면 종료
                //System.exit(0);
                finish();
            }
        });

        btnSubQuestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 선택된 답
                iSelectedAnswer = 1;

                // 단어장 등록 버튼 보이기
                btnSaveWord.setVisibility(view.VISIBLE);

                // 문제 파일에서 정답 번호를 가져온다.
                int solnumer = Integer.parseInt(FileSplit0.questionNum[iQuestionNumber][6].trim());

                if (solnumer == iSelectedAnswer) {
                    if (iSoundOk == 1) {
                        // 효과음 재생
                        sdPool.play(iDingDongDaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 맞힌 개수 증가
                        iCorrectNumber++;
                    }
                    else {
                        // 효과음 재생
                        sdPool.play(iTaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 틀린 개수 증가
                        iWrongNumber++;
                    }

                }
            }
        });

        btnSubQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 선택된 답
                iSelectedAnswer = 2;

                // 단어장 등록 버튼 보이기
                btnSaveWord.setVisibility(view.VISIBLE);

                // 문제 파일에서 정답 번호를 가져온다.
                int solnumer = Integer.parseInt(FileSplit0.questionNum[iQuestionNumber][6].trim());

                if (solnumer == iSelectedAnswer) {
                    if (iSoundOk == 1) {
                        // 효과음 재생
                        sdPool.play(iDingDongDaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 맞힌 개수 증가
                        iCorrectNumber++;
                    }
                    else {
                        // 효과음 재생
                        sdPool.play(iTaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 틀린 개수 증가
                        iWrongNumber++;
                    }

                }
            }
        });

        btnSubQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 선택된 답
                iSelectedAnswer = 3;

                // 단어장 등록 버튼 보이기
                btnSaveWord.setVisibility(view.VISIBLE);

                // 문제 파일에서 정답 번호를 가져온다.
                int solnumer = Integer.parseInt(FileSplit0.questionNum[iQuestionNumber][6].trim());

                if (solnumer == iSelectedAnswer) {
                    if (iSoundOk == 1) {
                        // 효과음 재생
                        sdPool.play(iDingDongDaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 맞힌 개수 증가
                        iCorrectNumber++;
                    }
                    else {
                        // 효과음 재생
                        sdPool.play(iTaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 틀린 개수 증가
                        iWrongNumber++;
                    }

                }

            }
        });

        btnSubQuestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 선택된 답
                iSelectedAnswer = 4;

                // 단어장 등록 버튼 보이기
                btnSaveWord.setVisibility(view.VISIBLE);

                // 문제 파일에서 정답 번호를 가져온다.
                int solnumer = Integer.parseInt(FileSplit0.questionNum[iQuestionNumber][6].trim());

                if (solnumer == iSelectedAnswer) {
                    if (iSoundOk == 1) {
                        // 효과음 재생
                        sdPool.play(iDingDongDaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 맞힌 개수 증가
                        iCorrectNumber++;
                    }
                    else {
                        // 효과음 재생
                        sdPool.play(iTaeng, iSoundVolumn, iSoundVolumn, 9, 0, 1);

                        // 틀린 개수 증가
                        iWrongNumber++;
                    }

                }

            }
        });

        // 단어장 등록
        btnSaveWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 문제 파일에서 정답 번호를 가져온다.
                int solnumer = Integer.parseInt(FileSplit0.questionNum[iQuestionNumber][6].trim());

                // 데이터베이스 연결
                SQLiteDatabase db = m_helper.getWritableDatabase();

                // 영어 단어
                String engword = FileSplit0.questionNum[iQuestionNumber][solnumer + 1];
                String hanword = FileSplit0.questionNum[iQuestionNumber][1];

                // db에 단어 추가
                m_helper.onInsert(db, engword, hanword);

                // db 닫기
                db.close();

                // 토스트 저장 메세지 보이기
                Toast.makeText(getApplicationContext(), "단어가 저장되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });

    }

    // 문제 화면에 보여주기 메소드
    public void ShowQuestion(int iQueNum) {
        // 파일에서 문제 불러와서 텍스트뷰에 보여주기
        tvwQuestionCaption.setText(FileSplit0.questionNum[iQueNum][0]);      // 문제번호
        tvwQuestion.setText(" : " + FileSplit0.questionNum[iQueNum][1]);     // 문제
        tvwSubQuestion1.setText(FileSplit0.questionNum[iQueNum][2]);         // 1번 보기
        tvwSubQuestion2.setText(FileSplit0.questionNum[iQueNum][3]);         // 2번 보기
        tvwSubQuestion3.setText(FileSplit0.questionNum[iQueNum][4]);         // 3번 보기
        tvwSubQuestion4.setText(FileSplit0.questionNum[iQueNum][5]);         // 4번 보기

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "sound on");
        menu.add(0, 2, 0, "sound off");
        menu.add(0, 3, 0, "");
        menu.add(0, 4, 0, "");
        return true;
    }

    //-------------------------------------
    //  onOptions ItemSelected
    //-------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
//			StudyView.soundOk=1;
                break;
            case 2:
//			StudyView.soundOk=0;
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //System.exit(0);   //메인화면으로 돌아가기
            finish();
            return false;
        }

        return false;
    }

    public void makeQuestion(int x) {
        mFile.loadFile(getApplicationContext(), x);
    }

}

