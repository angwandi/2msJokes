package com.example.demad.a2msjokes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lib2msjokes.JavaJokes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokesActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_activity);
        ButterKnife.bind(this);
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
/*
        Making the button display a toast showing a joke
        retrieved from your Java joke telling library.
*/
        JavaJokes jokesFromJavaLab = new JavaJokes();
        String jokes2ms = jokesFromJavaLab.getJokes();
        Toast.makeText(this, jokes2ms, Toast.LENGTH_LONG).show();
    }
}



