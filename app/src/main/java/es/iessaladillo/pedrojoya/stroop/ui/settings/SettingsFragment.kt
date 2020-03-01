package es.iessaladillo.pedrojoya.stroop.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import es.iessaladillo.pedrojoya.stroop.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupSettingsFragment();
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    private fun setupSettingsFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.fcSettingsList,
            SettingsListFragment()
        )
        ft.commit()
    }

    /*override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        /*(requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(false)
            setTitle(R.string.settings_title)
        }*/
        super.onActivityCreated(savedInstanceState)
    }

}
