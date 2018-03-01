package com.example.didact.empleadosfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FormularioActivity extends AppCompatActivity {

    EditText etDNI, etNombre, etEmpleo;
    Button btnInsertar, btnModificar;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;


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
            etDNI.setEnabled(false);
        }else{
            btnInsertar.setEnabled(true);
        }
    }

    public void insertar(View view){

        String dni = etDNI.getText().toString();
        String nombre = etNombre.getText().toString();
        String empleo = etEmpleo.getText().toString();

        if (dni.equals("") || nombre.equals("") || empleo.equals("")){
            Toast.makeText(getApplicationContext(), "Debes rellenar los campos",Toast.LENGTH_LONG).show();
        }else{
            CEmpleado nuevoEmpleado=new CEmpleado(dni, nombre, empleo);
            dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

            dbRef.child(dni).setValue(nuevoEmpleado, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"Insertado correctamente",Toast.LENGTH_LONG).show();
                        limpiarFormulario();

                    }else{

                        Toast.makeText(getApplicationContext(),"No se puede insertar el jugador",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    public void modificar(View view){
        String dni = etDNI.getText().toString();
        String nombre = etNombre.getText().toString();
        String empleo = etEmpleo.getText().toString();


        if (dni.equals("") || nombre.equals("") || empleo.equals("")){
            Toast.makeText(getApplicationContext(), "Debes rellenar los campos",Toast.LENGTH_LONG).show();
        }else{
            CEmpleado nuevoEmpleado=new CEmpleado(dni, nombre, empleo);
            dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

            dbRef.child(dni).setValue(nuevoEmpleado, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"Modificado correctamente",Toast.LENGTH_LONG).show();
                        limpiarFormulario();

                    }else{

                        Toast.makeText(getApplicationContext(),"No se puede insertar el jugador",Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

    private void limpiarFormulario(){
        etNombre.setText("");
        etDNI.setText("");
        etEmpleo.setText("");
    }

}
