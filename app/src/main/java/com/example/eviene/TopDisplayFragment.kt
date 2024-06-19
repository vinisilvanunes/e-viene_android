package com.example.eviene

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class TopDisplayFragment : Fragment() {

    interface OnButtonClickListener {
        fun onButtonPerfilClicked()
        fun onButtonConfigClicked()
    }

    private var listener: OnButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TopDisplayFragment.OnButtonClickListener) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnButtonClickListener")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_display, container, false)

        val btnPerfil: ImageButton = view.findViewById(R.id.fotoPerfilTopDisplay)
        btnPerfil.setOnClickListener {
            // Chama o método da interface
            replaceFragment(PerfilFragment.newInstance(PreferencesManager.getToken(requireContext()), true))
        }

        val btnConfig: ImageButton = view.findViewById(R.id.configuracaoTopDisplay)
        btnConfig.setOnClickListener {
            // Chama o método da interface
            listener?.onButtonConfigClicked()
        }

        // Inflate the layout for this fragment
        return view
    }
    fun replaceFragment(fragment : Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}
