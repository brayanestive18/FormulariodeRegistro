package com.brayadiaz.formularioderegistro;

import android.app.DatePickerDialog;
import android.icu.text.LocaleDisplayNames;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private String user = "", pass1 = "", pass2 = "", email = "", date = "", sexo = "",
            hobbies = "", ciudad;
    private int cDay, cMonth, cYear;
    private EditText eUser, ePass1, ePass2, e_mail,eFecha;
    private RadioButton female, male;
    private CheckBox jugar, bailar, cocinar, leer;
    private Button aceptar;
    private Spinner sCiudades;
    private TextView Info;

    Calendar Cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUser = (EditText) findViewById(R.id.cUser);
        ePass1 = (EditText) findViewById(R.id.cPass1);
        ePass2 = (EditText) findViewById(R.id.cPass2);
        e_mail = (EditText) findViewById(R.id.cEmail);
        eFecha = (EditText) findViewById(R.id.cDate);

        female = (RadioButton) findViewById(R.id.femenino);
        male = (RadioButton) findViewById(R.id.masculino);

        jugar = (CheckBox) findViewById(R.id.jugar);
        bailar = (CheckBox) findViewById(R.id.bailar);
        cocinar = (CheckBox) findViewById(R.id.cocinar);
        leer = (CheckBox) findViewById(R.id.leer);

        aceptar = (Button) findViewById(R.id.boton);

        sCiudades = (Spinner) findViewById(R.id.cCity);

        Info = (TextView) findViewById(R.id.info);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);

        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cDay = Cal.get(Calendar.DAY_OF_MONTH);
        cMonth = Cal.get(Calendar.MONTH);
        cYear = Cal.get(Calendar.YEAR);

        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                Cal.set(Calendar.YEAR, year);
                Cal.set(Calendar.MONTH, monthOfYear);
                Cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MMM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

                //eFecha.setText(sdf.format(Cal.getTime()));
                date = sdf.format(Cal.getTime());
                eFecha.setText(date);
                //date = Integer.toString(cDay) + "/" + Integer.toString(cMonth+1) + "/" + Integer.toString(cYear);
            }
        };

        eFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog(0);
                new DatePickerDialog(MainActivity.this, datePickerListener, cYear, cMonth,
                        cDay).show();
            }
        });

    }

    public void Aceptar(View view) {

        user = eUser.getText().toString();
        pass1 = ePass1.getText().toString();
        pass2 = ePass2.getText().toString();
        email = e_mail.getText().toString();

        if(user.equals("")){
            Toast.makeText(getApplicationContext(), "Ingrese Usuario, Por Favor", Toast.LENGTH_SHORT).show();
        }

        else if (pass1.equals("")){
            Toast.makeText(getApplicationContext(), "Ingrese La contraseña, Por Favor", Toast.LENGTH_SHORT).show();
        }

        else if (!(pass2.equals(pass1))){
            Toast.makeText(getApplicationContext(), "Las Contraseñas No son Iguales!!", Toast.LENGTH_SHORT).show();
        }

        else if(email.equals("")){
            Toast.makeText(getApplicationContext(), "Ingrese el Correo, Por Favor", Toast.LENGTH_SHORT).show();
        }

        else if(date.equals("")){
            Toast.makeText(getApplicationContext(), "Ingrese La Fecha de Nacimiento, Por Favor", Toast.LENGTH_SHORT).show();
        }

        else if(!(jugar.isChecked() || bailar.isChecked() || cocinar.isChecked() || leer.isChecked())){
            Toast.makeText(getApplicationContext(), "Seleccione un Hobby, Por Favor", Toast.LENGTH_SHORT).show();
        }

        else {

            hobbies = ""; //Evita que se acomulen los hobbies si le dan muhcas veces click en aceptar

            if (male.isChecked()) {
                sexo = "Masculino";
            } else {
                sexo = "Femenino";
            }

            if (jugar.isChecked()) {
                hobbies += "Jugar ";
            }
            if (bailar.isChecked()) {
                hobbies += "Bailar ";
            }
            if (cocinar.isChecked()) {
                hobbies += "Cocinar ";
            }
            if (leer.isChecked()) {
                hobbies += "Leer ";
            }

            Info.setText("Información \n" + "Usuario: " + user + "\nContraseña: " + pass1 + "\nCorreo: "
            + email + "\nSexo: " + sexo + "\nFecha de Nacimiento: " + date + "\nLugar de Nacimiento: " +
            ciudad + "\nHobbies: " + hobbies);
        }

    }
}
