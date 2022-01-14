package com.example.taegyung.paintboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 페인트보드를 위한 뷰를 생성 및 등록
        PaintBoard view = new PaintBoard(this);

        setContentView(view);
    }
}
