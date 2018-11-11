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

public class ShoppingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int posOfText;
    private List<String> items;

    private ShoppingRecyclerViewAdapter.ItemClickListener clickListener;
    private final int INGREDIENT = 0, TEXT = 1;
    private final String GROCERY_LIST = "Grocery Store List:";

    ShoppingRecyclerViewAdapter(Context context, List<String> items, int posOfText) {
        this.items = items;
        this.posOfText = posOfText;
    }

    // Inflates the row layout from xml
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case INGREDIENT:
                View ingredView = inflater.inflate(R.layout.cab_ingredient_row, parent, false);
                viewHolder = new ShoppingRecyclerViewAdapter.IngredViewHolder(ingredView);
                break;
            case TEXT:
                View textView = inflater.inflate(R.layout.text_row, parent, false);
                viewHolder = new ShoppingRecyclerViewAdapter.TextViewHolder(textView);
                break;
            default:
                viewHolder = null;
        }
        return viewHolder;
    }

    // size of makables defines which line textview will occur
    public int getItemViewType(int position) {
        if (position != posOfText)
            return INGREDIENT;
        else if (position == posOfText)
            return TEXT;
        return -1;
    }

    // Binds each drink to a button
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case INGREDIENT:
                ShoppingRecyclerViewAdapter.IngredViewHolder ingredVH = (ShoppingRecyclerViewAdapter.IngredViewHolder) holder;
                configureLiquorVH(ingredVH, position);
                break;
            default:
                ShoppingRecyclerViewAdapter.TextViewHolder textVH = (ShoppingRecyclerViewAdapter.TextViewHolder) holder;
                configureTVHolder(textVH, position);
                break;
        }
    }

    private void configureLiquorVH(ShoppingRecyclerViewAdapter.IngredViewHolder ingredVH, int position) {
        ingredVH.getTextView().setText(items.get(position));
    }

    private void configureTVHolder(ShoppingRecyclerViewAdapter.TextViewHolder textVH, int position) {
        textVH.getTextView().setText(GROCERY_LIST);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class IngredViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        Button button;

        IngredViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ingredientTV);
            button = itemView.findViewById(R.id.removeBtn);
            button.setOnClickListener(this);
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onItemClick(textView, getAdapterPosition());
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

    void setClickListener(ShoppingRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}