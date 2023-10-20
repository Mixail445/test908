package com.example.test908.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test908.R
import com.example.test908.databinding.ReviewesBinding
import com.example.test908.ui.adapters.ReviewsAdapter
import com.example.test908.utils.RecyclerViewItemDecoration
import com.example.test908.utils.Status
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class FragReviews : Fragment() {
    private var _binding: ReviewesBinding? = null
    private val binding get() = _binding!!
    lateinit var reviewsAdapter: ReviewsAdapter
    private val viewModel: FragViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ReviewesBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRcView()
        loadData()
        refresh()
        searchReviewers()
        searchDataReviewers()
        return view
    }

    private fun searchDataReviewers() {
        val dataClick = binding.dataclick
        val dataText = binding.dataTExt
        dataClick.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTheme(R.style.MaterialCalendarTheme)
                .setTitleText("SelectDataPicker")
                .setSelection(null)
                .build()
            picker.show(this.childFragmentManager, "Tag")
            picker.addOnPositiveButtonClickListener {
                dataText.text = convertData(it)
                reviewsAdapter.filter.filter(convertData(it))
            }
        }
    }

    private fun convertData(time: Long): String {
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        utc.timeInMillis = time
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(utc.time)
    }

    private fun refresh() {
        binding.swipeContainer.setOnRefreshListener {
            loadData()
            binding.dataTExt.text = ""
            binding.swipeContainer.isRefreshing = false
        }
    }

    private fun setupRcView() {
        reviewsAdapter = ReviewsAdapter()
        binding.rcview.adapter = reviewsAdapter
        binding.rcview.layoutManager = LinearLayoutManager(context)
        binding.rcview.setHasFixedSize(true)
        binding.rcview.addItemDecoration(RecyclerViewItemDecoration())
    }

    private fun loadData() {
        viewModel.getUsers().observe(viewLifecycleOwner) {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val list = listOf<com.example.test908.data.modelone.Result>()
                        reviewsAdapter.submitList(list)
                        resource.data?.let { users -> reviewsAdapter.setData(users.results) }
                        binding.swipeContainer.visibility = View.VISIBLE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.swipeContainer.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.rcview.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }

            }
        }


    }

    private fun searchReviewers() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                reviewsAdapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
