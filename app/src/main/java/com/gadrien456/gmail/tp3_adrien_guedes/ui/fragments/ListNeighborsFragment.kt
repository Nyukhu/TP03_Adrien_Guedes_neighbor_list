package com.gadrien456.gmail.tp3_adrien_guedes.ui.fragments

import android.R
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gadrien456.gmail.tp3_adrien_guedes.NavigationListener
import com.gadrien456.gmail.tp3_adrien_guedes.adapters.ListNeighborHandler
import com.gadrien456.gmail.tp3_adrien_guedes.adapters.ListNeighborsAdapter
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.ListNeighborsFragmentBinding
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor
import com.gadrien456.gmail.tp3_adrien_guedes.repositories.NeighborRepository
import com.gadrien456.gmail.tp3_adrien_guedes.viewmodels.NeighborViewModel

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var adapter: ListNeighborsAdapter
    private lateinit var viewModel: NeighborViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("item", "toutitou")
        setData()
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {

        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_dialog_alert)
            .setTitle("Supprimer un voisin ?")
            .setMessage("êtes vous sur de vouloir supprimer un voisin ?")
            .setPositiveButton(
                "supprimer",
                DialogInterface.OnClickListener { dialog, which -> deleteNeighbor(neighbor) }
            )
            .setNegativeButton("annuler", null)
            .show()
    }

    fun deleteNeighbor(neighbor: Neighbor) {
        val application: Application = activity?.application ?: return
        val deletedIndex = NeighborRepository.getInstance(application).dataSource.deleteNeighbour(neighbor)
        binding.neighborsList.removeViewAt(deletedIndex)
        adapter.notifyItemRemoved(deletedIndex)
        var size = 0
        if (NeighborRepository.getInstance(application).getNeighbours().value != null) {
            size = NeighborRepository.getInstance(application).getNeighbours().value!!.size
        }
        adapter.notifyItemRangeChanged(deletedIndex, size)
    }
    private fun setData() {
        Log.d("item", "toutitou")
        viewModel.neighbors.observe(
            viewLifecycleOwner,
            Observer<List<Neighbor>> { t ->
                val adapter = ListNeighborsAdapter(t, this)
                binding.neighborsList.adapter = adapter
            }
        )
    }
}
