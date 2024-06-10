package com.example.eviene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eviene.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), TipoDePostFragment.OnButtonClickListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fillTopDisplay(TopDisplayFragment())
        replaceFragment(HomeFragment())
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.pesquisar -> replaceFragment(PesquisarFragment())
                R.id.postar -> replaceFragment(TipoDePostFragment())
                R.id.perfil -> replaceFragment(PerfilFragment())
                else->{}
            }
            true
        }

    }

     fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }

    private fun fillTopDisplay(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.topDisplay,fragment)
        fragmentTransaction.commit()
    }

    override fun onButtonEventoClicked() {
        replaceFragment(PostarEventoFragment())
    }

    override fun onButtonPostagemClicked() {
        replaceFragment(PostarFragment())
    }


}