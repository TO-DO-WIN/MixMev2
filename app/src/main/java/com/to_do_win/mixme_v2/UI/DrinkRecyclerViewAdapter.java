package com.to_do_win.mixme_v2.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.to_do_win.mixme_v2.R;

import java.util.List;

public class DrinkRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;


    private ItemClickListener clickListener;
    private final int DRINK = 0, TEXT = 1;
    private final String ALMOST_TEXT = "You can almost make these drinks.";

    DrinkRecyclerViewAdapter(Context context, List<Object> items) {
        this.items = items;
    }

    // Inflates the row layout from xml
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case DRINK:
                View view1 = inflater.inflate(R.layout.drink_row, parent, false);
                viewHolder = new ViewHolder(view1);
                break;
            default:
                View view2 = inflater.inflate(R.layout.text_row, parent, false);
                viewHolder = new TextViewHolder(view2);
                break;
        }
        return viewHolder;
    }

    // size of makables defines which line textview will occur
    public int getItemViewType(int position) {
        if (items.get(position) instanceof DrinksFoundActivity.DrinkForAdapter)
            return DRINK;
        else if (items.get(position) instanceof String)
            return TEXT;
        return -1;
    }

    // Binds each drink to a button
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case DRINK:
                ViewHolder vh1 = (ViewHolder) holder;
                configureViewHolder(vh1, position);
                break;
            default:
                TextViewHolder tvh = (TextViewHolder) holder;
                configureTextViewHolder(tvh, position);
                break;
        }

//        String drink = A_Drink.get(position);
//        holder.button.setText(drink);
//        // not sure if needed
        //holder.bind(position);
    }

    private void configureViewHolder(ViewHolder vh1, int position) {
        DrinksFoundActivity.DrinkForAdapter drink =
                (DrinksFoundActivity.DrinkForAdapter) items.get(position);
        if (drink != null) {
            vh1.getButton().setText(drink.getDrinkName());
        }
    }

    private void configureTextViewHolder(TextViewHolder tvh, int position){
        tvh.getTextView().setText(ALMOST_TEXT);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button button;

        ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.drinkBtn);
            itemView.setOnClickListener(this);
        }

        public Button getButton() {
            return button;
        }

        public void setButton(Button button) {
            this.button = button;
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        TextViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.canAlmostMake);
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }

//    Object getItem(int id) {
//        return items.get(id);
//    }
    //String getItem(int id ){
//        return ingreds.get(id);
//    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
