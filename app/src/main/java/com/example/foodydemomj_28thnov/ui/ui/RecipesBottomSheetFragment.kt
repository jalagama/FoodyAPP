package com.example.foodydemomj_28thnov.ui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodydemomj_28thnov.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecipesBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes_button_sheet, container, false)
    }


}