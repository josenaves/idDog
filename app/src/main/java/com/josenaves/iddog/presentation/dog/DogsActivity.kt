package com.josenaves.iddog.presentation.dog

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.josenaves.iddog.R
import com.josenaves.iddog.common.architecture.UiState
import kotlinx.android.synthetic.main.activity_dogs.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogsActivity : AppCompatActivity() {

    private val vm : DogsViewModel by viewModel()

    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dogs)

        gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager

        vm.getDogs()

//        adapter = DogAdapter(listOf(
//            Dog("1", "https://images.dog.ceo/breeds/hound-english/n02089973_1.jpg"),
//            Dog("2", "https://images.dog.ceo/breeds/hound-english/n02089973_1000.jpg")))
        recyclerView.adapter = DogAdapter(emptyList())

        setupObservers()
    }

    private fun setupObservers() {
        vm.uiState.observe(this, Observer {
            when (val event = it.getContentIfNotHandled()) {
                is UiState.Loading -> disableWindow()

                is UiState.Success -> {
                    enableWindow()
                    recyclerView.adapter = DogAdapter(event.data)
                }

                is UiState.Error -> {
                    Snackbar.make(
                        root_view,
                        getString(R.string.unexpected_error_message),
                        Snackbar.LENGTH_LONG
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
