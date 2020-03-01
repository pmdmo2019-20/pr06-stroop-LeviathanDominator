package es.iessaladillo.pedrojoya.stroop.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.about_fragment.view.*

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.about_fragment, container, false)
        setupToolbar()

        setupAvatarFragment()
        setupButtons(view)
        return view
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(view?.findViewById(R.id.toolbar))
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true);
            setHasOptionsMenu(false)
            setTitle(R.string.settings_title)
        }
    }

    private fun setupAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.avatar_fragment, AvatarFragment())
        ft.commit()
    }

    private fun setupButtons(view: View) {
        view.btnHelp?.setOnClickListener {
            Toast.makeText(view.context, "Test", Toast.LENGTH_LONG).show()
            val dialog: Dialog = AlertDialog.Builder(context)
                .setTitle("Test")
                .setMessage("Testtest")
                .create()
        }
    }

}