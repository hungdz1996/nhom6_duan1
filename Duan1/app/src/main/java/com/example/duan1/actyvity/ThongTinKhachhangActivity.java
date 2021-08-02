package com.example.duan1.actyvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duan1.R;
import com.example.duan1.ultil.Checkconection;
import com.example.duan1.ultil.Sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachhangActivity extends AppCompatActivity {
    EditText edtTenKh,edtSodienthoai,edtEmail,edtTinh,edthuyen;
    Button btnxacnhan,btntrove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khachhang);
        Anhxa();


        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Checkconection.haveNetworkConnection(getApplicationContext())){

            EventButon();

        }else {
            Checkconection.ShowToast_Short(getApplicationContext(),"Bạn kiểm tra lại kết nối");
        }
    }
//    private void CatchSpiner() {
//        String [] soluong = new String[]{"hà nội","Hà nam"};
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
//        spinner.setAdapter(arrayAdapter);
//
//    }

    private void EventButon() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkh=edtTenKh.getText().toString().trim();
                String sodt=edtSodienthoai.getText().toString().trim();
                String email=edtEmail.getText().toString().trim();
                String tinh=edtTinh.getText().toString().trim();
                String huyen=edthuyen.getText().toString().trim();
                if (tenkh.length()>0&& sodt.length()>0&& email.length()>0&&tinh.length()>0&&huyen.length()>0){
                // dữ liệu lên sever
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Sever.duongdankhachhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String makhachhang) {
                            Log.d("madonhang",makhachhang);
                            if(Integer.parseInt(makhachhang)>0){
                                RequestQueue queue =Volley.newRequestQueue(getApplicationContext());
                                StringRequest request =new StringRequest(Request.Method.POST, Sever.duongdandonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            Checkconection.ShowToast_Short(getApplicationContext(),"Bạn thêm sản phẩm vào giỏ hàng thành công");
                                            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            Checkconection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        }
                                        else {
                                            Checkconection.ShowToast_Short(getApplicationContext(),"Dữ liệu đã bị lỗi");
                                        }

                                    }
                                },new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for (int i=0;i<MainActivity.manggiohang.size();i++){
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("makhachhang",makhachhang);
                                                jsonObject.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
                                                jsonObject.put("Tinh",MainActivity.manggiohang.get(i).getTinh());
                                                jsonObject.put("Huyen",MainActivity.manggiohang.get(i).getHuyen());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap <String,String> hashMap =new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());

                                        return hashMap;
                                    }
                                };
                                queue.add(request);

                            }

                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap=new HashMap<String, String>();
                            hashMap.put("tenkhachhang",tenkh);
                            hashMap.put("sodienthoai",sodt);
                            hashMap.put("email",email);
                            hashMap.put("Tinh",tinh);
                            hashMap.put("Huyen",huyen);

                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    Checkconection.ShowToast_Short(getApplicationContext(),"Bạn hãy nhập đầy đủ thông tin");
                }

            }
        });
    }

    private void Anhxa() {
        edtTenKh=findViewById(R.id.edtkhachhang);
        edtEmail=findViewById(R.id.edtemail);
        edtSodienthoai=findViewById(R.id.edtsodienthoai);
        btnxacnhan=findViewById(R.id.btnxacnhan);
        btntrove=findViewById(R.id.btntrove);
        edtTinh=findViewById(R.id.edtinh);
        edthuyen=findViewById(R.id.edthuyen);
    }
}