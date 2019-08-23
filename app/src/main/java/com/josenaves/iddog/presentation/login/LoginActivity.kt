package com.josenaves.iddog.presentation.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.josenaves.iddog.R
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
            Log.d(TAG, "clicou")
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.isLoading.observe(this, Observer {
            val visible = it.getContentIfNotHandled() ?: false
//            progress_bar.visibility = if (visible) View.VISIBLE else View.GONE
        })

//        viewModel.onDataReady.observe(this, Observer {
//            val data = it.getContentIfNotHandled()
//            Log.d(TAG, "data: $data")
//
//            data?.let { sectionList ->
//                section_list.adapter =
//                    SectionAdapter(sectionList, clickListener)
//            }
//
//            section_list.visibility = View.VISIBLE
//            progress_bar.visibility = View.GONE
//        })
    }

}
