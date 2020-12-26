package org.kevin.uberclone.activities.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.kevin.uberclone.R;
import org.kevin.uberclone.includes.MyToolbar;
import org.kevin.uberclone.models.Client;
import org.kevin.uberclone.providers.AuthProvider;
import org.kevin.uberclone.providers.ClientProvider;

import dmax.dialog.SpotsDialog;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences mPref;

    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;

    //Views
    Toolbar mToolbar;
    AlertDialog mDialog;
    ImageView imgRegister;
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyToolbar.show(this, "Register", true);

        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        String selectedUser = mPref.getString("user", "");
        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espere un momento").build();

        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        imgRegister = findViewById(R.id.imgRegister);

        if(selectedUser == "driver"){
            imgRegister.setImageDrawable(getDrawable(R.drawable.icon_driver));
        }

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRegister();
            }
        });
    }

    private void clickRegister() {
        final String name = mTextInputName.getText().toString();
        final String email = mTextInputEmail.getText().toString();
        final String password = mTextInputPassword.getText().toString();

        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if(password.length() >= 6){
                mDialog.show();
                register(name, email, password);
            }else{
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void register(String name, String email, String password){
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Client client = new Client(id, name, email);
                    create(client);
                }else{
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
    }

    void create(Client client){
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}