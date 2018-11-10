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

public class CreateRecyclerViewAdapter extends
        RecyclerView.Adapter<CreateRecyclerViewAdapter.ViewHolder> {

    private List<String> ingreds;
    private List<String> volumes;
    private List<String> units;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
//
//    public SparseBooleanArray getItemStateArray() {
//        return itemStateArray;
//    }
//
//    private SparseBooleanArray itemStateArray = new SparseBooleanArray();

    CreateRecyclerViewAdapter(Context context, List<String> ingreds, List<String> volumes,
                              List<String> units){
        this.inflater = LayoutInflater.from(context);
        this.ingreds = ingreds;
        this.volumes = volumes;
        this.units = units;
    }

    // Inflates the row layout from xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.create_row, parent, false);
        return new ViewHolder(view);
    }

    // Binds each ingredient to a TextView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String ingredient = ingreds.get(position);
        holder.ingredTextView.setText(ingredient);
        String volume = volumes.get(position);
        holder.volumeTextView.setText(volume);
        String unit = units.get(position);
        holder.unitsTextView.setText(unit);
        // not sure if needed
        //holder.bind(position);
    }

    @Override
    public int getItemCount(){
        return ingreds.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ConstraintLayout row;
        TextView ingredTextView;
        TextView volumeTextView;
        TextView unitsTextView;
        Button button;

        ViewHolder(View itemView){
            super(itemView);

           // row = (ConstraintLayout) itemView.findViewById(R.layout.create_row);
            ingredTextView = itemView.findViewById(R.id.ingredTextView);
            volumeTextView = itemView.findViewById(R.id.volumeTextView);
            unitsTextView = itemView.findViewById(R.id.unitsTextView);
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
