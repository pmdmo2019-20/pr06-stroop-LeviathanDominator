package es.iessaladillo.pedrojoya.stroop.ui.rankings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.models.RankingFilter
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import kotlinx.android.synthetic.main.rankings_fragment.*

class RankingsFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    private val listAdapter: RankingListAdapter =
        RankingListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rankings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupToolbar()
    }

    private fun setupToolbar() {
        val activity = (activity as AppCompatActivity)
        toolbar = view!!.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews() {
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.ranking_spnGameMode_values,
                android.R.layout.simple_spinner_item
            )
        }.also {
            it!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spFilter.adapter = it
        }

        val rankingObserver = Observer<List<Ranking>> {
            if (it.isEmpty()) {
                clEmptyPlayerList.visibility = View.VISIBLE
            } else {
                clEmptyPlayerList.visibility = View.INVISIBLE
            }
        }
        RepositoryImpl.rankings.observe(viewLifecycleOwner, rankingObserver)

        spFilter.setSelection(getDefaultSelection())
        spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                RepositoryImpl.setRankingFilter(rankingFilter(position))
            }

            private fun rankingFilter(position: Int): RankingFilter {
                when (position) {
                    1 -> return RankingFilter.TIME
                    2 -> return RankingFilter.ATTEMPTS
                }
                return RankingFilter.ALL
            }
        }
        rvRankings.run {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
        btnHelp?.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1)
                    .setTitle(getString(R.string.help_title))
                    .setMessage(getString(R.string.ranking_help_description))
                    .setPositiveButton(getString(R.string.help_accept)) { _, _ -> }
                    .create()
                    .show()
            }
        }
    }

    private fun getDefaultSelection(): Int {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return when (prefs.getString(
            getString(R.string.prefRankingFilter_key),
            getString(R.string.prefRankingFilter_defaultValue)
        )) {
            context!!.getString(R.string.ranking_spnGameMode_all) -> {
                0
            }
            context!!.getString(R.string.ranking_spnGameMode_time) -> {
                1
            }
            else -> {
                2
            }
        }
    }
}
