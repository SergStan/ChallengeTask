package com.example.taskonjsonapplication.presentation.series

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskonjsonapplication.EPISODE_FRAGMENT
import com.example.taskonjsonapplication.R
import com.example.taskonjsonapplication.databinding.FragmentSeriesBinding
import com.example.taskonjsonapplication.presentation.episode.EpisodeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : Fragment(), SeriesAdapter.OnEpisodeItemClickListener {

    private val viewModel: SeriesViewModel by viewModel()

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadSeries()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seriesAdapter = SeriesAdapter(layoutInflater, this)

        with(binding.episodes) {
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = seriesAdapter
        }

        viewModel.result.observe(requireActivity()) { state ->
            with(binding) {
                when (state) {
                    SeriesViewState.Loading -> {
                        progress.visibility = View.VISIBLE
                    }
                    is SeriesViewState.Content -> {
                        progress.visibility = View.GONE
                        seriesAdapter.submitList(state.episodes)
                    }
                    is SeriesViewState.Error -> {
                        progress.visibility = View.GONE
                        Toast.makeText(
                            requireActivity(),
                            state.throwable.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                        Log.e("TAG", "Exception loading data", state.throwable)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(seasonNumber: String, episodeNumber: String) {
        val fragment = EpisodeFragment.getInstance(
            seasonNumber.split(SPLIT_CHARACTER).last().trim(),
            episodeNumber.split(SPLIT_CHARACTER).last().trim()
        )
        requireActivity().supportFragmentManager.commit {
            @AnimRes
            val enter = R.anim.slide_in

            @AnimRes
            val exit = R.anim.fade_out

            @AnimRes
            val popEnter = R.anim.fade_in

            @AnimRes
            val popExit = R.anim.slide_out

            setCustomAnimations(enter, exit, popEnter, popExit)
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment, EPISODE_FRAGMENT)
            addToBackStack(null)
        }
    }

    companion object {
        private const val SPLIT_CHARACTER = ":"
    }
}