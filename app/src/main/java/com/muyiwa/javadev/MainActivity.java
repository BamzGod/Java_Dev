package com.muyiwa.javadev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b = findViewById(R.id.btnC);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(MainActivity.this, "Anything You Like", Toast.LENGTH_SHORT).show();
                 Toast.makeText(MainActivity.this, "Welcome on your Adventure", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Welcome.class);
               // Intent intent2 = new Intent(MainActivity.this, GetStarted.class);
                startActivity(intent);
//                startActivity(intent2);
            }
        });
    }
}