package com.example.foodydemomj_28thnov.ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.foodydemomj_28thnov.R
import com.example.foodydemomj_28thnov.constants.constants
import com.example.foodydemomj_28thnov.databinding.FragmentRecipesButtonSheetBinding
import com.example.foodydemomj_28thnov.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Locale


class RecipesBottomSheetFragment : BottomSheetDialogFragment() {

    private var binding : FragmentRecipesButtonSheetBinding? = null


    private lateinit var recipesViewModel: RecipesViewModel

    private var mealTypeChip = constants.DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = constants.DEFAULT_DIET_TYPE
    private var dietTypeChipID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentRecipesButtonSheetBinding.inflate(inflater, container, false)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner)    { values ->
            mealTypeChip = values.selectedMealType
            dietTypeChip = values.selectedDietType
            updateChip(values.selectedMealTypeId, binding!!.chipGroupMealType)
            updateChip( values.selectedDietTypeId, binding!!.dietTypeChipGroup)
        }

        binding!!.chipGroupMealType.setOnCheckedChangeListener{group, selectedChipId ->

            val chip  = group.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId
        }

        binding!!.dietTypeChipGroup.setOnCheckedChangeListener{group, selectedChipId ->

            val chip  = group.findViewById<Chip>(selectedChipId)
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipID = selectedChipId
        }

        binding!!.btnApply.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipID
            )

            val bundle = Bundle()
            bundle.putBoolean("fromBottomSheet", true)
            findNavController().navigate(R.id.action_recipesBottomSheetFragment_to_recipesFragment2,bundle)



           // findNavController().navigate(R.id.action_recipesBottomSheetFragment_to_recipesFragment2)
        }

        return binding!!.root
    }

    private fun updateChip(chipID: Int, chipGroup: ChipGroup) {
        if (chipID != 0) {
            try {
                chipGroup.findViewById<Chip>(chipID).isChecked = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}