package mx.edu.ittepic.tadm_t2_labsharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener{

    String nombre,telefono,direccion,fecha,hora,platillos,postres;
    SeekBar meseros;
    String eleccion,ad1,ad2;
    Integer numeroseek=0;

    public static final String STORAGE_NAME = "MySharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = ((EditText) findViewById(R.id.ed1)).getText().toString();
        telefono = ((EditText) findViewById(R.id.ed2)).getText().toString();
        direccion = ((EditText) findViewById(R.id.ed3)).getText().toString();
        fecha = ((EditText) findViewById(R.id.ed4)).getText().toString();
        hora = ((EditText) findViewById(R.id.ed5)).getText().toString();
        platillos = ((EditText) findViewById(R.id.ed7)).getText().toString();
        postres = ((EditText) findViewById(R.id.ed8)).getText().toString();
        meseros = findViewById(R.id.sb);

      /*  basico = findViewById(R.id.cb1);
        lujo = findViewById(R.id.cb2);
        guardar = findViewById(R.id.guardar);
        salvar = findViewById(R.id.recuperar);*/

        meseros.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = numeroseek;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Numero de Meseros:" + progressChangedValue, Toast.LENGTH_SHORT).show();

                numeroseek = progressChangedValue;
            }
        });

        eleccion = "";
        ad1 = "";
        ad2 = "";
    }
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonId);
        eleccion = radioButton.getText().toString();
    }
    // Add other methods

    public void onCheckboxClicked(View view) {

        CheckBox chb1 =  findViewById(R.id.cb1);
        CheckBox chb2 =  findViewById(R.id.cb2);

        StringBuilder sb = new StringBuilder();

        if (chb1.isChecked()) {
            sb.append(", " + chb1.getText());
        }

        if (chb2.isChecked()) {
            sb.append(", " + chb2.getText());
        }


        if (sb.length() > 0) {
            ad1 = sb.deleteCharAt(sb.indexOf(",")).toString();
        } else {
            ad1 = "";
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ad2 = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void guardar(View view) {
        nombre = ((EditText)findViewById(R.id.ed1)).getText().toString();
        telefono = ((EditText)findViewById(R.id.ed2)).getText().toString();
        direccion = ((EditText)findViewById(R.id.ed3)).getText().toString();
        fecha = ((EditText)findViewById(R.id.ed4)).getText().toString();
        hora = ((EditText)findViewById(R.id.ed5)).getText().toString();
        platillos = ((EditText)findViewById(R.id.ed7)).getText().toString();
        postres = ((EditText)findViewById(R.id.ed8)).getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("telefono", telefono);
        editor.putString("direccion", direccion);
        editor.putString("fecha", fecha);
        editor.putString("hora", hora);
        editor.putString("platillos", platillos);
        editor.putString("postres", postres);

        editor.putString("gender", eleccion);
        editor.putString("hobbies", ad1);
        editor.putString("zodiac", ad2);
        editor.putInt("numeseek", numeroseek);

        editor.apply();

        Toast.makeText(getApplicationContext(), "Datos Guardados", Toast.LENGTH_SHORT).show();

    }

    public void salvar(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        nombre = sharedPreferences.getString("nombre", "");
        telefono = sharedPreferences.getString("telefono", "");
        direccion = sharedPreferences.getString("direccion", "");
        fecha = sharedPreferences.getString("fecha", "");
        hora = sharedPreferences.getString("hora", "");
        platillos = sharedPreferences.getString("platillos", "");
        postres = sharedPreferences.getString("postres", "");

        eleccion = sharedPreferences.getString("eleccion", "");
        ad1 = sharedPreferences.getString("hobbies", "");
        ad2 = sharedPreferences.getString("zodiac", "");
        numeroseek = sharedPreferences.getInt("numeseek",numeroseek);

        setupUI();
    }

    protected void setupUI(){
        ((EditText)findViewById(R.id.ed1)).setText(nombre);
        ((EditText)findViewById(R.id.ed2)).setText(telefono);
        ((EditText)findViewById(R.id.ed3)).setText(direccion);
        ((EditText)findViewById(R.id.ed4)).setText(fecha);
        ((EditText)findViewById(R.id.ed5)).setText(hora);
        ((EditText)findViewById(R.id.ed7)).setText(platillos);
        ((EditText)findViewById(R.id.ed8)).setText(postres);// Add code here

        ((SeekBar)findViewById(R.id.sb)).setProgress(numeroseek);

        CheckBox chkCoding = findViewById(R.id.cb1);
        CheckBox chkWriting = findViewById(R.id.cb2);


        chkCoding.setChecked(false);
        chkWriting.setChecked(false);


        if (ad1.contains("Basica")) {
            chkCoding.setChecked(true);
        }

        if (ad2.contains("Lujo")) {
            chkWriting.setChecked(true);
        }
    }
}
