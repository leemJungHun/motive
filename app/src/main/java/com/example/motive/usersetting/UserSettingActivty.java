package com.example.motive.usersetting;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.motive.R;
import com.example.motive.join.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSettingActivty extends AppCompatActivity implements View.OnClickListener {

    private TextView logout;
    private TextView withdraw;
    private TextView pushSet;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersetting);
        logout = findViewById(R.id.logout);
        withdraw = findViewById(R.id.withdraw);
        pushSet = findViewById(R.id.pushSet);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        pushSet.setOnClickListener(this);
        pushSet.setOnTouchListener(touch);
        logout.setOnClickListener(this);
        logout.setOnTouchListener(touch);
        withdraw.setOnClickListener(this);
        withdraw.setOnTouchListener(touch);
    }

    View.OnTouchListener touch = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView selectV = (TextView)v;
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                selectV.setBackgroundResource(R.drawable.main_button_selelct);
                selectV.setTextColor(Color.WHITE);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                selectV.setBackgroundResource(R.drawable.main_button_line);
                selectV.setTextColor(Color.BLACK);
            }
            return false;
        }
    };

    @Override
    public void onClick(View view) {
        if (view == logout) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(UserSettingActivty.this);
            alert_confirm.setMessage("로그아웃을 하시겠습니까?").setCancelable(false).setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.setMessage("로그아웃 중입니다. 잠시 기다려 주세요...");
                            progressDialog.show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            assert user != null;
                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            firebaseAuth.signOut();
                                            finish();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        }
                                    });
                        }
                    }
            );
            alert_confirm.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(UserSettingActivty.this, "취소", Toast.LENGTH_LONG).show();
                }
            });
            alert_confirm.show();
        }
        //회원탈퇴를 클릭하면 회원정보를 삭제한다. 삭제전에 컨펌창을 하나 띄워야 겠다.
        if (view == withdraw) {
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(UserSettingActivty.this);
            alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.setMessage("계정을 삭제 중입니다. 잠시 기다려 주세요...");
                            progressDialog.show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            assert user != null;
                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            Toast.makeText(UserSettingActivty.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                            finish();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        }
                                    });
                        }
                    }
            );
            alert_confirm.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(UserSettingActivty.this, "취소", Toast.LENGTH_LONG).show();
                }
            });
            alert_confirm.show();
        }

        if(view == pushSet){

        }
    }
}
