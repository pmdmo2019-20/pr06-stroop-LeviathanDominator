package es.iessaladillo.pedrojoya.stroop.ui.about

import android.app.AlertDialog
import android.app.Dialog
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
import es.iessaladillo.pedrojoya.stroop.ui.avatar.AvatarFragment
import kotlinx.android.synthetic.main.about_fragment.view.*

class AboutFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.about_fragment, container, false)
        setupAvatarFragment()
        setupButtons(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

    private fun setupAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.avatar_fragment,
            AvatarFragment()
        )
        ft.commit()
    }

    private fun setupButtons(view: View) {

    }

}