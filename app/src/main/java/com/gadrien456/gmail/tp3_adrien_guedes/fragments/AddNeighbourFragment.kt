package com.gadrien456.gmail.tp3_adrien_guedes.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.vvalidator.form
import com.gadrien456.gmail.tp3_adrien_guedes.NavigationListener
import com.gadrien456.gmail.tp3_adrien_guedes.R
import com.gadrien456.gmail.tp3_adrien_guedes.data.NeighborRepository
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.AddNeighborBinding
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.ListNeighborsFragmentBinding
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor
import kotlinx.android.synthetic.main.add_neighbor.*
import kotlinx.android.synthetic.main.list_neighbors_fragment.view.*

class AddNeighbourFragment  : Fragment(){
    private lateinit var binding: AddNeighborBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddNeighborBinding.inflate(inflater, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(com.gadrien456.gmail.tp3_adrien_guedes.R.string.addTitle)
        }

        form {
            useRealTimeValidation(disableSubmit = true)
            input(binding.imageURLInput) {
                isNotEmpty().description(R.string.error_image_empty)
                isUrl().description(R.string.error_image)
            }

            input(binding.nameInput) {
                isNotEmpty().description(R.string.error_name)
            }

            input(binding.phoneInput) {
                isNotEmpty().description(R.string.error_phone)
                matches("^(0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}\$").description(R.string.error_phone)
            }

            input(binding.WebsiteInput) {
                isNotEmpty().description(R.string.error_WebSite)
            }
            input(binding.AdressInput) {
                isNotEmpty().description(R.string.error_Adress)
                isEmail().description(R.string.error_Adress)
            }
            input(binding.AboutMeInput) {
                isNotEmpty().description(R.string.error_About)
                length().atMost(30).description("30 carac. max")
            }

            submitWith( binding.saveBtn) {
                var image = binding.imageURLInput.text.toString()
                var name = binding.nameInput.text.toString()
                var phone = binding.phoneInput.text.toString()
                var website = binding.WebsiteInput.text.toString()
                var adress = binding.AdressInput.text.toString()
                var about = binding.AboutMeInput.text.toString()
                var id = NeighborRepository.getInstance().getNeighbours().count()

                println(image)
                val neighbor = Neighbor(
                    id.toLong(), name,image,adress,phone,about,false,website
                )

                NeighborRepository.getInstance().dataSource.createNeighbour(neighbor)
                (activity as? NavigationListener)?.let {
                    it.showFragment(ListNeighborsFragment())

                }
            }
        }
        return binding.root
    }
}