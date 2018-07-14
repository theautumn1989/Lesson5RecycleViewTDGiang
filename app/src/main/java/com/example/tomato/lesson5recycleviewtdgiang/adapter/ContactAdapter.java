package com.example.tomato.lesson5recycleviewtdgiang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tomato.lesson5recycleviewtdgiang.R;
import com.example.tomato.lesson5recycleviewtdgiang.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHodelr> {

    // khai báo interface callback
    OnCallBack onCallBack;

    ArrayList<Contact> arrContact;
    Context context;

    public ContactAdapter(ArrayList<Contact> arrContact, Context context, OnCallBack onCallBack) {
        this.arrContact = arrContact;
        this.context = context;
        this.onCallBack = onCallBack;
    }

    @NonNull
    @Override
    public ViewHodelr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);
        if (itemView != null) {
            return new ViewHodelr(itemView);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelr holder, int position) {

        // Contact contact = arrContact.get(position);
        holder.tvName.setText(arrContact.get(position).getmName());
        holder.tvNumberPhone.setText(arrContact.get(position).getmNumberPhone());
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHodelr extends RecyclerView.ViewHolder implements View.OnClickListener {

        // ánh xạ
        TextView tvName, tvNumberPhone;

        public ViewHodelr(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvNumberPhone = itemView.findViewById(R.id.tv_number_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCallBack.onItemClicked(getPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
        }
    }

    public interface OnCallBack {
        void onItemClicked(int position);
    }
}
