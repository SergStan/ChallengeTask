package com.example.taskonjsonapplication.presentation.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskonjsonapplication.EPISODE_NUMBER_KEY
import com.example.taskonjsonapplication.SEASON_NUMBER_KEY
import com.example.taskonjsonapplication.databinding.FragmentEpisodeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {

    private val viewModel: EpisodeViewModel by viewModel()

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = requireArguments()
        val seasonNumber: String? = arguments.getString(SEASON_NUMBER_KEY)
        val episodeNumber: String? = arguments.getString(EPISODE_NUMBER_KEY)

        seasonNumber?.let { season ->
            episodeNumber?.let { episode ->
                viewModel.loadSeries(season, episode)
            }
        }

        viewModel.result.observe(requireActivity()) { state ->
            with(binding) {
                when (state) {
                    EpisodeViewState.Loading -> {
                        progress.visibility = View.VISIBLE
                    }
                    is EpisodeViewState.Content -> {
                        progress.visibility = View.GONE
                        binding.state = state.episodeState
                    }
                    is EpisodeViewState.Error -> {
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

    companion object {
        fun getInstance(seasonNumber: String, episodeNumber: String): EpisodeFragment {
            val fragment = EpisodeFragment()
            val bundle = Bundle()
            bundle.putString(SEASON_NUMBER_KEY, seasonNumber)
            bundle.putString(EPISODE_NUMBER_KEY, episodeNumber)
            fragment.arguments = bundle
            return fragment
        }
    }
}