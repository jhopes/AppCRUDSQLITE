package pd.appcrudsqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Controller.ClsUsuario;
import Modell.usuario;

public class ListaActivity extends AppCompatActivity {
    List<usuario> data = new ArrayList();;
    ListView lista;
    Cursor cursorlista;
    ClsUsuario clsus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listar();
    }
    public void listar(){
        data = new ArrayList<>();
        lista = (ListView) findViewById(R.id.lista);
        clsus = new ClsUsuario(this);
        cursorlista = clsus.readUsuario();
        if(cursorlista.moveToFirst()) {
            do {
                data.add(new usuario(cursorlista.getString(1), cursorlista.getString(2), cursorlista.getString(3)));
            } while (cursorlista.moveToNext());
        }

        ListaAdaptador adaptador = new ListaAdaptador(this);

        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final usuario us = (usuario) parent.getItemAtPosition(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListaActivity.this);
                dialog.setTitle("Alerta").setMessage("¿Esta seguro que desea eliminar?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clsus.deleteUsuario(us.getLogin().toString());
                        Snackbar.make(view, "Login "+us.getLogin(), Snackbar.LENGTH_LONG)
                                .show();
                        listar();
                    }
                }).setNegativeButton("NO",null);
                dialog.show();
            }
        });
    }
    public class ListaAdaptador extends ArrayAdapter {

        Activity context;

        ListaAdaptador(Activity context) {
            super(context, R.layout.adapter, data);
            this.context = context;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.adapter, null);

            TextView login = (TextView) item.findViewById(R.id.primerDato);
            login.setText("Login: " + data.get(position).getLogin());

            TextView clave = (TextView) item.findViewById(R.id.segundoDato);
            clave.setText("Clave: " + data.get(position).getClave());

            return(item);
        }

    }

}

