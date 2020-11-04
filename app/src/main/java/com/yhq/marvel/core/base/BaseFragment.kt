package com.yhq.marvel.core.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import com.yhq.marvel.MainActivity
import com.yhq.marvel.core.extension.showToast
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.result.getErrorMessage
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel>(layout: Int) : Fragment(layout) {

    protected lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: Lazy<Factory>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @Suppress("UNCHECKED_CAST") // Type is always of Type=T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val klass =
            (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        viewModel = ViewModelProvider(this, viewModelFactory.get()).get<VM>(klass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showActionBar()
    }

    private fun showActionBar() = setActionBarStatus(true)

    internal fun hideActionBar() = setActionBarStatus(false)

    internal fun showProgress() = setProgressStatus(View.VISIBLE)

    internal fun hideProgress() = setProgressStatus(View.GONE)

    private fun showErrorMessage(messageResourceID: Int) = requireContext().showToast(getString(messageResourceID))

    private fun showErrorMessage(message: String) = requireContext().showToast(message)

    private fun setProgressStatus(viewStatus: Int) =
        with(activity) {
            if (this is MainActivity) this.binding.layoutProgressBar.rlProgressBar.visibility = viewStatus
        }

    private fun setActionBarStatus(isShow: Boolean) {
        with(activity) {
            if (this is MainActivity) {
                if (isShow)
                    this.supportActionBar?.show()
                else this.supportActionBar?.hide()
            }
        }
    }

    fun setActionBarTitle(title: String) {
        with(activity) {
            if (this is MainActivity) {
                this.supportActionBar?.title = title
            }
        }
    }

    fun executeFailureActions(failure: Failure?) {
        hideProgress()
        failure?.let {
            val errorMessage = getErrorMessage(it)
            showErrorMessage(errorMessage.messageResourceID)
        }
    }
}
