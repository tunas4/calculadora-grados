package com.example.calculadoragrados;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        EditText editText = findViewById(R.id.editTextText);
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        RadioButton radio_option1 = findViewById(R.id.radio_option1);
        RadioButton radio_option2 = findViewById(R.id.radio_option2);
        RadioButton radio_option3 = findViewById(R.id.radio_option3);

        button.setOnClickListener(view -> {
            String inputText = editText.getText().toString();
            double inputValue;

            try
            {
                inputValue = Double.parseDouble(inputText);
            }
            catch (NumberFormatException e)
            {
                textView.setText("Por favor, ingrese un número válido.");
                return;
            }

            if (radio_option1.isChecked()) { // Centigrado
                Centigrado centigrado = new Centigrado(inputValue);
                double farenheitValue = new Farenheit(centigrado.getTemperatura()).parse(centigrado);
                double kelvinValue = new Kelvin(centigrado.getTemperatura()).parse(centigrado);
                textView.setText("Centígrados: " + centigrado.getTemperatura() + "\nFahrenheit: " + farenheitValue + "\nKelvin: " + kelvinValue);

            } else if (radio_option2.isChecked()) { // Fahrenheit
                Farenheit farenheit = new Farenheit(inputValue);
                double centigradoValue = new Centigrado(farenheit.getTemperatura()).parse(farenheit);
                double kelvinValue = new Kelvin(farenheit.getTemperatura()).parse(farenheit);
                textView.setText("Fahrenheit: " + farenheit.getTemperatura() + "\nCentígrados: " + centigradoValue + "\nKelvin: " + kelvinValue);

            } else if (radio_option3.isChecked()) { // Kelvin
                Kelvin kelvin = new Kelvin(inputValue);
                double centigradoValue = new Centigrado(kelvin.getTemperatura()).parse(kelvin);
                double farenheitValue = new Farenheit(kelvin.getTemperatura()).parse(kelvin);
                textView.setText("Kelvin: " + kelvin.getTemperatura() + "\nCentígrados: " + centigradoValue + "\nFahrenheit: " + farenheitValue);

            } else {
                textView.setText("Por favor, seleccione una opción.");
            }
        });
    }

    public class Centigrado
    {
        private double temperatura;
        public Centigrado(double temperatura)
        {
            this.temperatura = temperatura;
        }
        public double getTemperatura()
        {
            return temperatura;
        }
        public Centigrado parse(Farenheit farenheit)
        {
            return (farenheit.getTemperatura() - 32) * 5 / 9;
        }
        public Centigrado parse(Kelvin kelvin)
        {
            return kelvin.getTemperatura() - 273.15;
        }
    }

    public class Farenheit
    {
        private double temperatura;
        public Farenheit(double temperatura)
        {
            this.temperatura = temperatura;
        }
        public double getTemperatura()
        {
            return temperatura;
        }
        public double parse(Centigrado centigrado)
        {
            return centigrado.parse(this) * 9 / 5 + 32;
        }
        public double parse(Kelvin kelvin)
        {
            return (kelvin.getTemperatura() - 273.15) * 9 / 5 + 32;
        }
    }

    public class Kelvin
    {
        private double temperatura;
        public Kelvin(double temperatura)
        {
            this.temperatura = temperatura;
        }
        public double getTemperatura()
        {
            return temperatura;
        }
        public double parse(Centigrado centigrado)
        {
            return centigrado.parse(this) + 273.15;
        }
        public double parse(Farenheit farenheit)
        {
            return (farenheit.getTemperatura() - 32) * 5 / 9 + 273.15;
        }
    }

}