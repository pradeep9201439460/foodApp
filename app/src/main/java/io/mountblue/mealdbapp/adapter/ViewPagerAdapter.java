package io.mountblue.mealdbapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.mountblue.mealdbapp.R;
import io.mountblue.mealdbapp.model.Meal;

public class ViewPagerAdapter extends PagerAdapter {
    private List<Meal> mealList;
    private Context context;
    private static ClickListener clickListener;

    public ViewPagerAdapter(List<Meal> mealList, Context context) {
        this.mealList = mealList;
        this.context = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPagerAdapter.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return mealList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_view_pager_header,
                container,
                false
        );

        ImageView mealThumb = view.findViewById(R.id.mealThumb);
        TextView mealName = view.findViewById(R.id.mealName);

        String strMealThumb = mealList.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).into(mealThumb);

        String strMealName = mealList.get(position).getStrMeal();
        mealName.setText(strMealName);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v, position);
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }
}
