package com.muyiwa.javadev;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    EditText eUser, ePass;
    String user, password;
    Button bLogin;
   TextView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        click = findViewById(R.id.tvclick);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        eUser = findViewById(R.id.edUsername);
        ePass = findViewById(R.id.edPassword);
        bLogin = findViewById(R.id.btnLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(eUser.getText())){
                    Toast.makeText(Login.this, "username is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(ePass.getText())) {
                    Toast.makeText(Login.this, "missing password", Toast.LENGTH_SHORT).show();
                }
                else {
                    user = eUser.getText().toString().trim();
                    password = ePass.getText().toString().trim();
                    //  Toast.makeText(Login.this, "Hello " + user + " " + password, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Login.this, "Hello " + user, Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();

                    bundle.putString("username", user);
                    bundle.putString("password", password);
                    Intent intent = new Intent(Login.this, showInput.class);
                    intent.putExtras(bundle);//go along with data in Bundle bundle
                    startActivity(intent);
                }

            }
        });
    }
}