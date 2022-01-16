package com.example.taegyung.basiccalculatorv1;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // 결과 출력창
    TextView tv_output;
    TextView tv_output_expression;

    // 연산자 버튼들
    // 없는 버튼을 추가하세요
    Button btn_question;
    Button btn_division;

    // 숫자 버튼들
    Button btn_number_2;
    Button btn_number_3;

    // 결과 출력을 위한 버튼
    Button btn_equal;

    // 표현식을 저장할 변수
    String strExpression = "";

    // 중위 표현식 저장할 변수
    PostExpression postExpression = null;

    // 표현식 최종 결과를 저장할 변수
    int result = 0;

    /**
     * onCreate : 핸드폰이 최초 생성되었을때 실행되는 메소드
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 결과 출력창
        tv_output = (TextView) findViewById(R.id.tv_output);
        tv_output_expression = (TextView) findViewById(R.id.tv_output_expression);

        // 연산자 버튼들
        btn_question = (Button) findViewById(R.id.btn_question);
        btn_division = (Button) findViewById(R.id.btn_division);

        // 숫자 버튼들
        btn_number_2 = (Button) findViewById(R.id.btn_number_2);
        btn_number_3 = (Button) findViewById(R.id.btn_number_3);

        // 결과 출력을 위한 버튼
        btn_equal = (Button) findViewById(R.id.btn_equal);

        // 중위 표현식 객체 생성
        postExpression = new PostExpression();

        // 출력창 ""으로 리셋
        btn_question.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 초기화
                strExpression = "";
                tv_output_expression.setText(strExpression);
                tv_output.setText(strExpression);

                // 토스트 메세지 출력
                Toast.makeText(getApplication(), "안녕하세요!! \n첫 심플 계산기입니다. " +
                        "<a href=\"http://www.freepik.com\">Designed by Sapann-Design / Freepik</a>", Toast.LENGTH_LONG).show();

            }
        });

        // 나누기 연산으로 세팅
        btn_division.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                // 연산자는 첫자리에 입력할 수 없
                // isValidate 함수 이용해서 아래 소스를 수정할 것
                strExpression += "/";
                tv_output.setText(strExpression);
            }
        });

        // 2로 세팅
        btn_number_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setExpression 안에서 0 한글자만 있는 표현식이면 숫자를 저장한다.
                // 아니면 숫자를 붙인다
                // 아래 소스 수정할 것
                strExpression +="2";
                tv_output.setText(strExpression);
            }
        });

        // 3로 세팅
        btn_number_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setExpression 안에서 0 한글자만 있는 표현식이면 숫자를 저장한다.
                // 아니면 숫자를 붙인다
                // 아래 소스 수정할 것
                strExpression +="3";
                tv_output.setText(strExpression);
            }
        });

        // 최종 결과 출력을 위한 버튼
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 연산자가 제일 앞에 있거나 제일 끝에 있으면 잘못된 표현식이다.
                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1 && strExpression.isEmpty() == false ) {

                    // 중위표현식을 후위표현식으로 변환
                    String strMiddleExpression = postExpression.middleToRear(strExpression);

                    // 중위표현식 -> 후위표현식으로 출력하는 창
                    tv_output_expression.setText(strMiddleExpression);

                    // 후위표현식으로 결과 계산

                    // 최종 결과 출력 ( 숫자 -> 문자열 변환 )
                    // 숫자 -> 문자열 변환 : result + ""
                    tv_output.setText(result + "");
                }
            }
        });

    }

    // 표현식에 0 숫자 하나만 있으면 0만 찍기
    // 아니면 표현식에 0을 그대로 붙이기
    // 아래 메소드 내용을 추가해서 유효성 체크할 것
    public void setExpression(String number) {

    }

    // 표현식 유효성 체크
    // 유효성 체크 후 정상이면 1
    // 에러이면 -1
    // 1) 문자열 끝에 연산자가 있으면 입력을 잘못 받았다
    // 2) 문자열 처음에 연산자가 있으면 입력을 잘못 받았다
    public int isValidate(String exp) {

        int iResult = 1;

        // 문자열 끝에 연산자가 있으면 잘못된 표현식이다
        // 아래 다른 연산자 추가해서 유효성 체크 할 것

            // Toast.maketext 이용해서 오류 메세지 날리기

        // 문자열 앞에 연산자가 있으면 잘못된 표현식이다
        // 아래 추가하여 유효성 체크 할 것

        return iResult;
    }

}
