package com.example.mypc.xxxxx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mypc.xxxxx.R;
import com.example.mypc.xxxxx.bean.Chat;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

   private Context context;

   private List<Chat> mlist;

   public RecyclerViewAdapter(Context context,List<Chat> mlist){
       this.context=context;
       this.mlist=mlist;
   }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat chat = mlist.get(position);
        //判断消息是那边 选择性隐藏
        if (chat.getType()==Chat.TYPE_RECEIVED){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftChat.setText(chat.getText());


        }else if (chat.getType() == chat.TYPE_SENT) {

            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightChat.setText(chat.getText());

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    /**
     * 声明控件
     */

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftChat;
        TextView rightChat;

        public ViewHolder(View itemView){
            super(itemView);


            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            leftChat = itemView.findViewById(R.id.tv_left_text);
            rightChat = itemView.findViewById(R.id.tv_right_text);



        }
    }
}
