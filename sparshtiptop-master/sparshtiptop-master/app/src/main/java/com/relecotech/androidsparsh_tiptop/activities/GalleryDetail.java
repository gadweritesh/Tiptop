package com.relecotech.androidsparsh_tiptop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.relecotech.androidsparsh_tiptop.R;
import com.relecotech.androidsparsh_tiptop.adapters.GalleryItemListAdapter;
import com.relecotech.androidsparsh_tiptop.fragments.SlideshowDialogFragment;
import com.relecotech.androidsparsh_tiptop.models.SchoolGalleryListData;

import java.util.ArrayList;

/**
 * Created by Amey on 06-04-2018.
 */

public class GalleryDetail extends AppCompatActivity {
    private ArrayList<SchoolGalleryListData> galleryListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridView galleryGridView = (GridView) findViewById(R.id.gallery_gridview);
        // Create an object of CustomAdapter and set Adapter to GirdView
        Intent intent = getIntent();
        galleryListData = intent.getParcelableArrayListExtra("GalleryListItem");

        GalleryItemListAdapter galleryItemListAdapter = new GalleryItemListAdapter(GalleryDetail.this, galleryListData);
        galleryGridView.setAdapter(galleryItemListAdapter);

        galleryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("listofGallery", galleryListData);
                bundle.putInt("position", position);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(fragmentTransaction, "slideshow");
            }
        });
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
}
