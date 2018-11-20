package com.to_do_win.mixme_v2.UI;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.to_do_win.mixme_v2.R;
import com.to_do_win.mixme_v2.controller.Controller;

import java.util.List;

public class ShoppingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int posOfText;
    private List<String> items;

    private ShoppingRecyclerViewAdapter.MyItemClickListener clickListener;
    private final int INGREDIENT = 0, TEXT = 1;
    private final String GROCERY_LIST = "Grocery Store List:";
    private final String EMPTY_GROCERY_LIST = "You do not have any ingredients in your grocery store shopping list.";
    Controller controller;

    ShoppingRecyclerViewAdapter(Context context, List<String> items, int posOfText) {
        this.items = items;
        this.posOfText = posOfText;
        controller = Controller.getInstance();
    }

    // Inflates the row layout from xml
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case INGREDIENT:
                View ingredView = inflater.inflate(R.layout.shop_ingredient_row, parent, false);
                viewHolder = new ShoppingRecyclerViewAdapter.IngredViewHolder(ingredView);
// , new IngredViewHolder.MyItemClickListener() {
//                    @Override
//                    public void onAddCabinet(int position) {
//                        Toast.makeText(parent.getContext(), "clicked add to cabinet", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onDelete(int position) {
//
//
////        if (position > posOfText) {
////            intent.putExtra("drink", items.get(position));
////            intent.setClassName(packageName,
////                    packageName + ".UI.DrinkRecipeActivity");
////            startActivity(intent);
////        }
//
//
//                        Toast.makeText(parent.getContext(), "clicked remove", Toast.LENGTH_LONG).show();
//                    }
//                });
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
                configureIngredientVH(ingredVH, position);
                break;
            default:
                ShoppingRecyclerViewAdapter.TextViewHolder textVH = (ShoppingRecyclerViewAdapter.TextViewHolder) holder;
                configureTVHolder(textVH, position);
                break;
        }
    }

    private void configureIngredientVH(ShoppingRecyclerViewAdapter.IngredViewHolder ingredVH, int position) {
        ingredVH.getTextView().setText(items.get(position));
    }

    private void configureTVHolder(ShoppingRecyclerViewAdapter.TextViewHolder textVH, int position) {
        if (items.size() - 1 == posOfText) {
            textVH.getTextView().setText(EMPTY_GROCERY_LIST);
        } else {
            textVH.getTextView().setText(GROCERY_LIST);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class IngredViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //MyItemClickListener listener;

        TextView textView;
        Button removeBtn;
        Button toCabBtn;

        IngredViewHolder(View itemView){
        //, MyItemClickListener listener) {
            super(itemView);

            //this.listener = listener;
            textView = itemView.findViewById(R.id.ingredientTV);
            toCabBtn = itemView.findViewById(R.id.toCabBtn);
            toCabBtn.setOnClickListener(this);
            removeBtn = itemView.findViewById(R.id.removeBtn);
            removeBtn.setOnClickListener(this);
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
                clickListener.onItemClick(v, getAdapterPosition());

//            switch (v.getId()) {
//                case R.id.toCabBtn:
//                    clickListener.onAddCabinet(this.getLayoutPosition());
//                    break;
//                case R.id.removeBtn:
//                    listener.onDelete(this.getLayoutPosition());
//                    break;
//                default:
//                    break;
//            }


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

    void setClickListener(ShoppingRecyclerViewAdapter.MyItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);

    }

}