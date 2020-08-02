package com.example.chen.logindemo1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_phone_numer, edit_phone_verification;
    private TextView get_phone_verification, text_user_password_login;
    private Button bt_phone_number_login;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=MainActivity.this;
        setContentView(R.layout.activity_main);
        bt_phone_number_login = findViewById(R.id.bt_phone_number_login);
        get_phone_verification = findViewById(R.id.get_phone_verification);
        edit_phone_numer=findViewById(R.id.edit_phone_numer);
        get_phone_verification.setOnClickListener(this);


        bt_phone_number_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建自定义Dialog实现提示框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view1 = View.inflate(MainActivity.this, R.layout.select_into_product, null);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setView(view1);
                alertDialog.show();
                Button button1 = view1.findViewById(R.id.insurance1);
                Button button2 = view1.findViewById(R.id.insurance2);
                Button button3 = view1.findViewById(R.id.insurance3);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Toast.makeText(MainActivity.this,"点击进入选项一界面",Toast.LENGTH_SHORT).show();

                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      Toast.makeText(MainActivity.this,"点击进入选项二界面",Toast.LENGTH_SHORT).show();

                    }
                });
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"点击进入选项三界面",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        text_user_password_login = findViewById(R.id.text_user_password_login);
        text_user_password_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserAndPasswordLogin.class);
                startActivity(intent);
            }
        });

    }
    //new倒计时对象,总共的时间,每隔多少秒更新一次时间
    final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);

    @Override
    public void onClick(View view) {
        if (view == get_phone_verification) {
            myCountDownTimer.start();//验证码按钮计时
            //获取验证码
            String phone_number=edit_phone_numer.getText().toString().trim();
            getVerification(phone_number);
        }
    }
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int arg1=msg.arg1;
            int result=msg.arg2;
            Object data=msg.obj;
        }
    };
    /*
    百度一些免费的短信接口放在这里可以使用即可
     */
    private void getVerification(String phone_number){

    }
    //计时函数内部类
    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        //计时过程
        @Override
        public void onTick(long l) {
            //防止多次点击
            get_phone_verification.setClickable(false);
            get_phone_verification.setText(l/1000+"");
        }
        //计时完毕的方法
        @Override
        public void onFinish() {
            get_phone_verification.setText("重新获取");
            get_phone_verification.setClickable(true);
        }
    }
}
