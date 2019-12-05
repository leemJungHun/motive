package com.example.motive.join;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.motive.R;

public class RegisterSelectActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView memberBtn;
    private TextView adminBtn;
    private TextView familyBtn;
    private TextView nextBtn;

    boolean memberSelect=true;
    boolean adminSelect=false;
    boolean familySelect=false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select);
        memberBtn = findViewById(R.id.member_select);
        adminBtn = findViewById(R.id.admin_select);
        familyBtn = findViewById(R.id.family_select);
        nextBtn = findViewById(R.id.next_btn);
        memberBtn.setOnClickListener(this);
        adminBtn.setOnClickListener(this);
        familyBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        if(v==memberBtn){
            memberBtn.setBackgroundResource(R.drawable.btn_round_select);
            adminBtn.setBackgroundResource(R.drawable.btn_round);
            familyBtn.setBackgroundResource(R.drawable.btn_round);
            memberSelect=true;
            adminSelect=false;
            familySelect=false;
        }else if(v == adminBtn){
            memberBtn.setBackgroundResource(R.drawable.btn_round);
            adminBtn.setBackgroundResource(R.drawable.btn_round_select);
            familyBtn.setBackgroundResource(R.drawable.btn_round);
            memberSelect=false;
            adminSelect=true;
            familySelect=false;
        }else if(v==familyBtn){
            memberBtn.setBackgroundResource(R.drawable.btn_round);
            adminBtn.setBackgroundResource(R.drawable.btn_round);
            familyBtn.setBackgroundResource(R.drawable.btn_round_select);
            memberSelect=false;
            adminSelect=false;
            familySelect=true;
        }else{
            if(memberSelect){
                finish();
                startActivity(new Intent(this, MemberRegisterActivity.class));
            }else if(adminSelect){
                finish();
                //startActivity(new Intent(this, AdminRegisterActivity.class));
            }else if(familySelect){
                finish();
                //startActivity(new Intent(this, FamilyRegisterActivity.class));
            }
        }

    }
}
