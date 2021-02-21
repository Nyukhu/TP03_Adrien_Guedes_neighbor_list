package com.gadrien456.gmail.tp3_adrien_guedes.fragments

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadrien456.gmail.tp3_adrien_guedes.NavigationListener
import com.gadrien456.gmail.tp3_adrien_guedes.adapters.ListNeighborHandler
import com.gadrien456.gmail.tp3_adrien_guedes.adapters.ListNeighborsAdapter
import com.gadrien456.gmail.tp3_adrien_guedes.data.NeighborRepository
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.ListNeighborsFragmentBinding
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor


class ListNeighborsFragment : Fragment(), ListNeighborHandler {


    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var adapter: ListNeighborsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        (activity as? NavigationListener)?.let {
            it.updateTitle(com.gadrien456.gmail.tp3_adrien_guedes.R.string.listTitle)
        }
        binding.addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        adapter = ListNeighborsAdapter(neighbors,this)
        binding.neighborsList.adapter = adapter
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {

        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_dialog_alert)
            .setTitle("Supprimer un voisin ?")
            .setMessage("êtes vous sur de vouloir supprimer un voisin ?")
            .setPositiveButton("supprimer",
                DialogInterface.OnClickListener { dialog, which -> deleteNeighbor(neighbor) })
            .setNegativeButton("annuler", null)
            .show()
    }

    fun deleteNeighbor(neighbor:Neighbor){
        val deletedIndex = NeighborRepository.getInstance().dataSource.deleteNeighbour(neighbor)
        binding.neighborsList.removeViewAt(deletedIndex);
        adapter.notifyItemRemoved(deletedIndex);
        adapter.notifyItemRangeChanged(deletedIndex, NeighborRepository.getInstance().getNeighbours().size);

    }
}