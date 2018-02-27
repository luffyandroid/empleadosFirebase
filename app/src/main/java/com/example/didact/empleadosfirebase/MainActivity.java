package com.example.didact.empleadosfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String EXTRA_EMPLEADO="EMPLEADO";

    ListView lvEmpleados;
    ArrayList<CEmpleado> lista_empleados = new ArrayList<CEmpleado>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();

        lvEmpleados = (ListView)findViewById(R.id.lvEmpleado);

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

                Toast.makeText(getApplicationContext(),"Holis!", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    public void nuevoEmpleado(View view){

        Intent i = new Intent(getApplicationContext(),FormularioActivity.class);
        startActivity(i);

    }

    private void cargarDatos(){
        lista_empleados.add(new CEmpleado("53584146B","Luis Jesus","programador"));
        lista_empleados.add(new CEmpleado("53564146Q","Pepe","cocinero"));
        lista_empleados.add(new CEmpleado("53546296E","Manuel","en paro"));
    }

}
