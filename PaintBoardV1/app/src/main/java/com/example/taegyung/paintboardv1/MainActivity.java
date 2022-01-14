package com.example.taegyung.paintboardv1;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 버튼 정의
    Button btnWidthIncrease;
    Button btnWidthDecrease;
    Button btnWidthErase;

    // 라이오 버튼
    RadioGroup rdgAntiAlias;
    RadioGroup rdgStyle;

    // 그려질 영역 정의
    PaintBoard paintBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 페인트보드를 위한 뷰를 생성 및 등록
        //PaintBoard view = new PaintBoard(this);
        setContentView(R.layout.activity_main);

        // 버튼 객체 얻기
        btnWidthIncrease = (Button) findViewById(R.id.btnWidthIncrease);
        btnWidthDecrease = (Button) findViewById(R.id.btnWidthDecrease);
        btnWidthErase = (Button) findViewById(R.id.btnWidthErase);

        rdgAntiAlias = (RadioGroup) findViewById(R.id.rdgAntiAlias);
        rdgStyle = (RadioGroup) findViewById(R.id.rdgStyle);

        // PaintBoard View 객체 얻기
        paintBoard = (PaintBoard) findViewById(R.id.viewContainer);

        // 펜 굵기 증가
        btnWidthIncrease.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PaintBoard.iStrokeWidth += 1;

                // 속성 재설정
                paintBoard.setPaintAttr(paintBoard.getiColor(), paintBoard.isbAntiAlias(),
                        PaintBoard.iStrokeWidth, paintBoard.getPsStyle());

                Toast.makeText(getApplicationContext(), PaintBoard.iStrokeWidth + "", Toast.LENGTH_SHORT).show();
            }
        });

        // 펜 굵기 감소
        btnWidthDecrease.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 감소시 현재 상태가 1아닐때에만 감소
                if ( PaintBoard.iStrokeWidth > 1) {

                    PaintBoard.iStrokeWidth -= 1;
                }

                // 속성 재설정
                paintBoard.setPaintAttr(paintBoard.getiColor(), paintBoard.isbAntiAlias(),
                        PaintBoard.iStrokeWidth, paintBoard.getPsStyle());

                Toast.makeText(getApplicationContext(),  PaintBoard.iStrokeWidth + "", Toast.LENGTH_SHORT).show();

            }
        });

        // 펜 지우기
        btnWidthErase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 화면 지우기
                paintBoard.clearCanvas();

            }
        });

        // 라이도 버튼 처리
        rdgAntiAlias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {

                    case R.id.rdoOn :
                        // 속성 재설정
                        paintBoard.setPaintAttr(paintBoard.getiColor(), true,
                                PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                        break;

                    case R.id.rdoOff :
                        paintBoard.setPaintAttr(paintBoard.getiColor(), false,
                                PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                        break;
                }
            }
        });

        // 라이도 버튼 처리
        rdgStyle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {

                    case R.id.rdoFill :
                        // 속성 재설정
                        paintBoard.setPaintAttr(paintBoard.getiColor(), paintBoard.isbAntiAlias(),
                                PaintBoard.iStrokeWidth, Paint.Style.FILL);
                        break;

                    case R.id.rdoFillAndStoke :
                        paintBoard.setPaintAttr(paintBoard.getiColor(), paintBoard.isbAntiAlias(),
                                PaintBoard.iStrokeWidth, Paint.Style.FILL_AND_STROKE);
                        break;

                    case R.id.rdoStoke :
                        paintBoard.setPaintAttr(paintBoard.getiColor(), paintBoard.isbAntiAlias(),
                                PaintBoard.iStrokeWidth, Paint.Style.STROKE);
                        break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.color_menu, menu);

        return true;
    }

    // 옵션 메뉴에 선택되었을 때 사용되는 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuRed :
                // 컬러 변경
                paintBoard.setPaintAttr(Color.RED, paintBoard.isbAntiAlias(),
                         PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
            case R.id.menuOrange :
                // 컬러 변경
                paintBoard.setPaintAttr(Color.MAGENTA, paintBoard.isbAntiAlias(),
                         PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
            case R.id.menuYellow :
                paintBoard.setPaintAttr(Color.YELLOW, paintBoard.isbAntiAlias(),
                         PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
            case R.id.menuGreen :
                paintBoard.setPaintAttr(Color.GREEN, paintBoard.isbAntiAlias(),
                         PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
            case R.id.menuBlue :
                paintBoard.setPaintAttr(Color.BLUE, paintBoard.isbAntiAlias(),
                         PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
            case R.id.menuNavy :
                paintBoard.setPaintAttr(Color.CYAN, paintBoard.isbAntiAlias(),
                         PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
            case R.id.menuPuple :
                paintBoard.setPaintAttr(Color.rgb(Integer.parseInt("8b", 16), 0, Integer.parseInt("ff", 16)),
                        paintBoard.isbAntiAlias(),  PaintBoard.iStrokeWidth, paintBoard.getPsStyle());
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
