package org.kevin.uberclone.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.kevin.uberclone.R;

public class MyToolbar {

    public static void show(AppCompatActivity activity, String title, boolean upBotton){
        //Toolbar
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upBotton);
    }
}
