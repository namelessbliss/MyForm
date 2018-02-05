package adolfopardo.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    //ELEMENTOS UI
    private ImageButton btnConfirm;
    private Button  compartir;

    //OTROS
    private String nombre;
    private int edad;
    private int tipoDeMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //ACTIVAR FLECHAÇ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //RECOGEMOS LOS DATOS DE LOS ACTIVITIES ANTERIORES
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
        nombre = bundle.getString("nombre");
        edad = bundle.getInt("edad");
        tipoDeMensaje = bundle.getInt("option");
        }

    //INSTANCIAR LOS ELEMENTO UI

    btnConfirm = (ImageButton) findViewById(R.id.btnConfirmar);
    compartir = (Button)findViewById(R.id.TercerButton_Compartir);

        //ONLCIK DE CONFIRMAR
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,CrearMensaje(nombre,edad,tipoDeMensaje));//CREAMOS UN METODO
                Toast.makeText(ThirdActivity.this,""+nombre,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
}


    private String CrearMensaje(String nombre, int edad, int tipoDeMensaje){

        if (tipoDeMensaje == SecondActivity.SALUDO_OPTION){
            return "Hola "+nombre+" , ¿Cómo llevas esos "+edad+" años?"+"...#MyForm";
        }else{
            return "Espero verte pronto "+nombre+" , antes que cumplas "+(edad+1)+"...#Myform";
        }
    }
}
