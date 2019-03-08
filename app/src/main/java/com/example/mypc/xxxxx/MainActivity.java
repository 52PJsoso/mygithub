package com.example.mypc.xxxxx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mypc.xxxxx.adapter.RecyclerViewAdapter;
import com.example.mypc.xxxxx.bean.Ask;
import com.example.mypc.xxxxx.bean.Chat;
import com.example.mypc.xxxxx.bean.Take;
import com.example.mypc.xxxxx.net.Api;
import com.example.mypc.xxxxx.util.L;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private EditText editText;
    private Button btn_send;


    //    对话信息集合
    private List<Chat> list = new ArrayList<>();
    //    适配器
    private RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        // 设置布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
    private void initView(){
        recyclerView=findViewById(R.id.recycler);
        editText=findViewById(R.id.et_text);
        btn_send=findViewById(R.id.btn_send);

        btn_send.setOnClickListener(this);
    }

    private void initData(){
        Chat c1 = new Chat("欢迎回来，俊崽", Chat.TYPE_RECEIVED);
        list.add(c1);
        Chat c2 = new Chat("今天不开心了吗？", Chat.TYPE_RECEIVED);
        list.add(c2);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                /**
                 * 获得输入框内容
                 * 发送后清空
                 */
                String text=editText.getText().toString();
                if (!TextUtils.isEmpty(text)){

                    addData(text, Chat.TYPE_SENT);

                    editText.setText("");

                    request(text);
                }
                break;
        }
    }

    private void addData(String text, int type) {
        Chat c = new Chat(text, type);
        list.add(c);
        recyclerViewAdapter.notifyItemInserted(list.size() - 1);
        recyclerView.scrollToPosition(list.size() - 1);

    }

    //数据请求
    private void request(String text){

        Ask ask=new Ask();
        Ask.UserInfoBean info=new Ask.UserInfoBean();
        info.setApiKey("52769b834a384670aa7fead99f048699");//机器key
        info.setUserId("411528");//用户id
        ask.setUserInfo(info);

        Ask.PerceptionBean.InputTextBean pre = new Ask.PerceptionBean.InputTextBean(text);//将要发送给机器人书文本



        ask.setPerception(new Ask.PerceptionBean(pre));




        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.tuling123.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建网络请求借口
        Api api=retrofit.create(Api.class);

        //Take接收返回数据
        Call<Take> call=api.requset(ask);

        call.enqueue(new Callback<Take>() {
            @Override
            public void onResponse(Call<Take> call, Response<Take> response) {
                String mText= response.body().getResults().get(0).getValues().getText();
                addData(mText, Chat.TYPE_RECEIVED);
                 L.d("接受到的机器人回复的数据： "+mText);

            }

            @Override
            public void onFailure(Call<Take> call, Throwable t) {
                L.d("请求失败： "+t.toString());
            }
        });
    }


}
