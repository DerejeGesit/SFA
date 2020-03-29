package com.sahalu.sfa


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.sahalu.sfa.utilities.InjectorUtils
import com.sahalu.sfa.viewmodels.login.LoginViewModel


/**
 * A simple [Fragment] subclass.
 */
class SplashScreenFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.navigate(R.id.homeFragment)
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> gotLogin(navController)

            }
        })
    }

    private fun gotLogin(navController: NavController) {
        Handler().postDelayed(
            {
                navController.navigate(R.id.logInFragment)
            },
            1500 // value in milliseconds
        )

    }
}
