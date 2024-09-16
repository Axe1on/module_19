package com.example.module_19.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_19.Film
import com.example.module_19.FilmListRecyclerAdapter
import com.example.module_19.MainActivity
import com.example.module_19.R
import com.example.module_19.TopSpacingItemDecoration
import com.example.module_19.databinding.FragmentHomeBinding
import java.util.Locale

@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    val filmsDataBase = listOf(
        Film(
            "Веном 3 Последний танец",
            R.drawable.kino1,
            "Эдди Брок (Харди) и его верный симбиот Веном путешествуют по стране, разбираясь по пути с разными преступниками и негодяями. Однажды на их след выходят военные, которые хотят поймать Венома для проведения опытов. Им почти удается достигнуть своей цели, однако всё меняется, когда на Землю вторгается целая армия симбиотов, намеренная поработить всё человечество."
        ),
        Film(
            "Кеэйвен-охотник",
            R.drawable.kino2,
            "Сергей Кравинов (Аарон Тейлор-Джонсон) — сын аристократа (Рассел Кроу), который был вынужден бежать из России в Штаты после Февральской революции 1917 года. Сергей, с одной стороны, является отличным охотником, а с другой, защищает мир животных от людей. Он не носит супергеройского трико. Чтобы получать сверхспособности, ему необходимо пить специальный отвар из африканских трав, но по-настоящему опасным его делают охотничьи таланты. Так он становится Крэйвеном-охотником. Чтобы доказать всем, что является альфа-самцом, он решается разобраться с крупной дичью — Человеком-пауком. Супергеройский блокбастер Джей Си Чендора («Самый жестокий год» и «Тройная граница») расширяет киновселенную Человека-паука и представляет одного из его самых опасных соперников."
        ),
        Film(
            "Дэдпул и Росомаха",
            R.drawable.kino3,
            "Уэйд Уилсон попадает в организацию «Управление временными изменениями», что вынуждает его вернуться к своему альтер-эго Дэдпулу и изменить историю с помощью Росомахи."
        ),
        Film(
            "Головоломка 2",
            R.drawable.kino4,
            "Мозг Райли внезапно подвергается капитальному ремонту в тот момент, когда необходимо освободить место для кое-чего совершенно неожиданного: новых эмоций. Радость, Грусть, Гнев, Страх и Отвращение никак не ожидали появления некой Тревожности. И похоже, не только её."
        ),
        Film(
            "Дюна Часть вторая",
            R.drawable.kino5,
            "Герцог Пол Атрейдес присоединяется к фременам, чтобы стать Муад Дибом, одновременно пытаясь остановить наступление войны."
        ),
        Film(
            "Артур, ты король",
            R.drawable.kino6,
            "Это было последнее соревнование капитана по приключенческим гонкам Майкла Лайта, он был полон решимости ничему не позволить встать у него на пути. Собрав первоклассную команду, он не мог и представить, что в 700-километровом забеге у них появится неожиданный попутчик — пес по кличке Артур, встреча с которым изменит не только исход гонки, но и их жизнь."
        ),
        Film(
            "Человек-паук Паутина вселенных",
            R.drawable.kino7,
            "После воссоединения с Гвен Стейси дружелюбный сосед Человек-Паук попадает из Бруклина в Мультивселенную, где встречает команду Паучков, защищающих само её существование. Пытаясь справиться с новой угрозой, Майлз сталкивается с Пауками из других вселенных. Настаёт момент, когда ему необходимо решить, что значит быть героем, спасающим тех, кого любишь больше всего."
        ),

        )

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