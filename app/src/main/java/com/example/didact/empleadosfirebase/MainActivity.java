package com.example.didact.empleadosfirebase;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String EXTRA_EMPLEADO="EMPLEADO";

    ListView lvEmpleados;
    ArrayList<CEmpleado> lista_empleados = new ArrayList<CEmpleado>();

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatosFirebase();

        lvEmpleados = (ListView)findViewById(R.id.lvEmpleado);




    }

    public void nuevoEmpleado(View view){

        Intent i = new Intent(getApplicationContext(),FormularioActivity.class);
        startActivity(i);

    }



    private void cargarListView (DataSnapshot dataSnapshot){

        lista_empleados.add(dataSnapshot.getValue(CEmpleado.class));

        AdaptadorEmpleado adaptadorEmpleado=new AdaptadorEmpleado(this,lista_empleados);

        lvEmpleados.setAdapter(adaptadorEmpleado);

        lvEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                CEmpleado e =((CEmpleado)parent.getItemAtPosition(position));
                String dni = e.getDni();
                String nombre = e.getNombre();
                String empleo = e.getEmpleo();
                CEmpleado empleadoenviado = new CEmpleado(dni, nombre, empleo);
                Intent i = new Intent(getApplicationContext(),FormularioActivity.class);
                i.putExtra(EXTRA_EMPLEADO, empleadoenviado);
                startActivity(i);

            }
        });

        lvEmpleados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                /*FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogEliminar dialogo = new DialogEliminar();
                    dialogo.show(fragmentManager, "dialogEliminar");*/

                CEmpleado e=((CEmpleado)parent.getItemAtPosition(position));

                String cajadni = e.getDni();

                dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");
                dbRef.child(cajadni).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null){
                            Toast.makeText(getApplicationContext(),"Eliminado correctamente", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"No se pudo eliminar", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                return true;
            }
        });
    }

    private void cargarDatosFirebase(){

        //hacemos referencia a la base de datos
        dbRef = FirebaseDatabase.getInstance().getReference().child("empleados");

        //añadimos el evento que no va a devolver los valores
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_empleados.clear();
                for (DataSnapshot empleadosDataSnapshot: dataSnapshot.getChildren()){
                    cargarListView(empleadosDataSnapshot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("MainActivity", "DATABASE ERROR");
            }
        };
        //asignamos el evento para que sea a tiempo real
        dbRef.addValueEventListener(valueEventListener);
    }



}
