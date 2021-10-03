package com.csuncion.examen_suncion.examen_final.upn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csuncion.examen_suncion.examen_final.upn.entities.Menu;
import com.csuncion.examen_suncion.examen_final.upn.models.DAORestaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AdapterCustomized extends RecyclerView.Adapter<AdapterCustomized.MiViewHolder> {

    private Context context;
    private List<Menu> listMenu = new ArrayList<>();

    public AdapterCustomized(Context context, List<Menu> listMenu){
        this.context = context;
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public AdapterCustomized.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCustomized.MiViewHolder holder, int position) {
        double priceFood = listMenu.get(position).getPriceFood();
        double priceInput = listMenu.get(position).getPriceInput();
        int count = listMenu.get(position).getCountTotal();
        holder.rowFood.setText(listMenu.get(position).getFood());
        holder.rowInput.setText(listMenu.get(position).getInput());
        holder.rowPriceSecond.setText(String.valueOf(priceFood));
        holder.rowPriceInput.setText(String.valueOf(priceInput));
        holder.rowCount.setText(String.valueOf(count));
        switch (listMenu.get(position).getCodFood()){
            case 1:
                holder.imgFood.setImageResource(R.drawable.lomo_saltado);
                break;
            case 2:
                holder.imgFood.setImageResource(R.drawable.arroz_con_pollo);
                break;
            case 3:
                holder.imgFood.setImageResource(R.drawable.estofado_de_pollo);
                break;
            case 4:
                holder.imgFood.setImageResource(R.drawable.seco_de_carne);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder{
        TextView rowFood, rowPriceSecond, rowPriceInput,rowCount, rowInput;
        ImageView imgFood;
        public MiViewHolder(@NonNull View itemView){
            super(itemView);
            rowFood = itemView.findViewById(R.id.rowFood);
            rowPriceSecond = itemView.findViewById(R.id.rowPriceSecond);
            rowPriceInput = itemView.findViewById(R.id.rowPriceInput);
            rowCount = itemView.findViewById(R.id.rowCount);
            rowInput = itemView.findViewById(R.id.rowInput);
            imgFood = itemView.findViewById(R.id.imgFood);
        }
    }
}
