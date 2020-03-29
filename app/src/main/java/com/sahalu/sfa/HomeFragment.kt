package com.sahalu.sfa


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.afollestad.materialdialogs.MaterialDialog
import com.sahalu.sfa.utilities.InjectorUtils
import com.sahalu.sfa.viewmodels.homepage.HomePageViewModel
import com.sahalu.sfa.viewmodels.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var toolbar: Toolbar


    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(this)
    }

    private val homeViewModel: HomePageViewModel by viewModels {
        InjectorUtils.provideHomePageViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.welcome)
        val imageView: ImageView = view.findViewById(R.id.sync)
        val listOutlets: ImageView = view.findViewById(R.id.listOutlets)
        val outletsAmend: ImageView = view.findViewById(R.id.imageView3)

        val navController = Navigation.findNavController(view)
        var dialog = MaterialDialog(this!!.context!!)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = ""
        (activity as MainActivity?)!!.setSupportActionBar(toolbar)


        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if (user !== null && user.isNotEmpty()) {
                textView.text = "Welcome, ${user[0]}"
            }
        })

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            if (authenticationState == LoginViewModel.AuthenticationState.UNAUTHENTICATED)
                    navController.navigate(R.id.splashScreenFragment)
        })

        homeViewModel.ntwError.observe(viewLifecycleOwner, Observer { ntwError ->
            if(ntwError){
                dialog.dismiss()
                Toast.makeText(context, "Network Error Can't sync now",Toast.LENGTH_SHORT).show()
            }
        })

        homeViewModel.syncComplete.observe(viewLifecycleOwner, Observer { syncDone ->
            if(syncDone){
                dialog.dismiss()
                Toast.makeText(context, "Sync Completed Successfully.",Toast.LENGTH_SHORT).show()
            }
        })


        imageView.setOnClickListener {
            homeViewModel.sync()
            context?.let { co ->
              dialog  = MaterialDialog(co).
                    title(R.string.syncing)
                    .message(R.string.syncing_body)
                    .cancelable(false)
                    .cancelOnTouchOutside(false)
                dialog.show()
            }
        }

        listOutlets.setOnClickListener{
            navController.navigate(R.id.createOutletSaleFragment)
        }
        outletsAmend.setOnClickListener{
            navController.navigate(R.id.outletAmedmentFragment)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.log_out -> {
                logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun logout() {
        viewModel.logout()
    }


}
