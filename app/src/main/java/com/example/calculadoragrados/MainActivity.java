package com.example.calculadoragrados;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import nav.centigrado;
import nav.farenheit;
import nav.kelvin;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText = findViewById(R.id.editTextText);
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        RadioButton radio_c = findViewById(R.id.radio_option1);
        RadioButton radio_f = findViewById(R.id.radio_option2);
        RadioButton radio_k = findViewById(R.id.radio_option3);

        button.setOnClickListener(view -> {
            Double valor = Double.parseDouble(editText.getText().toString());

            centigrado centigrado = new centigrado(valor);
            farenheit farenheit = new farenheit(valor);
            kelvin kelvin = new kelvin(valor);

            if (radio_c.isChecked())
            {
                textView.setText("Farenheit = " + farenheit.parse(centigrado).getValor() + "\nKelvin = " + kelvin.parse(centigrado).getValor());
            }
            else if (radio_f.isChecked())
            {
                textView.setText("Centigrado = " + centigrado.parse(farenheit).getValor() + "\nKelvin = " + kelvin.parse(farenheit).getValor());
            }
            else if (radio_k.isChecked())
            {
                textView.setText("Centigrado = " + centigrado.parse(kelvin).getValor() + "\nFarenheit = " + farenheit.parse(kelvin).getValor());
            }
        });
    }
}