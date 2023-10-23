package com.yudhae.submissionkokas.ui.minuman

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yudhae.submissionkokas.DetailActivity
import com.yudhae.submissionkokas.R
import com.yudhae.submissionkokas.adapter.Hero
import com.yudhae.submissionkokas.adapter.ListHeroAdapter
import com.yudhae.submissionkokas.databinding.FragmentMinumanBinding

class MinumanFragment : Fragment() {

    private var _binding: FragmentMinumanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gridViewModel =
            ViewModelProvider(this)[MinumanViewModel::class.java]

        _binding = FragmentMinumanBinding.inflate(inflater, container, false)
        binding.root

        val textView: TextView = binding.textItemMinuman
        gridViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_heroes)

        val adapter = ListHeroAdapter(getListHeroes())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context,2)

        adapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })

        return view

    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.menu_coffee)
        val dataDescription = resources.getStringArray(R.array.menu_coffee_desc)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showSelectedHero(hero: Hero) {
        val characterDetailIntent = Intent(requireContext(), DetailActivity::class.java)
        characterDetailIntent.putExtra("extra_name", hero.name)
        characterDetailIntent.putExtra("extra_name_detail", hero.name)
        characterDetailIntent.putExtra("extra_photo", hero.photo)
        characterDetailIntent.putExtra("extra_description", hero.description)
        startActivity(characterDetailIntent)
        Toast.makeText(requireActivity(), "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}