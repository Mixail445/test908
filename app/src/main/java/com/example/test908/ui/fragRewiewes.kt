package com.example.test908.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test908.databinding.ReviewesBinding
import com.example.test908.ui.adapters.loadStateAdapter
import com.example.test908.ui.adapters.rewiewsAdapter
import com.example.test908.utils.RecyclerViewItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class fragRewiewes:Fragment() {
    private  var _binding:ReviewesBinding? = null
    private  val binding get() = _binding!!

    lateinit var  rewiewsAdapter: rewiewsAdapter

 private val viewModel: fragViewModel by viewModels()
    companion object {
        fun newInstance() = fragRewiewes()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ReviewesBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRcView()
        loadData()
        refresh()
        return view
    }
fun refresh(){
    binding.swipeContainer.setOnRefreshListener{
        rewiewsAdapter.refresh()
    }
}
    fun setupRcView(){
        rewiewsAdapter = rewiewsAdapter()
        binding.rcview.adapter = rewiewsAdapter
        binding.rcview.layoutManager = LinearLayoutManager(context)
        binding.rcview.setHasFixedSize(true)
        binding.rcview.addItemDecoration(RecyclerViewItemDecoration())
        binding.rcview.adapter = rewiewsAdapter.withLoadStateHeaderAndFooter(
        header = loadStateAdapter { rewiewsAdapter.retry() },
        footer = loadStateAdapter { rewiewsAdapter.retry() })

        rewiewsAdapter.addLoadStateListener {

            binding.progressBar.isVisible = it.refresh is LoadState.Loading
            binding.swipeContainer.visibility = View.GONE
           // binding.progressBar.visibility = View.VISIBLE
            if (it.refresh is LoadState.Error) {
                binding.ErrorTextView.text = (it.refresh as LoadState.Error).error.localizedMessage.toString()
                binding.ErrorTextView.visibility = View.VISIBLE
                binding.swipeContainer.visibility = View.GONE
            }
        }
}
    fun loadData(){
        lifecycleScope.launch {
            viewModel.getList().observe(viewLifecycleOwner) {
                rewiewsAdapter.submitData(lifecycle, it)

            }}
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}