package org.kevin.uberclone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.kevin.uberclone.R;
import org.kevin.uberclone.activities.client.RegisterActivity;
import org.kevin.uberclone.activities.driver.RegisterDriverActivity;
import org.kevin.uberclone.includes.MyToolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {
    SharedPreferences mPref;

    Toolbar mToolbar;
    Button mButtonGoToLogin;
    Button mButtonGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);
        //Toolbar
        MyToolbar.show(this, "Seleccionar Opción", true);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        mButtonGoToLogin = findViewById(R.id.btnGoToLogin);
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        
        mButtonGoToRegister = findViewById(R.id.btnGoToRegister);
        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
        
    }

    private void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToRegister(){
        String typeUser = mPref.getString("user", "");
        Intent intent;
        if(typeUser.equals("client")){
            intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
        }else{
            intent = new Intent(SelectOptionAuthActivity.this, RegisterDriverActivity.class);
        }
        startActivity(intent);
    }

}