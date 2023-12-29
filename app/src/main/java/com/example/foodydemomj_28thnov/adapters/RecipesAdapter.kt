package com.example.foodydemomj_28thnov.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodydemomj_28thnov.constants.RecipesDiffUtil
import com.example.foodydemomj_28thnov.databinding.RecipiesRowLayoutBinding
import com.example.foodydemomj_28thnov.models.FoodRecipe
import com.example.foodydemomj_28thnov.models.Result

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {


    private var recipes = emptyList<Result>()

    class MyViewHolder(private val binding: RecipiesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(result: Result) {
                binding.result = result;
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup) : MyViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val bindling = RecipiesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(bindling)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    fun setData(newData: FoodRecipe) {
        val diffUtilResult = RecipesDiffUtil(recipes,newData.results)
        val diffUtilCalc = DiffUtil.calculateDiff(diffUtilResult)
        recipes = newData.results
        diffUtilCalc.dispatchUpdatesTo(this)
    }
}