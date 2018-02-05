package adolfopardo.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//DECLARACION DE ELEMENTOS DE LA UI
    private EditText PrimerEditText;
    private Button PrimerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FORZAR MOSTRAR ICONO EN EL ACTIVITY MAIN
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //INSTANCIAMOS LOS ELEMENTOS DE LA UI
        PrimerEditText = (EditText)findViewById(R.id.PrimerEditText);
        PrimerButton = (Button)findViewById(R.id.PrimerButton);

        //ONLCICK PARA PASAR AL SIGUIENTE ACTIVITY
        PrimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = PrimerEditText.getText().toString();
                if(nombre !=null && !nombre.isEmpty()){
                    //CREAMOS EL INTENT EXPLICITO, DE DONDE A DONDE VAMOS
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("nombre",nombre);
                    Toast.makeText(MainActivity.this,""+nombre,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Escriba su nombre",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
