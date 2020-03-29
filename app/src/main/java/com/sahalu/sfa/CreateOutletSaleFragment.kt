package com.sahalu.sfa


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahalu.sfa.adaptors.OutletsAdaptor
import com.sahalu.sfa.utilities.InjectorUtils
import com.sahalu.sfa.viewmodels.outletlistpage.OutletViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreateOutletSaleFragment : Fragment() {


    private val viewModel: OutletViewModel by viewModels {
        InjectorUtils.provideOutletListModelFactory(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_create_outlet_sale, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.outletsRV)
        val locatingString = view.findViewById<TextView>(R.id.locationString)

        rv.layoutManager = LinearLayoutManager(activity)

        val adaptor = OutletsAdaptor()

        rv.adapter = adaptor

        viewModel.outlets.observe(viewLifecycleOwner, Observer { outlets ->
            adaptor.setData(outlets)
        })

        viewModel.userLoc.observe(viewLifecycleOwner, Observer {
            locatingString.text = "Longitude ${it.longitude}, Latitude ${it.latitude}"
        })

        return view
    }


}
