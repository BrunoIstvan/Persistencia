package br.com.bicmsystems.persistencia

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.EditorInfo
import br.com.bicmsystems.persistencia.view.lista.ListaGamesActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("myapp",
                Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("MANTER_CONECTADO", false)) {
            startActivity(Intent(this, ListaGamesActivity::class.java))
            finish()
        }

        if(sharedPreferences.getString("USUARIO", "").isNotEmpty()) {
            edtNome.setText(sharedPreferences.getString("USUARIO", ""))
        }

        btnEntrar.setOnClickListener {
            edtNome.onEditorAction(EditorInfo.IME_ACTION_DONE);
            entrar(sharedPreferences)
        }

    }

    private fun entrar(sharedPreferences: SharedPreferences) {

        if(edtNome.text.toString().isNullOrEmpty()) {
            Snackbar.make(findViewById(android.R.id.content) as View, "Informe seu nome", Snackbar.LENGTH_LONG).show()
            return
        }

        val editor = sharedPreferences.edit()
        editor.putBoolean("MANTER_CONECTADO", chkManterConectado.isChecked)
        editor.putString("USUARIO", edtNome.text.toString())
        editor.apply()
        startActivity(Intent(this, ListaGamesActivity::class.java))
        finish()

    }


}
