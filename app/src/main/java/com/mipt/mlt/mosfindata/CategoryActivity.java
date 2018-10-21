package com.mipt.mlt.mosfindata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mipt.mlt.mosfindata.model.Category;
import com.mipt.mlt.mosfindata.ui.CategoryRecyclerAdapter;
import com.mipt.mlt.mosfindata.utils.JsonConstant;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryRecyclerAdapter.ItemClickListener {

    public static String[] titles = {"Ремонт, окраска и пошив обуви", "Ремонт часов", "Парикмахерские и косметические услуги",
            "Химическая чистка и крашение", "Фотоателье, фотоуслуги"};
    private String[] descs = {"Магазины цветов, услуги флористов", "Удобрения, скотоводческие базы",
            "Инструменты для ремонта и строительства", "Заведения быстрого питания, кафе, бары", "Товары для животных",
            "Фото мастерские", "Продуктовые магазины"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        JsonConstant.fillFromAssets(this);

        Toolbar toolbar = findViewById(R.id.category_toolbar);
        toolbar.setTitle("Выбор рубрики");

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CategoryRecyclerAdapter(createCategories(), this));
    }

    private List<Category> createCategories() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            categories.add(new Category(titles[i], descs[i]));
        }
        return categories;
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("categoryId", position);
        startActivity(intent);
    }
}
