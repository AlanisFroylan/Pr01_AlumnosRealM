package tec.com.pr01_alumnosrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    ImageButton btnGuardar, btnMostrar, btnBorrar,btn_update;
    EditText txNom, txApe, txCar, txCon, txMail;
    ListView list;

    Alumnos alumnos;
    ArrayAdapter <AlumnosModels> modelsAdapter;
    ArrayList <AlumnosModels> listaAlumno= new ArrayList<>();

    Realm realm;
    RealmResults<Alumnos>result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txNom=(EditText)findViewById(R.id.nombre);
        txApe=(EditText)findViewById(R.id.apellidos);
        txCar=(EditText)findViewById(R.id.carrera);
        txCon=(EditText)findViewById(R.id.control);
        txMail=(EditText)findViewById(R.id.email);

        btnGuardar=(ImageButton) findViewById(R.id.btn_guardar);
        btnMostrar=(ImageButton) findViewById(R.id.btnMostrar);
        btnBorrar=(ImageButton) findViewById(R.id.btnEliminar);
        btn_update=(ImageButton)findViewById(R.id.btnUpdate);


        list=(ListView)findViewById(R.id.listview);

        alumnos= new Alumnos();
        realm=realm.getInstance(this);

        result=realm.where(Alumnos.class).findAll();
        if(result.size()!=0){
            obtenerAlumno();
        }
        modelsAdapter=new ArrayAdapter<AlumnosModels>(MainActivity.this,android.R.layout.simple_list_item_1,listaAlumno);
        list.setAdapter(modelsAdapter);



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaText()) {
                    guardarAlumno();
                }

            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarAlumno();

            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAlumno();

            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarAlumno();
            }
        });


    }

    private void obtenerAlumno() {
        listaAlumno.clear();
        for(int i=0;i<result.size();i++){
            listaAlumno.add(new AlumnosModels(
                    result.get(i).getControl(),
                    result.get(i).getNombre(),
                    result.get(i).getApellidos(),
                    result.get(i).getCarrera(),
                    result.get(i).getEmail()

            ));
        }
    }
    private void guardarAlumno(){
        String no_Contro=txCon.getText().toString();
        RealmResults<Alumnos> Buscar=realm.where(Alumnos.class).equalTo("control",no_Contro).findAll();

        if(Buscar.size()==0) {
            alumnos.setControl(txCon.getText().toString());
            alumnos.setNombre(txNom.getText().toString());
            alumnos.setApellidos(txApe.getText().toString());
            alumnos.setCarrera(txCar.getText().toString());
            alumnos.setEmail(txMail.getText().toString());

            realm.beginTransaction();
            realm.copyToRealm(alumnos);
            realm.commitTransaction();

            obtenerAlumno();
            modelsAdapter.notifyDataSetChanged();

            limpiarText(txCon);
            limpiarText(txNom);
            limpiarText(txApe);
            limpiarText(txCar);
            limpiarText(txMail);

        }else{
            Toast.makeText(this, "El No.Control ya existe", Toast.LENGTH_SHORT).show();
        }




    }
    private void eliminarAlumno(){
        if(TextUtils.isEmpty(txCon.getText())){
            txCon.setError(String.format(getString(R.string.Ingresar),getString(R.string.control)));
        }else{
            String no_Contro=txCon.getText().toString();
            RealmResults<Alumnos> resultDelete=realm.where(Alumnos.class).equalTo("control",no_Contro).findAll();
            realm.beginTransaction();
            resultDelete.removeLast();
            realm.commitTransaction();
            obtenerAlumno();
            modelsAdapter.notifyDataSetChanged();
        }

    }
    private void mostrarAlumno(){
        if(TextUtils.isEmpty(txCon.getText())){
            txCon.setError(String.format(getString(R.string.Ingresar),getString(R.string.control)));

        }else{
            String no_Control=txCon.getText().toString();
            RealmResults<Alumnos> resultQuery=realm.where(Alumnos.class).equalTo("control",no_Control).findAll();
            if(resultQuery.size()!=0){
                txNom.setText(resultQuery.get(0).getNombre());
                txApe.setText(resultQuery.get(0).getApellidos());
                txCar.setText(resultQuery.get(0).getCarrera());
                txMail.setText(resultQuery.get(0).getEmail());
            }
        }
    }
    public boolean validaText(){
        boolean esvalido=true;
        if(TextUtils.isEmpty(txCon.getText())){
            esvalido=false;
            txCon.setError(String.format(getString(R.string.Ingresar),getString(R.string.control)));
        }
        if(TextUtils.isEmpty(txNom.getText())){
            esvalido=false;
            txNom.setError(String.format(getString(R.string.Ingresar),getString(R.string.nom)));
        }
        if(TextUtils.isEmpty(txApe.getText())){
            esvalido=false;
            txApe.setError(String.format(getString(R.string.Ingresar),getString(R.string.ape)));
        }
        if(TextUtils.isEmpty(txCar.getText())){
            esvalido=false;
            txCar.setError(String.format(getString(R.string.Ingresar),getString(R.string.carrera)));
        }
        if(TextUtils.isEmpty(txMail.getText())){
            esvalido=false;
            txMail.setError(String.format(getString(R.string.Ingresar),getString(R.string.Email)));
        }
        return esvalido;
    }
    public void limpiarText(EditText t){
        t.setText("");
    }

    private void ActualizarAlumno(){
        String no_Contro=txCon.getText().toString();
        RealmResults<Alumnos> verificar=realm.where(Alumnos.class).equalTo("control",no_Contro).findAll();

        if(verificar.size()==1){
            alumnos.setControl(txCon.getText().toString());
            alumnos.setNombre(txNom.getText().toString());
            alumnos.setApellidos(txApe.getText().toString());
            alumnos.setCarrera(txCar.getText().toString());
            alumnos.setEmail(txMail.getText().toString());

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(alumnos);
            realm.commitTransaction();

            obtenerAlumno();
            modelsAdapter.notifyDataSetChanged();

            limpiarText(txCon);
            limpiarText(txNom);
            limpiarText(txApe);
            limpiarText(txCar);
            limpiarText(txMail);


        }else{
            Toast.makeText(this, "El No.Control no existe!!!", Toast.LENGTH_SHORT).show();
        }

    }
}
