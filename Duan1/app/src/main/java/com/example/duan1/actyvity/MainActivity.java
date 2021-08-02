package com.example.duan1.actyvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.duan1.R;
import com.example.duan1.adapter.LoaisanphamAdapter;
import com.example.duan1.adapter.SanPhamAdapter;
import com.example.duan1.model.GiohangModel;
import com.example.duan1.model.Loaisanpham;
import com.example.duan1.model.SanPham;
import com.example.duan1.ultil.Checkconection;
import com.example.duan1.ultil.Sever;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ListView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewManhinhchinh;
    ArrayList<Loaisanpham> mangloaisanpham;
//    ArrayList<SanPham> arrayListsp;
    LoaisanphamAdapter loaisanphamAdapter;
    int id = 0;
    String tenloaisp = "";
    String hinhanhsp = "";
    ArrayList<SanPham> mangsanpham;
    SanPhamAdapter sanPhamAdapter;

    public static ArrayList<GiohangModel> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        if (Checkconection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewLiper();
            GetDulieuSanpham();
            GetDulieuSanphamNew();
            CatchItemListview();
            Loadmoredat();
        } else {
            Checkconection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra kết nối");
            finish();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("abcd", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("abc", null);
        Type type = new TypeToken<ArrayList<GiohangModel>>() {
        }.getType();
        // Log.d(",av", sharedPreferences.getString("HungNgong", null) + "");
        MainActivity.manggiohang = gson.fromJson(json, type);
        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }

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

    private void CatchItemListview() {
        listViewManhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (Checkconection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            // intent.putExtra("thongtinsanpham",mangsanpham.get(position));
                            startActivity(intent);
                        } else {
                            Checkconection.ShowToast_Short(getApplicationContext(), "Bạn Kiểm Tra Lại Kết Nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (Checkconection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, SonAcityvity.class);
                            intent.putExtra("idloaisanpham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            Checkconection.ShowToast_Short(getApplicationContext(), "Bạn Kiểm Tra Lại Kết Nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (Checkconection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, SuaRuaMatActivity.class);
                            intent.putExtra("idloaisanpham", mangloaisanpham.get(position).getId());
                            startActivity(intent);
                        } else {
                            Checkconection.ShowToast_Short(getApplicationContext(), "Bạn Kiểm Tra Lại Kết Nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (Checkconection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, LienHeActyvity.class);
                            startActivity(intent);
                        } else {
                            Checkconection.ShowToast_Short(getApplicationContext(), "Bạn Kiểm Tra Lại Kết Nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (Checkconection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, ThongTinActivityActivity.class);
                            startActivity(intent);
                        } else {
                            Checkconection.ShowToast_Short(getApplicationContext(), "Bạn Kiểm Tra Lại Kết Nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDulieuSanphamNew() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.duongdansanphamNew, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            int ID = 0;
                            String tensanpham = "";
                            Integer Giasanpham = 0;
                            String Hinhanhsanpham = "";
                            String Motasanpham = "";
                            int IDsanpham = 0;
                            SanPham sanPham=new SanPham();
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensanpham");
                            Giasanpham = jsonObject.getInt("giasanpham");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            Motasanpham = jsonObject.getString("motasanpham");
                            IDsanpham = jsonObject.getInt("idsanpham");

                            mangsanpham.add(new SanPham(ID, tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));

                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDulieuSanpham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisanpham");
                            hinhanhsp = jsonObject.getString("hinhanhsanpham");
                            mangloaisanpham.add(new Loaisanpham(id, tenloaisp, hinhanhsp));
                            loaisanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    mangloaisanpham.add(3, new Loaisanpham(0, "Liên Hệ", "http://file.hstatic.net/1000068742/article/nhiet_tinh.png"));
                    mangloaisanpham.add(4, new Loaisanpham(0, "Thông Tin", "https://png.pngtree.com/png-vector/20190916/ourlarge/pngtree-info-icon-for-your-project-png-image_1731084.jpg"));
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Checkconection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewLiper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://img.my-best.vn/press_eye_catches/e5bd79cc8f4e5b3a6ff971f9adf5b1ef.jpg?ixlib=rails-4.2.0&q=70&lossless=0&w=1400&h=787&fit=crop&s=ee4298a214f00dcac9b6708631e90a82");
        mangquangcao.add("https://dncosmetics.vn/wp-content/uploads/2020/09/son-li-ba-mau-agag-nu-hoang.jpg");
        mangquangcao.add("https://skinderm.vn/wp-content/uploads/2020/10/Sakura01.jpg");
        mangquangcao.add("https://www.watsons.vn/medias/Rosette-Acne-Clear-Face-Wash-120g-201117.jpg?context=bWFzdGVyfGZyb250L3pvb218MjE5OTcwfGltYWdlL2pwZWd8ZnJvbnQvem9vbS9oODcvaDBhLzkyMTMzMjM3MDYzOTguanBnfDhmNjMyODhhOTMzMmZiOGQ3ZTQxZjYxNzBmMTM3YzUyMmM1NzY2OTcyZDJmY2VlMzkxMjM2MWRjNTM4ZjM4ZTY");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);


    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }
    private void Loadmoredat(){

        recyclerViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,ChitietSanphamActivity.class);
                intent.putExtra("thongtinsanpham",mangsanpham.get(position));
                startActivity(intent);
            }
        });

    }


    private void AnhXa() {
        drawerLayout = findViewById(R.id.drawwelayout);
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh = findViewById(R.id.rycyclerview);
        navigationView = findViewById(R.id.navigation);
        listViewManhinhchinh = findViewById(R.id.lvmanhinhchinh);
        mangloaisanpham = new ArrayList<>();
        mangloaisanpham.add(0, new Loaisanpham(0, "Trang Chính", "https://i.pinimg.com/originals/da/81/9b/da819b1c3040075c535efb2dde00d914.jpg"));
        loaisanphamAdapter = new LoaisanphamAdapter(getApplicationContext(), mangloaisanpham);
        listViewManhinhchinh.setAdapter(loaisanphamAdapter);
        mangsanpham = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(getApplicationContext(),mangsanpham);
//        recyclerViewmanhinhchinh.setHasFixedSize(true);
//        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewmanhinhchinh.setAdapter(sanPhamAdapter);
        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }
    }



}