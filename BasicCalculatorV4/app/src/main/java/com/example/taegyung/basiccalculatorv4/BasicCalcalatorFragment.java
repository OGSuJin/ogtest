package com.example.taegyung.basiccalculatorv4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

// 중복 클릭 방지용 추상클래스 생성
// View.OnClickListener 대신 사용
abstract class OnSingleClickListener implements View.OnClickListener{

    //중복 클릭 방지 시간 설정 ( 해당 시간 이후에 다시 클릭 가능 )
    private static final long MIN_CLICK_INTERVAL = 200;
    private long mLastClickTime = 0;

    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {
        // 컴퓨터 부팅된 후 경과시간(밀리초) : elapsedRealtime
        long currentClickTime = SystemClock.elapsedRealtime();
        long elapsedTime = currentClickTime - mLastClickTime;
        mLastClickTime = currentClickTime;

        // 중복클릭 아닌 경우
        // 중복클릭 이면 skip
        if (elapsedTime > MIN_CLICK_INTERVAL) {
            onSingleClick(v);
        }
    }
}

/**
 * Created by TaeGyung on 2021-12-26.
 */

public class BasicCalcalatorFragment extends Fragment {


    // context 변수 생성
    private Context context;

    // 결과 출력창
    TextView tv_output;
    TextView tv_output_expression;

    Button btn_ac;
    Button btn_question;
    Button btn_pecent;
    Button btn_division;

    Button btn_number_7;
    Button btn_number_8;
    Button btn_number_9;
    Button btn_multiplication;

    Button btn_number_4;
    Button btn_number_5;
    Button btn_number_6;
    Button btn_subtract;

    Button btn_number_1;
    Button btn_number_2;
    Button btn_number_3;
    Button btn_plus;

    Button btn_number_0;
    Button btn_period;
    Button btn_equal;

    // 표현식을 저장할 변수
    String strExpression = "";

    // 표현식 최종 결과를 저장할 변수
    double result = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = container.getContext();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_basiccalcalator, container, false);

        tv_output = (TextView) rootView.findViewById(R.id.tv_output);
        tv_output_expression = (TextView) rootView.findViewById(R.id.tv_output_expression);

        btn_ac = (Button) rootView.findViewById(R.id.btn_ac);
        btn_question = (Button) rootView.findViewById(R.id.btn_question);
        btn_pecent = (Button) rootView.findViewById(R.id.btn_pecent);
        btn_division = (Button) rootView.findViewById(R.id.btn_division);

        btn_number_7 = (Button) rootView.findViewById(R.id.btn_number_7);
        btn_number_8 = (Button) rootView.findViewById(R.id.btn_number_8);
        btn_number_9 = (Button) rootView.findViewById(R.id.btn_number_9);
        btn_multiplication = (Button) rootView.findViewById(R.id.btn_multiplication);

        btn_number_4 = (Button) rootView.findViewById(R.id.btn_number_4);
        btn_number_5 = (Button) rootView.findViewById(R.id.btn_number_5);
        btn_number_6 = (Button) rootView.findViewById(R.id.btn_number_6);
        btn_subtract = (Button) rootView.findViewById(R.id.btn_subtract);

        btn_number_1 = (Button) rootView.findViewById(R.id.btn_number_1);
        btn_number_2 = (Button) rootView.findViewById(R.id.btn_number_2);
        btn_number_3 = (Button) rootView.findViewById(R.id.btn_number_3);
        btn_plus = (Button) rootView.findViewById(R.id.btn_plus);

        btn_number_0 = (Button) rootView.findViewById(R.id.btn_number_0);
        btn_period = (Button) rootView.findViewById(R.id.btn_period);
        btn_equal = (Button) rootView.findViewById(R.id.btn_equal);

        // 출력창 ""으로 리셋
        btn_question.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                // 초기화
