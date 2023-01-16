package com.kamsiz.retrofit.apadter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamsiz.retrofit.R;
import com.kamsiz.retrofit.model.CryptoModel;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.RowHolder> {

    public RecylerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    private ArrayList<CryptoModel> cryptoList;
    private  String[] colors={"#bb2222","#bb77bb","#a9d1d1","#06666e","#741b47","#f4cccc","#ffe599","#6a329f"};
    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_layout,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textname;
        TextView textprice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bind(CryptoModel cryptoModel,String[] colors,Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textname= itemView.findViewById(R.id.text_name);
            textprice=itemView.findViewById(R.id.text_price);
            textname.setText(cryptoModel.currency);
            textprice.setText(cryptoModel.price);

        }

    }
}
