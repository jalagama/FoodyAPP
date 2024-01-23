package com.example.foodydemomj_28thnov

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_DIET_TYPE
import com.example.foodydemomj_28thnov.constants.constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foodydemomj_28thnov.viewmodels.RecipesViewModel

class FavoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }


}