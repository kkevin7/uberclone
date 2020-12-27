package org.kevin.uberclone.activities.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.kevin.uberclone.R;
import org.kevin.uberclone.activities.MainActivity;
import org.kevin.uberclone.providers.AuthProvider;

public class MapDriverActivity extends AppCompatActivity {

    Button mButtonLogout;
    AuthProvider mAuthProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_driver);

        mButtonLogout = findViewById(R.id.btnLogout);

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthProvider.logout();
                Intent intent = new Intent(MapDriverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}