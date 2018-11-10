package com.to_do_win.mixme_v2.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.to_do_win.mixme_v2.R;

import java.util.List;

/**
 * Class used to create and bind views to be used in a RecyclerView which displays a list of
 * ingredient CheckBoxes
 */
public class IngredientRecyclerViewAdapter extends
        RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {

    private List<String> ingreds;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public SparseBooleanArray getItemStateArray() {
        return itemStateArray;
    }

    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    IngredientRecyclerViewAdapter(Context context, List<String> ingreds){
        this.inflater = LayoutInflater.from(context);
        this.ingreds = ingreds;
    }

    // Inflates the row layout from xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.ingredient_row, parent, false);
        return new ViewHolder(view);
    }

    // Binds each ingredient to a CheckBox
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String ingredient = ingreds.get(position);
        holder.checkBox.setText(ingredient);
        // not sure if needed
        holder.bind(position);
    }

    @Override
    public int getItemCount(){
        return ingreds.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CheckBox checkBox;

        ViewHolder(View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.ingredChBox);
            itemView.setOnClickListener(this);
        }

        void bind(int position){
            // use the sparse boolean array to check
            if (!itemStateArray.get(position, false)) {
                checkBox.setChecked(false);}
            else {
                checkBox.setChecked(true);
            }
        }

        @Override
        public void onClick(View view){
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                checkBox.setChecked(true);
                itemStateArray.put(adapterPosition, true);
            }
            else  {
                checkBox.setChecked(false);
                itemStateArray.put(adapterPosition, false);
            }
        }
    }

    String getItem(int id ){
        return ingreds.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
