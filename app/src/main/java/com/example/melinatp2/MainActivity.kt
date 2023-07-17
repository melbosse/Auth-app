package com.example.melinatp2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.iterator
import com.example.melinatp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Variables
        val localStorage = getSharedPreferences("infos", MODE_PRIVATE)

        //Vider le SharedPreferences
        //localStorage.edit().clear().apply()

        binding.bConnexion.isEnabled = false
        binding.bChangerMdp.isEnabled = false

        // Vérification s'il y a des informations enregistrées dans le téléphone
        if (localStorage.getString("utilisateur", "nothing") == "nothing") {
            binding.bConnexion.text = getString(R.string.ins)
        } else {
            binding.bConnexion.text = getString(R.string.cn)
        }

        // Actions définies lors du clic du bouton de connexion
        // Quand il n'y a aucune données dans le SharedPreferences
        binding.bConnexion.setOnClickListener {
            if (binding.bConnexion.text.toString() == "S'inscrire") {
                // Inscription
                inscription(binding.etUtilisateur, binding.etMdp, localStorage, binding.bConnexion)
            } else {
                // Connexion
                connexion(binding.etUtilisateur, binding.etMdp, localStorage)
            }
        }

        // Bouton qui permet la création d'un nouvel utilisateur
        binding.bNouvUtil.setOnClickListener {
            inscription(
                binding.etUtilisateur,
                binding.etMdp,
                localStorage,
                binding.bConnexion
            )
        }

        // Bouton qui permet de passer à la la troisième activitée pour le changement de mot de passe
        binding.bChangerMdp.setOnClickListener {
            if (binding.bConnexion.text.toString() == "Connexion") {
                startActivity(Intent(this, ThirdActivity::class.java).apply {})
            }
        }

        // Préparation du TextWatcher
        val txtWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.bConnexion.isEnabled =
                    binding.etUtilisateur.text.isNotEmpty() && binding.etMdp.text.isNotEmpty()

                binding.bChangerMdp.isEnabled =
                    binding.etUtilisateur.text.isNotEmpty() && binding.etMdp.text.isNotEmpty()
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

    // S'il y a une connexion, lancement de la seconde activitée si l'utilisateur est déjà inscrit
    private fun connexion(
        etUtilisateur: EditText,
        etMdp: EditText,
        localStorage: SharedPreferences
    ) {
        if (etMdp.text.toString() == localStorage.getString("${etUtilisateur.text}", "nothing")) {
            startActivity(Intent(this, SecondActivity::class.java).apply {
                putExtra("utilisateur", etUtilisateur.text.toString())
                putExtra("mdp", etMdp.text.toString())
            })
        } else {
            val msgErreur = AlertDialog.Builder(this)
            msgErreur.setTitle("Nom d'utilisateur ou mot de passe invalide.")
                .setMessage("Si vous n'êtes pas inscrit, appuyez sur : S'inscrire.")
                .setCancelable(false)
                .setPositiveButton("OK") { di, i ->
                    di.dismiss()
                    println("Valeur de i: $i")
                }
                .show()
        }
    }

    // S'il n'y a pas de connexion, l'inscription de l'utilisateur se fait
    private fun inscription(
        etUtilisateur: EditText,
        etMdp: EditText,
        localStorage: SharedPreferences,
        bConnexion: Button
    ) {
        val msgIns = AlertDialog.Builder(this)
        msgIns.setTitle("Inscription réussie!")
            .setMessage("Vous pouvez désormais vous connecter.")

        if (localStorage.getString("utilisateur", "nothing") == "nothing") {
            localStorage.edit().apply {
                putString("utilisateur", "content")
            }.apply()
        }

        if (localStorage.getString("${etUtilisateur.text}", "nothing") == "nothing") {
            localStorage.edit().apply {
                putString("${etUtilisateur.text}", "${etMdp.text}")
            }.apply()
        }

        bConnexion.text = getString(R.string.cn)

        msgIns.show()
    }
}