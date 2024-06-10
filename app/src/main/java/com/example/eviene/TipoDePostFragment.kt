package com.example.eviene

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

class TipoDePostFragment : Fragment() {

    interface OnButtonClickListener {
        fun onButtonEventoClicked()
        fun onButtonPostagemClicked()
    }

    private var listener: OnButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnButtonClickListener) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnButtonClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tipo_de_post, container, false)
        // Inflate the layout for this fragment
        val btnEvento: ImageButton = view.findViewById(R.id.btnEvento)
        btnEvento.setOnClickListener {
            // Chama o método da interface
            listener?.onButtonEventoClicked()
        }

        val btnPostagem: ImageButton = view.findViewById(R.id.btnPostagem)
        btnPostagem.setOnClickListener {
            // Chama o método da interface
            listener?.onButtonPostagemClicked()
        }

        return view
    }

}