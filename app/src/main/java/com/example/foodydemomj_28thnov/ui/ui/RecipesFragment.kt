package com.example.foodydemomj_28thnov.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodydemomj_28thnov.R
import com.example.foodydemomj_28thnov.adapters.RecipesAdapter
import com.example.foodydemomj_28thnov.constants.NetworkResult
import com.example.foodydemomj_28thnov.constants.observeOnce
import com.example.foodydemomj_28thnov.databinding.FragmentRecipesBinding
import com.example.foodydemomj_28thnov.viewmodels.MainViewModel
import com.example.foodydemomj_28thnov.viewmodels.RecipesViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var _binding : FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val recipesAdapter by lazy { RecipesAdapter() }
    private lateinit var recyclerView: ShimmerRecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        recyclerView = binding.recyclerview
        setUpRecylerView()

        binding.recipesFab.setOnClickListener() {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheetFragment2)
        }

        readDatabse()
        return binding.root
    }

    private fun readDatabse() {
        lifecycleScope.launch {
            mainViewModel.readRecipe.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    recipesAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            }
        }
    }

    private fun loadDataFromCache(){
        lifecycleScope.launch {
            mainViewModel.readRecipe.observeOnce(viewLifecycleOwner) {database ->
                if (database.isNotEmpty()) {
                    recipesAdapter.setData(database[0].foodRecipe)
                }
            }
        }
    }

    fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { recipesAdapter.setData(response.data) }

                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }

            }
        }
    }


    fun setUpRecylerView() {
        recyclerView.adapter = recipesAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    fun showShimmerEffect() {
        recyclerView.showShimmer()
    }
    fun hideShimmerEffect() {
        recyclerView.hideShimmer()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}