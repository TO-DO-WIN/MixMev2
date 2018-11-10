package com.to_do_win.mixme_v2.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.to_do_win.mixme_v2.R;

import java.util.List;

public class SelectIngredientRVAdapter extends RecyclerView.Adapter<SelectIngredientRVAdapter.ViewHolder> {
    private List<String> ingredients;
    private LayoutInflater inflater;
    private SelectIngredientRVAdapter.ItemClickListener clickListener;

    SelectIngredientRVAdapter(Context context, List<String> ingredients){
        this.inflater = LayoutInflater.from(context);
        this.ingredients = ingredients;
    }

    // Inflates the row layout from xml
    @Override
    public SelectIngredientRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.add_ingredient_row, parent, false);
        return new SelectIngredientRVAdapter.ViewHolder(view);
    }

    // Binds each drink to a button
    @Override
    public void onBindViewHolder(SelectIngredientRVAdapter.ViewHolder holder, int position){
        String ingredient = ingredients.get(position);
        holder.button.setText(ingredient);
        // not sure if needed
        //holder.bind(position);
    }

    @Override
    public int getItemCount(){
        return ingredients.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button button;

        ViewHolder(View itemView){
            super(itemView);
            button = itemView.findViewById(R.id.selectIngredientBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id ){
        return ingredients.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
