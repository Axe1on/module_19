package com.example.module_19.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Fade
import androidx.transition.Visibility
import com.example.module_19.utils.AnimationHelper
import com.example.module_19.domain.Film
import com.example.module_19.view.rv_adapter.FilmListRecyclerAdapter
import com.example.module_19.view.MainActivity
import com.example.module_19.view.rv_adapter.TopSpacingItemDecoration
import com.example.module_19.databinding.FragmentHomeBinding
import com.example.module_19.viewmodel.HomeFragmentViewModel
import java.util.Locale

@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    init {
        exitTransition = Fade(Visibility.MODE_OUT).apply { duration = 400;mode = Fade.MODE_OUT }
        reenterTransition = Fade(Visibility.MODE_IN).apply { duration = 800 }
    }

    private lateinit var binding: FragmentHomeBinding

    //private lateinit var binding2: MergeHomeScreenContentBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    //Создадим переменную, куда будем класть нашу БД из ViewModel, чтобы у нас не сломался поиск:
    private var filmsDataBase = listOf<Film>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //подпишемся на изменения этой View Model
        viewModel.filmsListLifeData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmsDataBase = it
        })

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )

        /* Анимация появления Recycle View с низу и Search View с верху
        binding2 =
            MergeHomeScreenContentBinding.inflate(layoutInflater, binding.homeFragmentRoot, false)
        val scene = Scene(binding.homeFragmentRoot, binding2.root)
        //Создаем анимацию выезда поля поиска сверху
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        //Создаем анимацию выезда RV снизу
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(R.id.main_recycler)
        //Создаем экземпляр TransitionSet, который объединит все наши анимации
        val customTransition = TransitionSet().apply {
            //Устанавливаем время, за которое будет проходить анимация
            duration = 500
            //Добавляем сами анимации
            addTransition(searchSlide)
            addTransition(recyclerSlide)
        }
        //Также запускаем через TransitionManager, но вторым параметром передаем нашу кастомную анимацию
        TransitionManager.go(scene, customTransition)


         */

        val main_recycler = binding.mainRecycler
        //находим наш RV
        main_recycler.apply {
            //Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс,
            //оставим его пока пысты, он нам понадобится во второй части задания
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)

        //для нажатия на поля поиска целиком, а не только на лупу
        val search_view = binding.searchView
        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        //Подключаем слушателя изменений введенного текста в поиска
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поискк подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })

    }

}
