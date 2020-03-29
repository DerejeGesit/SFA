package com.sahalu.sfa
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sahalu.sfa.data.outletnew.OutletAmedments
import com.sahalu.sfa.viewmodels.outletamedmentpage.OutletAmedmentViewModel
import com.sahalu.sfa.utilities.InjectorUtils
import java.util.Date

class OutletAmedmentFragment: Fragment() {

    private lateinit var  cmdCity : Spinner
    private  lateinit var cmdSubCity : Spinner
    private lateinit var cmdSpecialArea  : Spinner
    private lateinit var txtOutletName  : EditText
    private  lateinit var  txtPhone  : EditText
    private lateinit  var  txtOwnerName : EditText
    private val viewModel: OutletAmedmentViewModel by viewModels {
        InjectorUtils.provideOutletAmedmentListRModelFactory(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outletamedment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cmdCity= view.findViewById(R.id.cmdCity) as Spinner
        cmdSubCity = view.findViewById(R.id.cmdSubCity) as Spinner
        cmdSpecialArea= view.findViewById(R.id.cmdSpecialArea) as Spinner
        txtOutletName=  view.findViewById<EditText >(R.id.txtOutletName)
        txtPhone= view.findViewById<EditText >(R.id.txtPhone)
        txtOwnerName =  view.findViewById<EditText >(R.id.txtOwnerName)

      if (AppPermission.checkLocationPermission(context!!)) {
           view.findViewById<Button>(R.id.btnLocation).visibility = View.INVISIBLE
           view.findViewById<Button>(R.id.btnSubmit).isEnabled = true;
      }
      else
      {
         view.findViewById<Button>(R.id.btnLocation).visibility = View.VISIBLE
         view.findViewById<Button>(R.id.btnSubmit).isEnabled = false;
       }


        var adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_list_item_1, listOf<String>("").union(viewModel.getCities().distinctBy { it.city }.map { it.city }).toList())

        cmdCity.adapter = adapter

        cmdCity.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                var adapter = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1,
                    // myList.map { it.city }
                    listOf<String>("").union(viewModel.getCities().filter { it.city == cmdCity.selectedItem.toString() }.distinctBy { it.sub_city }.map { it.sub_city }).toList()
                )
                cmdSubCity.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }

        }

        cmdSubCity.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                var adapter = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1,
                    // myList.map { it.city }
                    listOf<String>("").union(viewModel.getCities().filter { it.sub_city == cmdSubCity.selectedItem.toString() }.distinctBy { it.specific_area }.map { it.specific_area }).toList()
                )
                cmdSpecialArea.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }



        }


        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            if(!AppValidation().EmptyTextValidation(txtOutletName,requireContext(),"Outlet is Empty!") &&
                !AppValidation().EmptyTextValidation(txtOwnerName,requireContext(),"Owner is Empty!") &&
                !AppValidation().EmptyTextValidation(txtPhone,requireContext(),"Phone is Empty!") &&
                !AppValidation().PhoneNoValidation(txtPhone,requireContext(),"Invalid Phone No!") &&
            !AppValidation().EmptyComboValidation(cmdCity,requireContext(),"City is not Selected!") &&
                !AppValidation().EmptyComboValidation(cmdSubCity,requireContext(),"SubCity is not Selected!") &&
                !AppValidation().EmptyComboValidation(cmdSpecialArea,requireContext(),"SpecialArea is not Selected!")
               )
            {
                var geolocation = viewModel.outloc;
                var outamed = OutletAmedments(txtOutletName.text.toString(),
                    txtOwnerName.text.toString(), txtPhone.text.toString(),"","",
                    "","","",geolocation.lon,geolocation.lat,"","","","",false,"",false,"","")
               viewModel.outletamed.addOutletAmedment(outamed);
            }

        }

    }
}