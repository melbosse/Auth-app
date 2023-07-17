package com.example.melinatp2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import com.example.melinatp2.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val localStorage = getSharedPreferences("infos", MODE_PRIVATE)

        binding.bConfirmer.isEnabled = false

        binding.bConfirmer.setOnClickListener {
            transfert(
                binding.etMdp,
                binding.etUtilisateur,
                binding.etNouvMdp,
                localStorage,
                binding.bConfirmer
            )
        }

        // TextWatcher
        val txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.bConfirmer.isEnabled =
                    binding.etUtilisateur.text.isNotEmpty() && binding.etMdp.text.isNotEmpty() && binding.etNouvMdp.text.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        // Assignation
        for (view in binding.root) {
            if (view is EditText) {
                view.addTextChangedListener(txtWatcher)
            }
        }
    }

    // Permet le transfert du changement de mot de passe
    private fun transfert(
        etMdp: EditText,
        etUtilisateur: EditText,
        etNouvMdp: EditText,
        localStorage: SharedPreferences,
        bConfirmer: Button
    ) {
        if (localStorage.getString("mdp", "nothing") == "nothing") {
            localStorage.edit().apply {
                putString("${etUtilisateur.text}", "${etMdp.text}")
            }.apply()

            if (localStorage.getString("${etMdp.text}", "nothing") == "nothing") {
                localStorage.edit().apply {
                    putString("${etUtilisateur.text}", "${etNouvMdp.text}")
                }.apply()

                val msgConfirm = AlertDialog.Builder(this)
                msgConfirm.setTitle("Mot de passe changé")
                    .setMessage("Vous avez bien modifié votre mot de passe.")
                    .setCancelable(false)
                    .setPositiveButton("OK") { di, i ->
                        di.dismiss()
                        println("Valeur de i: $i")
                    }
                    .show()
            } else {
                val msgErreur = AlertDialog.Builder(this)
                msgErreur.setTitle("Échec de confirmation")
                    .setMessage("Vous n'avez pas modifié votre mot de passe.")
                    .setCancelable(false)
                    .setPositiveButton("OK") { di, i ->
                        di.dismiss()
                        println("Valeur de i: $i")
                    }
                    .show()
            }
        }
    }
}