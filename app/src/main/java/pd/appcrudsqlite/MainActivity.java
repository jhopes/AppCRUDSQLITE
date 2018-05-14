package pd.appcrudsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import Controller.ClsUsuario;
import Modell.usuario;

public class MainActivity extends AppCompatActivity {
    static ClsUsuario clsus;
    static usuario us;
    EditText edtuser, edtclave, edtrclave;
    RadioButton rbactivo, rbinactivo;
    String estado;
    Cursor curlisusuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtuser = (EditText) findViewById(R.id.edtuser);
        edtclave = (EditText) findViewById(R.id.edtclave);
        edtrclave = (EditText) findViewById(R.id.edtrclave);
        rbactivo = (RadioButton) findViewById(R.id.rbactivo);
        rbinactivo = (RadioButton) findViewById(R.id.rbinactivo);

       if(rbactivo.isChecked()==true){
            estado ="1";
        }else if(rbinactivo.isChecked()==true){
           estado ="0";
       }
    }
    public void guardar(View view){
        clsus = new ClsUsuario(this);
        us = new usuario();
        curlisusuario = clsus.readUsuario(edtuser.getText().toString());
        //clsus.close();
        if(curlisusuario.moveToFirst()) {
            if (!(curlisusuario.getString(1).equals(edtuser.getText().toString()))) {
                if (edtclave.getText().toString().equals(edtrclave.getText().toString())) {

                    us.setLogin(edtuser.getText().toString());
                    us.setClave(edtclave.getText().toString());
                    us.setEstado(estado);
                    clsus.createUsuario(us);
                    clsus.close();
                    limpiar();
                    Snackbar.make(view, "Se inserto correctamente", Snackbar.LENGTH_LONG)
                            .show();

                } else {
                    Snackbar.make(view, "Las claves no coinciden", Snackbar.LENGTH_LONG)
                            .show();

                }
            } else {
                if (edtclave.getText().toString().equals(edtrclave.getText().toString())) {

                    us.setLogin(edtuser.getText().toString());
                    us.setClave(edtclave.getText().toString());
                    us.setEstado(estado);
                    clsus.updateUsuario(us);
                    clsus.close();
                    limpiar();
                    Snackbar.make(view, "Se actualizo correctamente", Snackbar.LENGTH_LONG)
                            .show();

                } else {
                    Snackbar.make(view, "Las claves no coinciden", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        }else{
            if (edtclave.getText().toString().equals(edtrclave.getText().toString())) {

                us.setLogin(edtuser.getText().toString());
                us.setClave(edtclave.getText().toString());
                us.setEstado(estado);
                clsus.createUsuario(us);
                clsus.close();
                limpiar();
                Snackbar.make(view, "Se inserto correctamente", Snackbar.LENGTH_LONG)
                        .show();

            } else {
                Snackbar.make(view, "Las claves no coinciden", Snackbar.LENGTH_LONG)
                        .show();

            }
        }
    }
    public void verLista(View view) {

        Intent intent = new Intent(this, ListaActivity.class);
        startActivity(intent);
        //this.finish();
    }
    public void limpiar(){
        edtuser.setText("");
        edtclave.setText("");
        edtrclave.setText("");
        rbactivo.setChecked(true);
        rbinactivo.setChecked(false);
    }
}
