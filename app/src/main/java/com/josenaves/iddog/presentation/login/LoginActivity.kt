package com.josenaves.iddog.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.josenaves.iddog.R
import com.josenaves.iddog.common.architecture.UiState
import com.josenaves.iddog.presentation.dog.DogsActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    private val viewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindScope(getOrCreateScope(TAG))

        buttonEnter.setOnClickListener {
            viewModel.login(editTextEmail.text.toString())
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this, Observer {
            when (val state = it.getContentIfNotHandled()) {
                UiState.Loading -> disableWindow()
                is UiState.Success -> {
                    enableWindow()
                    startActivity(Intent(this, DogsActivity::class.java))
                }
                is UiState.Error -> {
                    Snackbar.make(
                        root_view,
                        getString(R.string.login_error_message),
                        LENGTH_LONG
                    ).show()

                    enableWindow()
                }
            }
        })
    }

    private fun disableWindow() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        progressBar.visibility = View.VISIBLE
    }

    private fun enableWindow() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBar.visibility = View.GONE
    }

}
