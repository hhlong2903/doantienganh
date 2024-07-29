package com.example.doanapphoctienganh.ActivityThiThu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanapphoctienganh.R;

public class LoadCauHoi extends AppCompatActivity {

    private TextView textTenBaiKiemTra, txt_Diem;
    private int idBaiKiemTra;
    private ImageButton btn_back;
    private Button start_button;
    private String tenBaiKiemTra;
    private static final int REQUEST_CODE_QUESTION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_cau_hoi);
        addControls();
        addEvents();

        textTenBaiKiemTra = findViewById(R.id.text_ten_bai_kiem_tra);
        idBaiKiemTra = getIntent().getIntExtra("IDBaiKiemTra", 0);
        tenBaiKiemTra = getIntent().getStringExtra("EXAM_NAME");

        textTenBaiKiemTra.setText(tenBaiKiemTra);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_QUESTION)
        {
            if(resultCode==RESULT_OK){
                int score=data.getIntExtra("score",0);
                txt_Diem.setText("Điểm " + tenBaiKiemTra + ": " + score);
            }
        }
    }

    public void addControls()
    {
        btn_back = findViewById(R.id.btn_back);
        start_button = findViewById(R.id.start_button);
        textTenBaiKiemTra = findViewById(R.id.text_ten_bai_kiem_tra);
        txt_Diem = findViewById(R.id.text_diem);
    }
    public void addEvents()
    {
        btn_back.setOnClickListener(v -> {
            finish();
        });

        start_button.setOnClickListener(v -> {
            Intent myintent= new Intent(LoadCauHoi.this, CauHoiKT.class);
            myintent.putExtra("IDBaiKiemTra", idBaiKiemTra);
            myintent.putExtra("EXAM_NAME", tenBaiKiemTra);
            startActivityForResult(myintent,REQUEST_CODE_QUESTION);
        });
    }
}