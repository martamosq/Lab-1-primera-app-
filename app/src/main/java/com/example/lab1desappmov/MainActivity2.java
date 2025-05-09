package com.example.lab1desappmov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab1desappmov.R;

public class MainActivity2 extends AppCompatActivity {
    Button miBoton = null;
    EditText caja = null;

    String defaultLocal = "+507";
    int limiteCodeLocal = defaultLocal.length();
    int limitePhone = 8;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showLog("Activity Created");

        // Referencias de vista
        caja = findViewById(R.id.cajaNum);
        miBoton = findViewById(R.id.button);
        EditText inputPhone = findViewById(R.id.inputPhone);
        Button btnSendWhatsapp = findViewById(R.id.btnSendWhatsapp);

        // Botón de regreso
        miBoton.setOnClickListener(v -> finish());

        // Botón para enviar a WhatsApp
        btnSendWhatsapp.setOnClickListener(view -> {
            String phone = inputPhone.getText().toString();
            sendMessageWhatsapp(phone);
        });
    }

    private void sendMessageWhatsapp(String phone) {
        String myphone = phone;
        if (myphone.length() == (limiteCodeLocal + limitePhone)) {
            String numeroPhone = myphone.substring(myphone.lastIndexOf(defaultLocal));
            System.out.println("numeroPhone = " + numeroPhone);

            Handler handler = new Handler();
            handler.post(() -> {
                String url = "https://api.whatsapp.com/send?phone=" + phone;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                finish();
            });

        } else {
            Toast.makeText(getApplicationContext(), "FAVOR INGRESE UN CONTACTO VÁLIDO", Toast.LENGTH_SHORT).show();
        }
    }

    private static final String HOME_ACTIVITY_TAG = MainActivity2.class.getSimpleName();

    private void showLog(String text) {
        Log.d(HOME_ACTIVITY_TAG, text);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        caja.setText("1");
        showLog("Activity restarted");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("Activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("Activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog("Activity paused");
        caja.setText("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("Activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("Activity is being destroyed");
    }
}

