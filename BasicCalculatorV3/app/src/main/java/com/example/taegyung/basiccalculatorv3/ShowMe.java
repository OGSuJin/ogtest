package com.example.taegyung.basiccalculatorv3;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by TaeGyung on 2021-12-27.
 */

public class ShowMe extends AppCompatActivity {

    Button button1;

    protected void onCreate(Bundle saveedInstanceState) {

        super.onCreate(saveedInstanceState);
        setContentView(R.layout.sub1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // 현재 실행중인 Activity를 종료한다.
                // 부모 화면으로 돌아간다.
                finish();
            }
        });

    }
}
