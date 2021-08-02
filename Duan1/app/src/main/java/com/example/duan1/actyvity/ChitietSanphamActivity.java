package com.example.duan1.actyvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duan1.R;
import com.example.duan1.model.GiohangModel;
import com.example.duan1.model.SanPham;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChitietSanphamActivity extends AppCompatActivity {
    Toolbar toolbarchitiet;
    ImageView imageViewchitiet;
    TextView txtten, txtgia, txtmota;
    Spinner spinner;
    Button btndatmua;
    int id = 0;
    String Tinh ="";
    String Huyen ="";
    String Tenchitiet = "";
    int Giachitiet = 0;
    String Hinhanhchitiet = "";
    String Motachitiet = "";
    int Idsanpham = 0;
    public static final String MY_PREFS_NAME = "abcd";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_sanpham);
        Anhxa();
        Actiontoolbar();
        Getinformation();
        CatchSpiner();
        EventButon();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButon() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exit = false;

                    for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                        if (MainActivity.manggiohang.get(i).getIdsp() == id) {
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp() >= 10) {
                                MainActivity.manggiohang.get(i).setSoluongsp(10);

                            }
                            MainActivity.manggiohang.get(i).setGiasp(Giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exit = true;
                        }
                    }
                    if (exit == false) {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * Giachitiet;
                        MainActivity.manggiohang.add(new GiohangModel(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong,Tinh,Huyen));
                    }


                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * Giachitiet;
                    MainActivity.manggiohang.add(new GiohangModel(id, Tenchitiet, Giamoi, Hinhanhchitiet, soluong,Tinh,Huyen));

                }


                SharedPreferences sharedPreferences=getSharedPreferences("abc", MODE_PRIVATE);
                sharedPreferences.edit().remove("abc").commit();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                Gson gson = new Gson();
                String json = gson.toJson(MainActivity.manggiohang);
                editor.putString("abc", json);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                startActivity(intent);

            }
        });
    }

    private void CatchSpiner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);

    }

    private void Getinformation() {

        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanPham.getId();
        Tenchitiet = sanPham.getTensanpham();
        Giachitiet = sanPham.getGiasanpham();
        Hinhanhchitiet = sanPham.getHinhanhsanpham();
        Motachitiet = sanPham.getMotasanpham();
        Idsanpham = sanPham.getIdsanpham();
        txtten.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá :" + decimalFormat.format(Giachitiet) + "   Đồng");
        txtmota.setText(Motachitiet);
        Picasso.with(getApplicationContext()).load(Hinhanhchitiet).placeholder(R.drawable.anh1)
                .error(R.drawable.anh2).into(imageViewchitiet);


    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet = findViewById(R.id.toolbarchitietsp);
        imageViewchitiet = findViewById(R.id.imgchitietsp);
        txtten = findViewById(R.id.tvtenchitietsp);
        txtgia = findViewById(R.id.tvgiachitietsp);
        txtmota = findViewById(R.id.tvchitietmota);
        spinner = findViewById(R.id.spinerchitiet);
        btndatmua = findViewById(R.id.btnthemgiohang);
    }
}