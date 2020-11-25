package br.com.k19.android.prj_extra_aval00;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/***
 * @author renan
 */
public class InsertActivity extends AppCompatActivity {

    private EditText edtTelefone;
    private RadioGroup rgTamanho;
    private RadioGroup rgTipo;
    private CheckBox cbStatus;
    private Button btnInserir;

    String tamanho, tipo, status, telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtTelefone = findViewById(R.id.edtTelefone);
        rgTamanho = findViewById(R.id.rgTamanho);
        rgTipo = findViewById(R.id.rgTipo);
        cbStatus = findViewById(R.id.cbStatus);
        btnInserir = findViewById(R.id.btnInserir);

        edtTelefone.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, edtTelefone));

        rgTamanho.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbPequeno:
                    tamanho = "Pequeno";
                    break;
                case R.id.rbMedio:
                    tamanho = "Médio";
                    break;
                case R.id.rbGrande:
                    tamanho = "Grande";
                    break;
                default:
                    tamanho = "Não Sei";
            }
        });

        rgTipo.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbCasa:
                    tipo = "Casa";
                    break;
                case R.id.rbApartamento:
                    tipo = "Apartamento";
                    break;
                case R.id.rbLoja:
                    tipo = "Loja";
                    break;
                default:
                    tipo = "Não Sei";
            }
        });

        btnInserir.setOnClickListener(v -> {

            telefone = edtTelefone.getText().toString();

            status = cbStatus.isChecked() ? "Em Construção" : "Pronto";

            if (!validTelephone(edtTelefone.getText().toString())) {
                Toast.makeText(InsertActivity.this, "Telefone inválido!", Toast.LENGTH_SHORT).show();
            } else {
                Estate estate = new Estate(tipo, tamanho, telefone, status);

                Log.v("Type", estate.TYPE);
                Log.v("Size", estate.SIZE);
                Log.v("Phone", String.valueOf(estate.PHONE));

                // recupera instancia singleton do banco de dados
                EstateData databaseHelper = EstateData.getInstance(InsertActivity.this);

                // adiciona imovel a banco de dados
                databaseHelper.addEstate(estate);

                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(InsertActivity.this, "Dados inseridos com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    /***
     *
     * @param number
     * @return
     */
    public boolean validTelephone(String number) {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }

    /***
     *
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(InsertActivity.this, MainActivity.class);
        startActivity(intent);
    }
}