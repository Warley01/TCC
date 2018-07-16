package br.edu.ifg.util;

import android.app.Application;


/**
 * Classe para definir o contexto em qualquer Activity
 * Esta classe deve ser declarada no manifesto como:
 * android:name="util.MyApp"
 */
public class MyApp extends Application{

    //atributo estático
    private static MyApp context;

    public MyApp () {
        //O atributo recebe o contexto
        context = this;
    }

    //Método para obter o contexto atual
    public static MyApp getContext(){
        return context;
    }

}//fim da classe