//                strExpression = "";
//                tv_output_expression.setText(strExpression);
//                tv_output.setText(strExpression);
//
//
//                // 토스트 메세지 출력
//                Toast.makeText(getApplication(), "안녕하세요!! \n첫 심플 계산기 업그레이드 버전입니다. \n많이 사랑해 주세요\n " +
//                        "<a href=\"http://www.freepik.com\">Designed by Sapann-Design / Freepik</a>", Toast.LENGTH_LONG).show();

                // 두번째 화면으로 이동한다.
                Intent intent1 = new Intent(context, ShowMe.class);
                startActivity(intent1);
            }
        });

        // 출력창 ""으로 리셋
        btn_ac.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                // 초기화
                strExpression = "";
                tv_output_expression.setText(strExpression);
                tv_output.setText(strExpression);
            }

        });

        // 나머지 연산으로 세팅
        btn_pecent.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1) {

                    // 연산자는 첫자리에 입력할 수 없다
                    if (strExpression.isEmpty() == false) {
                        strExpression += "%";
                        tv_output.setText(strExpression);
                    }
                }
            }
        });

        // 나누기로 세팅
        btn_division.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1) {

                    // 연산자는 첫자리에 입력할 수 없다
                    if (strExpression.isEmpty() == false) {
                        strExpression += "/";
                        tv_output.setText(strExpression);
                    }
                }
            }
        });

        // 7로 세팅
        btn_number_7.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("7");
                tv_output.setText(strExpression);
            }
        });

        // 8로 세팅
        btn_number_8.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("8");
                tv_output.setText(strExpression);
            }
        });

        // 9로 세팅
        btn_number_9.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("9");
                tv_output.setText(strExpression);
            }
        });

        // *로 세팅
        btn_multiplication.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1) {

                    // 연산자는 첫자리에 입력할 수 없다
                    if (strExpression.isEmpty() == false) {
                        strExpression += "*";
                        tv_output.setText(strExpression);
                    }
                }
            }
        });

        // 4로 세팅
        btn_number_4.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("4");
                tv_output.setText(strExpression);
            }
        });

        // 5로 세팅
        btn_number_5.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("5");
                tv_output.setText(strExpression);
            }
        });

        // 6로 세팅
        btn_number_6.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("6");
                tv_output.setText(strExpression);
            }
        });

        // -로 세팅
        btn_subtract.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1) {

                    // 연산자는 첫자리에 입력할 수 없다
                    if (strExpression.isEmpty() == false) {
                        strExpression += "-";
                        tv_output.setText(strExpression);
                    }
                }
            }
        });

        // 1로 세팅
        btn_number_1.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("1");
                tv_output.setText(strExpression);
            }
        });

        // 72로 세팅
        btn_number_2.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("2");
                tv_output.setText(strExpression);
            }
        });

        // 3로 세팅
        btn_number_3.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("3");
                tv_output.setText(strExpression);
            }
        });

        // +로 세팅
        btn_plus.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1) {

                    // 연산자는 첫자리에 입력할 수 없다
                    if (strExpression.isEmpty() == false) {
                        strExpression += "+";
                        tv_output.setText(strExpression);
                    }
                }

            }
        });

        // 0로 세팅
        btn_number_0.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                // setter 안에서 0만 있는 문자인지 체크해서 저장한다.
                setExpression("0");
                tv_output.setText(strExpression);
            }
        });

        // .로 세팅
        btn_period.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1) {

                    // 소수점은 첫자리에 입력할 수 없다
                    if (strExpression.isEmpty() == false) {

                        strExpression += ".";
                        tv_output.setText(strExpression);
                    }
                }
            }
        });

        // 최종 결과 출력
        btn_equal.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {

                // 표현식 유효성 체크
                if (isValidate(strExpression) == 1 && strExpression.isEmpty() == false ) {

                    // 중위표현식을 후위표현식으로 변환
                    String strMiddleExpression = middleToRear(strExpression);

                    // 후위표현식으로 결과 계산
                    result = postCalculator(strMiddleExpression);

                    // 중위표현식 -> 후위표현식으로 출력하는 창
                    tv_output_expression.setText(strMiddleExpression);

                    // 최종 결과 출력 ( 숫자 -> 문자열 변환 )
                    tv_output.setText(result + "");
                }

            }
        });

        return rootView;
    }

    // 표현식에 0만 있는지 체크
    public void setExpression(String number) {

        if (!strExpression.equals("0")) {
            strExpression += number;
        } else {
            strExpression = number;
        }

    }


    // 표현식 유효성 체크
    public int isValidate(String exp) {

        int iResult = 1;

        // 문자열 끝에 연산자가 있으면 잘못된 표현식이다
        if ( exp.endsWith("+") || exp.endsWith("-") || exp.endsWith("*") || exp.endsWith("%") || exp.endsWith("/") || exp.endsWith(".")) {

            iResult = -1;

            // 오류 메세지 날리기
            Toast.makeText(context, "잘못된 식입니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();

            // 입력창 초기화
            //tv_output.setText("");
        }
        // 문자열 앞에 연산자가 있으면 잘못된 표현식이다
        else if ( exp.startsWith("+") || exp.startsWith("-") || exp.startsWith("*") || exp.startsWith("%") || exp.startsWith("/") || exp.startsWith(".") ) {

            iResult = -1;

            // 오류 메세지 날리기
            Toast.makeText(context, "잘못된 식입니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
        }

        return iResult;
    }

    // 연산자 우선순위 정의하는 메소드
    public int getOpPriority(char op) {

        if (op == '*' || op == '/' || op == '%') {
            return 3;
        }
        else if (op == '+' || op == '-'){
            return 2;
        }
        else if (op == '(') {
            return 1;
        }
        return -1;
    }

    // 연산자 우선순위 실제로 비교하는 메소드
    public boolean compareToPriority(char op1, char op2) {

        int opPriority1 = getOpPriority(op1);
        int opPriority2 = getOpPriority(op2);

        if(opPriority1 >= opPriority2) {
            return true;
        }
        else {
            return false;
        }
    }


    public String setSpace( String exp ) {

        String res = "";

        // 두자리 수 계산을 위해 공백  추가
        // 만일 문자열 앞에 이미 공백이 추가되어 있으면 추가하지 않는다
        if ( !exp.endsWith(" ")) {
            res = " ";
        }

        return res;
    }

    public String middleToRear(String exp) {

        Stack<Character> stack = new Stack<>();
        String postExpression = "";
        int cnt = 0;

        // 문자열이 끝에 도달할때 까지 체크하여 후위표현식으로 변환한다.
        while (cnt <  exp.length()) {

            char chrExp = exp.charAt(cnt);
            char chrPreExp = ' ';

            if (cnt -1 >= 0) {
                chrPreExp = exp.charAt(cnt - 1);
            }

            // 숫자이면 (피연산자이면) 후위표현식 문자열에 저장
            // 두자리 이상일 경우도 반영
            if (chrExp >= '0' && chrExp <= '9' || chrExp == '.' ) {

                if (chrPreExp == '+' || chrPreExp == '-' || chrPreExp == '*' || chrPreExp == '/' ||  chrPreExp == '%' || chrPreExp == '(') {

                    // 두자리 수 계산을 위해 공백  추가
                    postExpression += setSpace(postExpression);
                }

                postExpression += chrExp;

            }
            else {
                // 연산자이면
                // 두자리이상 숫자를 위해 공백을 하나 추가한다.
                postExpression += setSpace(postExpression);

                // 닫는 괄호가 나오면 여는 괄호가 나올 때까지 꺼내서 출력합니다.
                if (chrExp == ')') {
                    while (true) {
                        char chrPeek = (char)stack.pop();
                        if (chrPeek == '(') {
                            break;
                        }
                        // 두자리이상 숫자를 위해 연산자 앞에 공백을 추가한다.
                        postExpression += setSpace(postExpression);
                        postExpression += chrPeek;
                    }
                }
                // 연산자는 스택이 비어있으면 자신을 바로 추가합니다.
                else if (chrExp == '+' || chrExp == '-' || chrExp == '*' || chrExp == '/' ||  chrExp == '%' || chrExp == '(') {
                    // 연산자가 높으면 push , 연산자가 낮으면 스택의 연산자를 계속 꺼낸다
                    // 연산자 우선순위가 낮아 꺼내는 부분
                    // stack.peek() > chrExp 이면 true , 아니면 false
                    while (stack.isEmpty() == false && compareToPriority(stack.peek(), chrExp) == true ) {

                        // 두자리수 숫자를 위해 공백을 추가한다.
                        postExpression += setSpace(postExpression);
                        postExpression += stack.pop();
                    }
                    // 연산자 우선순위가 높아 스택에 저장
                    stack.push(chrExp);
                }
            }
            cnt++;
        }

        // 문자열을 끝까지 조사했는데도 스택에 값이 남아있으면 모두 꺼내서 후위표현식 문자열의 끝에 붙인다.
        while (stack.isEmpty() == false) {
            // 두자리이상 숫자를 위해 연산자 앞에 공백을 추가한다.
            postExpression += setSpace(postExpression);
            postExpression += stack.pop();
        }

        return postExpression;
    }


    // 알고리즘 : 후위 표현식 계산하기
    //후위 표현식을 왼쪽부터 한 글자씩 읽어서
    //피연산자이면, 스택에 push
    //연산자를 만나면 스택에서 pop → (1), 또 pop → (2)
    //(1) 연산 (2) 을 계산 이 결과를 스택에 push
    //수식의 끝에 도달하면 스택에서 pop → 이것이 계산 결과
    public double postCalculator(String expression) {

        // 중위 표현식을 후위 표현식으로 변환
        // 두자리이상 수를 위해 각각 숫자와 연산자는 공백으로 구분되어 있다.
        //String exp = middleToRear(expression);
        String exp = expression;

        Stack<Double> stack = new Stack<>();
        int cnt = 0;

        try {
            // 공백을 기준으로 나눈다
            String[] res = exp.split(" ");

            while (cnt < res.length) {

                if (res[cnt].equals("+") || res[cnt].equals("-") || res[cnt].equals("*") || res[cnt].equals("%") || res[cnt].equals("/")) {

                    // 연산자를 만나면 스택에서 pop → (1), 또 pop → (2)
                    double op2 = stack.pop();
                    double op1 = stack.pop();

                    // (1) 연산 (2) 을 계산 이 결과를 스택에 push
                    if (res[cnt].equals("+")) {
                        stack.push(op1 + op2);
                    } else if (res[cnt].equals("-")) {
                        stack.push(op1 - op2);
                    } else if (res[cnt].equals("*")) {
                        stack.push(op1 * op2);
                    } else if (res[cnt].equals("%")) {
                        stack.push(op1 % op2);
                    } else if (res[cnt].equals("/")) {
                        stack.push(op1 / op2);
                    }

                } else {
                    stack.push(Double.parseDouble(res[cnt]));
                }

                cnt++;

            }

        }
        catch ( Exception e) {
            // 에러 출력
            Toast.makeText(context, e.getMessage() , Toast.LENGTH_SHORT).show();
        }

        return stack.pop();

    }
}
