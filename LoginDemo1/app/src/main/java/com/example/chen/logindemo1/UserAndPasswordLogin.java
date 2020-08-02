package com.example.chen.logindemo1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.chen.logindemo1.PickerScrollView.onSelectListener;
/**
 * Created by Chen on 2020/8/2.
 */

public class UserAndPasswordLogin  extends AppCompatActivity {
    private Context mContext;
    private String product_select;

    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private View view_custom;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    private TextView text_cancle,text_ok;
    private TextView bt_selected;


    private ArrayList<String> stringArrayList = new ArrayList<>();//需要显示的所有数据
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_password_login);
        mContext = UserAndPasswordLogin.this;
        initView();
        initLinstener();
        initData();
    }
    /**
     * 初始化
     */
    private void initView() {
        TextView text_phone_number_login = findViewById(R.id.text_phone_number_login);
        bt_selected = findViewById(R.id.bt_selected);

        builder = new AlertDialog.Builder(mContext);
        final LayoutInflater inflater = UserAndPasswordLogin.this.getLayoutInflater();
        view_custom = inflater.inflate(R.layout.select_dialog, null, false);
        pickerscrlllview = view_custom.findViewById(R.id.pickerscrlllview);
        text_cancle=view_custom.findViewById(R.id.text_cancle);
        text_ok=view_custom.findViewById(R.id.text_ok);
        builder.setView(view_custom);
        builder.setCancelable(false);
        alert = builder.create();
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window dialogWindow = alert.getWindow();
        //设置进出动画
        dialogWindow.setWindowAnimations(R.style.dialog_style);//<pre name="code" class="java">然后再Style文件中定义这么一个Style,就是我们的dialog_anim
        //设置宽度铺屏幕
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.setBackgroundDrawableResource(android.R.color.white);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        text_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });
        text_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                bt_selected.setText(product_select);


            }
        });
        text_phone_number_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAndPasswordLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bt_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.show();
            }
        });
    }
    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<Pickers>();
        id = new String[] { "1", "2", "3", "4", "5", };
        name = new String[] { "选项一", "选项二", "选项三", "选项四", "选项五" };
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }
    /**
     * 设置监听事件
     */
    private void initLinstener() {

        pickerscrlllview.setOnSelectListener(pickerListener);
        // bt_yes.setOnClickListener(onClickListener);
    }

    // 滚动选择器选中事件

    onSelectListener pickerListener = new onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
            product_select=pickers.getShowConetnt();
        }
    };

}

