package br.com.k19.android.prj_extra_aval00;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/***
 * @author renan
 */
public class MainActivity extends AppCompatActivity {

    private Button btnNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovo = findViewById(R.id.btnNovo);

        btnNovo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}