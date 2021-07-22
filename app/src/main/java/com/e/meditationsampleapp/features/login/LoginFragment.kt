package com.e.meditationsampleapp.features.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.e.meditationsampleapp.R
import com.e.meditationsampleapp.databinding.LoginFragmentBinding
import com.e.meditationsampleapp.features.dashboard.DashboardViewModel

private const val MIN_USERNAME_LENGTH = 3
private const val MIN_PASSWORD_LENGTH = 3
private const val REGEX_AT_LEAST_ONE_UPPERCASE = "(?=.*\\d)"
private const val REGEX_AT_LEAST_ONE_DIGIT = "(?=.*\\d)"

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)


        binding.run {
            continueButton.setOnClickListener {
//                if (isInputValid(usernameInput.text?.toString().orEmpty(), passwordInput.text?.toString().orEmpty())) {
                if (true) {
                    val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
                    this@LoginFragment.findNavController().navigate(action)
                } else {
                    showRegexError()
                }
            }
        }
    }

    private fun isInputValid(username: String, password: String) = username.length >= MIN_USERNAME_LENGTH
            && password.length >= MIN_PASSWORD_LENGTH
            && password.contains(Regex(REGEX_AT_LEAST_ONE_DIGIT))
            && password.contains(Regex(REGEX_AT_LEAST_ONE_UPPERCASE))

    private fun showRegexError() {
        context?.let { context ->
            AlertDialog.Builder(context).run {
                setMessage(
                    getErrorText(
                        binding.usernameInput.text?.toString().orEmpty(),
                        binding.passwordInput.text?.toString().orEmpty()
                    )
                )
                setCancelable(false)
                setNegativeButton("OK") { _, _ ->

                }
                show()
            }
        }
    }

    private fun getErrorText(username: String, password: String): String {
        var errorText = ""
        if (username.length < 3) errorText += getString(R.string.username_length_error)
        if (password.length < 6) errorText += getString(R.string.password_length_error)
        if (!password.contains(Regex(REGEX_AT_LEAST_ONE_DIGIT))) errorText += getString(R.string.password_digit_error)
        if (!password.contains(Regex(REGEX_AT_LEAST_ONE_UPPERCASE))) errorText += getString(R.string.password_uppercase_error)
        return errorText
    }
}