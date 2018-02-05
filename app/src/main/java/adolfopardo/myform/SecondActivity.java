package adolfopardo.myform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    //DECLARACION ELEMENTO UI
    private SeekBar seekBarEdad;
    private TextView textViewEdad;
    private Button SegundoButton;
    private RadioButton radioButtonSaludo;
    private RadioButton radioButtonDespedida;

    //OTROS VALORES
    private String nombre="";
    private int edad=18;
    private final int MAX_EDAD=60;
    private final int MIN_EDAD=16;

    // PARA COMPARTIR
    public static final int SALUDO_OPTION=1;
    public static final int DESPEDIDA_OPTION=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // ACTIVAR FLECHA PARA VOLVER
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // RECOGER EL STRING NOMBRE DEL ACTIVITY ANTERIOR
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            nombre = bundle.getString("nombre");
        }

        // INSTANCIAR LOS ELEMENTOS UI

        seekBarEdad = (SeekBar)findViewById(R.id.seekBarEdad);
        textViewEdad = (TextView)findViewById(R.id.textViewEdadActual);
        SegundoButton = (Button)findViewById(R.id.SegundoButton);
        radioButtonSaludo = (RadioButton)findViewById(R.id.radioButtonSaludo);
        radioButtonDespedida = (RadioButton)findViewById(R.id.radioButtonDespedida);

        //EVENTO PARA CREAR EL SEEKBAR
        seekBarEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int edadActual, boolean b) {
                // Declaramos nuestras restricciones de edad en el evento en que el usuario suelta/deja el seekbar.
                edad = edadActual;
                textViewEdad.setText(edad+ " Años ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Aunque no lo sobreescribamos con alguna funcionalidad, OnSeekBarChangeListener es una interfaz
                // y como interfaz que es, necesitamos sobreescribir todos sus métodos, aunque lo dejemos vacío.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Declaramos nuestras restricciones de edad en el evento en que el usuario suelta/deja el seekbar.
                edad = seekBarEdad.getProgress();
                textViewEdad.setText(edad +"");

                if (edad > MAX_EDAD){
                    SegundoButton.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this,"La edad maxima permitible es: "+MAX_EDAD+" años",Toast.LENGTH_SHORT).show();
                }else if (edad < MIN_EDAD){
                    SegundoButton.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this,"La edad minima permitible es: "+MIN_EDAD+" años",Toast.LENGTH_SHORT).show();
                }else{
                    SegundoButton.setVisibility(View.VISIBLE);
                }


            }
        });

        // EVENTO ONCLICK DEL BOTON SEGUNDO PARA PASAR AL SIGUIENTE ACTIVITY
        SegundoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("edad",edad);
                // Si el botón de greeter esta activo, option valdrá 1, si no, 2
                int option = (radioButtonSaludo.isChecked()) ? SALUDO_OPTION : DESPEDIDA_OPTION; //OPERADOR TERNARIO
                intent.putExtra("option", option);
                Toast.makeText(SecondActivity.this,""+nombre,Toast.LENGTH_SHORT).show();
                startActivity(intent);
                Toast.makeText(SecondActivity.this,seekBarEdad.getProgress()+"",Toast.LENGTH_LONG).show();
            }
        });


    }
}
