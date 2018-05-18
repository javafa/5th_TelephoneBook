package com.kodonho.android.telephonebook.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kodonho.android.telephonebook.R;
import com.kodonho.android.telephonebook.domain.Phone;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.Holder>{
    List<Phone> data;
    public PhoneAdapter(){

    }
    public void setDataAndRefresh(List<Phone> data){
        this.data = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Phone phone = data.get(position);
        holder.setNo(phone.id);
        holder.setName(phone.name);
        holder.setPhoneNumber(phone.phoneNumber);
        holder.phone = phone;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private TextView textNumber,textNo, textName;
        Button btnCall;
        LinearLayout item;
        Phone phone;
        public Holder(View itemView) {
            super(itemView);
            textNo = itemView.findViewById(R.id.textNo);
            textName = itemView.findViewById(R.id.textName);
            textNumber = itemView.findViewById(R.id.textNumber);
            btnCall = itemView.findViewById(R.id.btnCall);
            item = itemView.findViewById(R.id.item);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View v) {
                    Uri phoneUri = Uri.parse("tel:"+phone.phoneNumber);
                    Intent intent = new Intent(Intent.ACTION_CALL, phoneUri);
                    v.getContext().startActivity(intent);
                }
            });
        }
        public void setNo(int no){
            textNo.setText(no + "");
        }
        public void setNo(String no){
            textNo.setText(no);
        }
        public void setName(String name){
            textName.setText(name);
        }
        public void setPhoneNumber(String number){
            //String formatted = ""; // 전화번호 숫자사이에 - 입력
            textNumber.setText(number);
        }
    }
}
