package com.example.duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham>arrayListsanpham;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayListsanpham) {
        this.context = context;
        this.arrayListsanpham = arrayListsanpham;

    }



//
//    @NonNull
//
//    @Override
//    public ItemHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham,null);
//        ItemHolder itemHolder=new ItemHolder(view);
//
//
//        return itemHolder;
//    }
//
//    @Override
//    public void onBindViewHolder( SanPhamAdapter.ItemHolder holder, int position) {
//        SanPham sanPham=arrayListsanpham.get(position);
//        holder.txttensanpham.setText(sanPham.getTensanpham());
//        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
//        holder.txtgiasanpham.setText("Giá :"+ decimalFormat.format(sanPham.getGiasanpham())+"  Đồng");
//        Picasso.with(context).load(sanPham.getHinhanhsanpham()).placeholder(R.drawable.anh1).
//                error(R.drawable.anhchao).into(holder.imghinhanhsanpham);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayListsanpham.size();
//    }

    @Override
    public int getCount() {
        return arrayListsanpham.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListsanpham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ItemHolder{
        public ImageView imghinhanhsanpham;
        public TextView txttensanpham,txtgiasanpham;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder itemHolder=null;
        if (convertView==null){
            itemHolder=new ItemHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_sanpham,null);
            itemHolder.imghinhanhsanpham=convertView.findViewById(R.id.imgsanpham);
            itemHolder.txttensanpham=convertView.findViewById(R.id.tvtensanpham);
            itemHolder.txtgiasanpham=convertView.findViewById(R.id.tvgiasanpham);
            convertView.setTag(itemHolder);
        }
        else {
            itemHolder= (ItemHolder) convertView.getTag();
        }
        SanPham sanPham= (SanPham) getItem(position);
        itemHolder.txttensanpham.setText(sanPham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        itemHolder.txtgiasanpham.setText("Giá :"+ decimalFormat.format(sanPham.getGiasanpham())+"  Đồng");
//        itemHolder.txtgiasanpham.setText(sanPham.getGiasanpham());
        Picasso.with(context).load(sanPham.getHinhanhsanpham()).placeholder(R.drawable.anh1).error(R.drawable.anh2)
                .into(itemHolder.imghinhanhsanpham);
        return convertView;
    }

//    public class ItemHolder extends RecyclerView.ViewHolder{
//        public ImageView imghinhanhsanpham;
//         public TextView txttensanpham,txtgiasanpham;
//
//        public ItemHolder(View itemView) {
//            super(itemView);
//            imghinhanhsanpham=itemView.findViewById(R.id.imgsanpham);
//            txttensanpham=itemView.findViewById(R.id.tvtensanpham);
//            txtgiasanpham=itemView.findViewById(R.id.tvgiasanpham);
//
//        }
//    }
}
