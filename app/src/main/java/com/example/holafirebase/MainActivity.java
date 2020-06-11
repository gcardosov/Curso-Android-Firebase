package com.example.holafirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

        private static final String PATH_START = "start";
        private static final String PATH_MESSAGE = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvMessage = findViewById(R.id.tvMessage);
        //Variable para la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Referencia a la ruta a la cual queremos ller o escribir el mensaje "nodos"
        //Recibe como parametros las ramas que declaramos al inicio en este caso start
        //El nodo padre seria start y el nodo hojo seria message
        final DatabaseReference reference = database.getReference(PATH_START).child(PATH_MESSAGE);

        //Vamos a agregar el evento que nos permita capturar el evento donde se agrega el nuevo valor
        //
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //Se ejecutara cuando exista un cambio en la referencia establecida anteriormente de las
            //ramas que indicamos
            //regresa un objetivo de tipo data snapshot que devolvera todos los valores de esa ruta
            //se extrae el valor como un string
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvMessage.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            //En caso de tener algun error enviamos notificacion
            //con un toast
            
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al consultar en FB", Toast.LENGTH_LONG).show();
            }
        });

    }
}
