package com.example.apppractica07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class LstActivity extends AppCompatActivity {
    private ListView lstAlumnos;
    private SearchView srcLista;
    private TextView lblUser;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst);

        srcLista = (SearchView) findViewById(R.id.menu_search);
        lstAlumnos = (ListView) findViewById(R.id.lstAlumnos);
        lblUser = (TextView) findViewById(R.id.lblUsuario);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.alumnos));
        lstAlumnos.setAdapter(adapter);


        //Bundle datos = getIntent().getExtras(); //Toma el conjunto de extras que manda la clase utilizando el intent
        //lblUser.setText(datos.getString("usuario")); //Toma el String de datos con el identificador
        Bundle datos = getIntent().getExtras();
        Usuarios user = (Usuarios) datos.getSerializable("usuario");
        lblUser.setText(user.getNombreCompleto());

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchview, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}