package com.example.tomato.lesson5recycleviewtdgiang.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tomato.lesson5recycleviewtdgiang.R;
import com.example.tomato.lesson5recycleviewtdgiang.adapter.ContactAdapter;
import com.example.tomato.lesson5recycleviewtdgiang.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        ContactAdapter.OnCallBack, View.OnClickListener {

    RecyclerView recyclerView;
    SwitchCompat switchStatus;
    LinearLayoutManager layoutManager;
    ArrayList<Contact> arrayList;
    ImageButton ibtnAdd;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initData();
    }

    private void initData() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // hàm tối ưu giao diện
        optimizationInterface();

        // data demo  --------------------------------------------------------------------
        arrayList = new ArrayList<>();
        arrayList.add(new Contact("Nguyễn Thanh Hải ", "012668832"));
        arrayList.add(new Contact("Phạm Minh Khôi ", "012663388"));
        arrayList.add(new Contact("Lưu Lan Phương ", "01266833"));
        arrayList.add(new Contact("Đào Bá Hùng ", "01266822"));
        arrayList.add(new Contact("Lại Cương Dũng ", "012668668"));
        arrayList.add(new Contact("Tô Nguyệt Nga ", "01266884"));
        // -----------------------------------------------------------------------------

        adapter = new ContactAdapter(arrayList, getApplicationContext(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initEvent() {
        switchStatus.setOnCheckedChangeListener(this);
        ibtnAdd.setOnClickListener(this);
    }

    private void initView() {
        switchStatus = findViewById(R.id.switch_status);
        recyclerView = findViewById(R.id.recyclerview);
        ibtnAdd = findViewById(R.id.imgbtn_add);
    }

    private void changeInterface() {
        if (switchStatus.isChecked()) {
            layoutManager = new GridLayoutManager(this, 2);
            optimizationInterface();
            recyclerView.setLayoutManager(layoutManager);
        } else {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            optimizationInterface();
            recyclerView.setLayoutManager(layoutManager);
        }
    }

    private void optimizationInterface() {
        recyclerView.setHasFixedSize(true);
        // tối ưu hóa tự động
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void addContact() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);       // không hiên title của dialog
        dialog.setContentView(R.layout.dialog_add);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtName = dialog.findViewById(R.id.edt_name);
        final EditText edtPhoneNumber = dialog.findViewById(R.id.edt_phone_number);
        Button btnSave = dialog.findViewById(R.id.btn_save);
        Button btnClose = dialog.findViewById(R.id.btn_close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtName.getText().toString().trim()) ||
                        TextUtils.isEmpty(edtPhoneNumber.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, R.string.notification, Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(edtName.getText().toString(), edtPhoneNumber.getText().toString());
                    arrayList.add(0, contact);
                    //  adapter.notifyItemInserted(0);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgbtn_add:
                addContact();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_status:
                changeInterface();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClicked(int position) {
        editContact(position);
    }

    private void editContact(final int position) {
        Contact contact = arrayList.get(position);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);       // không hiên title của dialog
        dialog.setContentView(R.layout.dialog_update);
        //  dialog.setCanceledOnTouchOutside(false);

        final EditText edtName = dialog.findViewById(R.id.edt_name);
        final EditText edtPhoneNumber = dialog.findViewById(R.id.edt_phone_number);
        Button btnSave = dialog.findViewById(R.id.btn_save);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);

        edtName.setText(contact.getmName());
        edtPhoneNumber.setText(contact.getmNumberPhone());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(position);
                adapter.notifyItemRemoved(position);
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtName.getText().toString().trim()) ||
                        TextUtils.isEmpty(edtPhoneNumber.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, R.string.notification, Toast.LENGTH_SHORT).show();
                } else {
                    arrayList.get(position).setmName(edtName.getText().toString());
                    arrayList.get(position).setmNumberPhone(edtPhoneNumber.getText().toString());
                    // adapter.notifyItemInserted(0);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
