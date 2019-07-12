package io.mountblue.mealdbapp.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import io.mountblue.mealdbapp.R;
import io.mountblue.mealdbapp.model.Meal;
import io.mountblue.mealdbapp.viewmodel.FoodViewModel;
import io.mountblue.mealdbapp.viewmodel.ViewModelProviderFactory;

import static io.mountblue.mealdbapp.util.Constant.EXTRA_DETAIL;
import static io.mountblue.mealdbapp.util.Constant.SPACE_DOT;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView mealThumb;
    TextView category;
    TextView country;
    TextView instructions;
    TextView ingredients;
    TextView measures;
    ProgressBar progressBar;
    TextView youtube;
    TextView source;
    String mealName;
    Meal meal = new Meal();
    private static final String TAG = "DetailActivity";
    FoodViewModel foodViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        mealThumb = findViewById(R.id.mealThumb);
        category = findViewById(R.id.category);
        country = findViewById(R.id.country);
        instructions = findViewById(R.id.instructions);
        ingredients = findViewById(R.id.ingredient);
        measures = findViewById(R.id.measure);
        progressBar = findViewById(R.id.progressBar);
        youtube = findViewById(R.id.youtube);
        source = findViewById(R.id.source);

        showLoading();
        Intent intent = getIntent();
        mealName = intent.getStringExtra(EXTRA_DETAIL);
        foodViewModel = ViewModelProviders.of(this, viewModelProviderFactory).
                get(FoodViewModel.class);
        foodViewModel.getMealByName(mealName).observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable List<Meal> mealList) {
                assert mealList != null;
                meal = mealList.get(0);
                initMealByName(meal);
            }
        });
    }

    private void initMealByName(Meal meal) {
        Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
        collapsingToolbarLayout.setTitle(meal.getStrMeal());
        category.setText(meal.getStrCategory());
        country.setText(meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
        hideLoading();
        getMealIngredient(meal);
        setupActionBar();
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void getMealIngredient(final Meal meal) {
        if (!meal.getStrIngredient1().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient1());
        }
        if (!meal.getStrIngredient2().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient2());
        }
        if (!meal.getStrIngredient3().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient3());
        }
        if (!meal.getStrIngredient4().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient4());
        }
        if (!meal.getStrIngredient5().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient5());
        }
        if (!meal.getStrIngredient6().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient6());
        }
        if (!meal.getStrIngredient7().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient7());
        }
        if (!meal.getStrIngredient8().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient8());
        }
        if (!meal.getStrIngredient9().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient9());
        }
        if (!meal.getStrIngredient10().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient10());
        }
        if (!meal.getStrIngredient11().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient11());
        }
        if (!meal.getStrIngredient12().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient12());
        }
        if (!meal.getStrIngredient13().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient13());
        }
        if (!meal.getStrIngredient14().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient14());
        }
        if (!meal.getStrIngredient15().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient15());
        }
        if (!meal.getStrIngredient16().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient16());
        }
        if (!meal.getStrIngredient17().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient17());
        }
        if (!meal.getStrIngredient18().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient18());
        }
        if (!meal.getStrIngredient19().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient19());
        }
        if (!meal.getStrIngredient20().isEmpty()) {
            ingredients.append(SPACE_DOT + meal.getStrIngredient20());
        }

        if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure1());
        }
        if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure2());
        }
        if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure3());
        }
        if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure4());
        }
        if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure5());
        }
        if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure6());
        }
        if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure7());
        }
        if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure8());
        }
        if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure9());
        }
        if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure10());
        }
        if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure11());
        }
        if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure12());
        }
        if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure13());
        }
        if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure14());
        }
        if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure15());
        }
        if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure16());
        }
        if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure17());
        }
        if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure18());
        }
        if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure19());
        }
        if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
            measures.append("\n : " + meal.getStrMeasure20());
        }


        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                intentYoutube.setData(Uri.parse(meal.getStrYoutube()));
                DetailActivity.this.startActivity(intentYoutube);
            }
        });

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSource = new Intent(Intent.ACTION_VIEW);
                intentSource.setData(Uri.parse(meal.getStrSource()));
                DetailActivity.this.startActivity(intentSource);
            }
        });

    }
}
