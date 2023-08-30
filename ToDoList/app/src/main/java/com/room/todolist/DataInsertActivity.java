package com.room.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.room.todolist.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
    ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type=getIntent().getStringExtra("type");
        if(type.equals("update")){
            setTitle("Update Note");
            binding.edtTitle.setText(getIntent().getStringExtra("title"));
            binding.edtDesc.setText(getIntent().getStringExtra("desc"));
            int id = getIntent().getIntExtra("id",0);
            binding.noteSubmit.setText("Update");
            binding.noteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.edtTitle.getText().toString());
                    intent.putExtra("desc",binding.edtDesc.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
        else{
            setTitle("Add Note");
            binding.noteSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.edtTitle.getText().toString());
                    intent.putExtra("desc",binding.edtDesc.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
        }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}