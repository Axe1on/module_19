package com.example.module_19

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.module_19.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var deBinding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,container:ViewGroup?,savedInstanceState: Bundle?
    ): View?{
        deBinding = FragmentDetailsBinding.inflate(layoutInflater)
        return deBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilmsDetails()
    }

       private fun setFilmsDetails() {
        val film = arguments?.get("film") as Film
        //Устанавливаем заголовок
        deBinding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        deBinding.detailsPoster.setImageResource(film.poster)
        //Устанавливаем описание
        deBinding.detailsDescription.text = film.description
    }

}