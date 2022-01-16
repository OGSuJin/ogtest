package com.example.taegyung.englishvocav2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TaeGyung on 2022-01-14.
 */

public class NoteItemAdapter extends BaseAdapter {

    ArrayList<MyNoteCommand> altItems = new ArrayList<>();
    Context cttContext;

    // SQLite DB
    // testforeng.db 데이터베이스  생성 또는 연결
    MyDBHelper m_helper;

    @Override
    public int getCount() {
        return altItems.size();
    }

    @Override
    public Object getItem(int i) {
        return altItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        cttContext = viewGroup.getContext();
        MyNoteCommand myNoteCommand = altItems.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) cttContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_note_item, viewGroup, false);

        }

        // 화면 연결

        // 커맨트 객체에서 화면으로 출력

        // 아이템 번호 전달


        return view;
    }

    public void addItem(MyNoteCommand myNoteCommand) {
        altItems.add(myNoteCommand);
    }

    public void clearItem() {
        altItems.clear();
    }
}
