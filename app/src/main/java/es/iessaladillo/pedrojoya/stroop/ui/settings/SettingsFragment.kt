package es.iessaladillo.pedrojoya.stroop.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupSettingsFragment()
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    private fun setupSettingsFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.fcSettingsList,
            SettingsListFragment()
        )
        ft.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupButton()
    }

    private fun setupButton() {
        btnHelp?.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(getString(R.string.help_title))
                .setMessage(getString(R.string.settings_help_description))
                .setPositiveButton(getString(R.string.help_accept)){ _, _ -> }
                .create()
                .show()
        }
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

}
