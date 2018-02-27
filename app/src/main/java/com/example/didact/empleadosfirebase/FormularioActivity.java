package com.example.didact.empleadosfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    EditText etDNI, etNombre, etEmpleo;
    Button btnInsertar, btnModificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etDNI = (EditText)findViewById(R.id.etDNI);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etEmpleo = (EditText)findViewById(R.id.etEmpleo);
        btnInsertar = (Button)findViewById(R.id.btnInsetar);
        btnModificar = (Button)findViewById(R.id.btnModificar);

        Bundle b= getIntent().getExtras();

        if (b!=null) {

            CEmpleado e = b.getParcelable(MainActivity.EXTRA_EMPLEADO);
            etDNI.setText(e.getDni());
            etNombre.setText(e.getNombre());
            etEmpleo.setText(e.getEmpleo());
            btnModificar.setEnabled(true);
        }else{
            btnInsertar.setEnabled(true);
        }
    }

    public void insertar(View view){

        String DNI = etDNI.getText().toString();
        String nombre = etNombre.getText().toString();
        String empleo = etEmpleo.getText().toString();

        if (DNI.equals("") || nombre.equals("") || empleo.equals("")){
            Toast.makeText(getApplicationContext(), "Debes rellenar los campos",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Se pasan los datos a la base de datos",Toast.LENGTH_LONG).show();
        }
    }

    public void modificar(View view){
        String DNI = etDNI.getText().toString();
        String nombre = etNombre.getText().toString();
        String empleo = etEmpleo.getText().toString();

        if (DNI.equals("") || nombre.equals("") || empleo.equals("")){
            Toast.makeText(getApplicationContext(), "Debes rellenar los campos",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Se modifican los datos de la base de datos",Toast.LENGTH_LONG).show();
        }
    }

}
