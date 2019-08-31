package com.projects.naduni.eventplanner;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projects.naduni.eventplanner.Service.DatabaseHelper;


public class TodoListView extends Fragment {


    Button btn_viewTodos;
    DatabaseHelper mydb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todolist_view, container, false);
        btn_viewTodos = (Button)view.findViewById(R.id.todolistviewbutton);
        mydb = new DatabaseHelper(getActivity());
        btn_viewTodos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Cursor res = mydb.viewAllData();
                    if(res.getCount() == 0){
                        showMessage("Error","Nothing Found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){

                        buffer.append("ID :"+res.getString(0)+"\n");
                        buffer.append("Task :"+res.getString(1)+"\n");
                        buffer.append("Note :"+res.getString(2)+"\n");
                    }
                    showMessage("Data ",buffer.toString());
            }});
        return view;
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}