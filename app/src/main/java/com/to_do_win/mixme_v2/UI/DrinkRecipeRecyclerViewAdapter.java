package com.to_do_win.mixme_v2.UI;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.to_do_win.mixme_v2.R;

import java.util.List;

public class DrinkRecipeRecyclerViewAdapter extends
        RecyclerView.Adapter<DrinkRecipeRecyclerViewAdapter.ViewHolder> {

    private List<String> ingreds;
    private List<String> volumeUnits;
    private boolean user;
    private List<DrinkRecipeActivity.IngredientStatus> statuses;

    private LayoutInflater inflater;
    private ItemClickListener clickListener;
//
//    public SparseBooleanArray getItemStateArray() {
//        return itemStateArray;
//    }
//
//    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    DrinkRecipeRecyclerViewAdapter(Context context, List<String> ingreds, List<String> volumeUnits,
                                   boolean user, List<DrinkRecipeActivity.IngredientStatus> statuses){
        this.inflater = LayoutInflater.from(context);
        this.ingreds = ingreds;
        this.volumeUnits = volumeUnits;
        this.user = user;
        this.statuses = statuses;
    }

    // Inflates the row layout from xml
    @Override
    public DrinkRecipeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.create_row, parent, false);
        return new DrinkRecipeRecyclerViewAdapter.ViewHolder(view);
    }

    // Binds each ingredient to a TextView
    @Override
    public void onBindViewHolder(DrinkRecipeRecyclerViewAdapter.ViewHolder holder, int position){
        String ingredient = ingreds.get(position);
        holder.ingredTextView.setText(ingredient);
        String volume = volumeUnits.get(position);
        holder.volumeUnitsTextView.setText(volume);

        /////////////////////////////////////////////////// non-users still unable to access, so not able to test this
        if (!user){
            holder.button.setVisibility(View.INVISIBLE);
        } else {
            switch (statuses.get(position)){
                case NONE:
                    holder.button.setText("Add to Shopping List");
                    break;
                case CABINET:
                    holder.button.setText("Remove from Cabinet");
                    break;
                case SHOPPING:
                    holder.button.setText("Add to Cabinet");
                    break;
            }
        }


    }

    @Override
    public int getItemCount(){
        return ingreds.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ConstraintLayout row;
        TextView ingredTextView;
        TextView volumeUnitsTextView;
        Button button;

        ViewHolder(View itemView){
            super(itemView);

            // row = (ConstraintLayout) itemView.findViewById(R.layout.create_row);
            ingredTextView = itemView.findViewById(R.id.ingredTextView);
            volumeUnitsTextView = itemView.findViewById(R.id.volumeUnitsTextView);
            //unitsTextView = itemView.findViewById(R.id.unitsTextView);
            button = itemView.findViewById(R.id.removeBtn);
            itemView.setOnClickListener(this);
        }

        //  void bind(int position){
        // use the sparse boolean array to check
//            if (!itemStateArray.get(position, false)) {
//                checkBox.setChecked(false);}
//            else {
//                checkBox.setChecked(true);
//            }
        //    }

        @Override
        public void onClick(View view){
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());

//            int adapterPosition = getAdapterPosition();
//            Controller controller = Controller.getInstance();
//
//            controller.removeCreationIngredient(adapterPosition);


//            if (!itemStateArray.get(adapterPosition, false)) {
//                //checkBox.setChecked(true);
//                itemStateArray.put(adapterPosition, true);
//            }
//            else  {
//                //checkBox.setChecked(false);
//                itemStateArray.put(adapterPosition, false);
//            }
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
