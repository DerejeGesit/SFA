package com.sahalu.sfa


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.sahalu.sfa.utilities.InjectorUtils
import com.sahalu.sfa.viewmodels.login.LoginViewModel

/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(this)
    }

    private lateinit var phoneNumberEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: AppCompatButton
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phoneNumberEditText = view.findViewById(R.id.input_phoneNumber)
        passwordEditText = view.findViewById(R.id.input_password)

        loginButton = view.findViewById(R.id.btn_login)
        progressBar = view.findViewById(R.id.progress_circular)

        errorMessage = view.findViewById(R.id.errorMessage)

        loginButton.setOnClickListener {
            viewModel.authenticate(
                phoneNumberEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        val navController = findNavController(view)
        progressBar.visibility = View.INVISIBLE


        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if(loading) {
                progressBar.visibility = View.VISIBLE
                loginButton.isEnabled = false
            }else {
                progressBar.visibility = View.INVISIBLE
                loginButton.isEnabled = true
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            error -> errorMessage.text = error
        })

    }

    private fun showErrorMessage() {
        Log.d("Error", "Error Login ")
    }


}
