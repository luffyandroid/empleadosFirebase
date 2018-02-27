package com.example.didact.empleadosfirebase;

/**
 * Created by DIDACT on 27/02/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class AdaptadorEmpleado extends ArrayAdapter<CEmpleado> {
    ArrayList<CEmpleado> empleados;
    Context e;
    public AdaptadorEmpleado(Context e, ArrayList<CEmpleado> empleados) {
        super(e, R.layout.item_empleado, empleados);
        this.empleados = empleados;
        this.e = e;
    }
    public View getView(int position, View view, ViewGroup
            viewGroup) {
        LayoutInflater inflater =
                LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_empleado, null);
        TextView tv_DNI = (TextView)
                item.findViewById(R.id.tvDNI);
        tv_DNI.setText(empleados.get(position).getDni() );
        TextView tv_nombre = (TextView)
                item.findViewById(R.id.tvNombre);
        tv_nombre.setText(empleados.get(position).getNombre() );
        TextView tv_empleo = (TextView)
                item.findViewById(R.id.tvEmpleo);
        tv_empleo.setText(empleados.get(position).getEmpleo() );
        return item;
    }
}
