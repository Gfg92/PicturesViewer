package com.example.picturesviewer

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.picturesviewer.databinding.FragmentSecondBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val foto: ImageView = view.findViewById(R.id.imageView2)
        val imagen = arguments?.getInt("foto") as Int
        foto.setImageResource(imagen)



        val fab = view.findViewById(R.id.floatingActionButton) as FloatingActionButton
        fab.setImageResource(R.drawable.ic_pause)
        var icono = 0

        fab.setOnClickListener {
            if (icono == 0) {
                val rotar = context?.getDrawable(R.drawable.ad_animacion) as AnimatedVectorDrawable
                fab.setImageDrawable(rotar)
                rotar.start()
                icono = 1
            } else {
                val rotar = context?.getDrawable(R.drawable.ad_animacion_inversa) as AnimatedVectorDrawable
                fab.setImageDrawable(rotar)
                rotar.start()
                icono = 0
            }

        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}