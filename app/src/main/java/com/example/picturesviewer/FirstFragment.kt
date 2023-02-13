package com.example.picturesviewer

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picturesviewer.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = ArrayList<Card>()
        items.add(Card(R.drawable.image1))
        items.add(Card(R.drawable.image2))
        items.add(Card(R.drawable.image3))
        items.add(Card(R.drawable.image4))
        items.add(Card(R.drawable.image5))
        items.add(Card(R.drawable.image6))
        items.add(Card(R.drawable.image7))
        items.add(Card(R.drawable.image8))

        val rv: RecyclerView = view.findViewById(R.id.recyclerview)
        val adapter = AdaptadorCards(items)
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(context,2)


        adapter.onClick = {
            val t = items[rv.getChildAdapterPosition(it)]
            val bundle = bundleOf("foto" to t.foto)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        var modeCallBack: ActionMode.Callback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val id = item?.itemId
                when (id) {
                    R.id.action_editar -> {
                        Log.i("MainActivity", "editar")
                        mode?.finish()
                    }
                    R.id.action_eliminar -> {
                        Log.i("MainActivity", "eliminar")
                        mode?.finish()
                    }
                    R.id.action_compartir -> {
                        Log.i("MainActivity", "compartir")
                        mode?.finish()
                    }
                    else -> return false
                }
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                var mode = mode
                mode = null
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.setTitle("Options")
                mode.getMenuInflater().inflate(R.menu.menu_main, menu)
                return true
            }
        }

        adapter.onLongClick = { view ->
            view.startActionMode(modeCallBack)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}