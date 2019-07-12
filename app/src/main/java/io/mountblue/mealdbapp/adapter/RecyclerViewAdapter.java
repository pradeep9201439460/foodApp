package io.mountblue.mealdbapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.mountblue.mealdbapp.R;
import io.mountblue.mealdbapp.model.Category;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Category> categoryList;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_category,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder viewHolder, int i) {

        String strCategoryThum = categoryList.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThum).placeholder(R.drawable.ic_circle).into(viewHolder.categoryThumb);

        String strCategoryName = categoryList.get(i).getStrCategory();
        viewHolder.categoryName.setText(strCategoryName);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView categoryThumb;
        TextView categoryName;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryThumb = itemView.findViewById(R.id.categoryThumb);
            categoryName = itemView.findViewById(R.id.categoryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
