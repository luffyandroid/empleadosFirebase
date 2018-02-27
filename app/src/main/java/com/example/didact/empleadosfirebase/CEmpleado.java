package com.example.didact.empleadosfirebase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DIDACT on 27/02/2018.
 */

public class CEmpleado implements Parcelable{

    String dni;
    String nombre;
    String empleo;

    public CEmpleado(){

    }

    public static final Parcelable.Creator<CEmpleado> CREATOR = new
            Parcelable.Creator<CEmpleado>(){
                public CEmpleado createFromParcel(Parcel in){
                    return new CEmpleado(in);
                }

                public CEmpleado[] newArray(int size){
                    return new CEmpleado[size];
                }
            };

    public CEmpleado(String dni, String nombre, String empleo) {
        this.dni = dni;
        this.nombre = nombre;
        this.empleo = empleo;
    }

    public CEmpleado(Parcel p){
        readFromParcel(p);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dni);
        dest.writeString(this.nombre);
        dest.writeString(this.empleo);
    }

    private void readFromParcel(Parcel p){
        this.dni=p.readString();
        this.nombre=p.readString();
        this.empleo=p.readString();
    }
}


