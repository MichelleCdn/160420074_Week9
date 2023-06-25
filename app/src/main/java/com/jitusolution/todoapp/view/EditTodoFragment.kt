package com.jitusolution.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.jitusolution.todoapp.R
import com.jitusolution.todoapp.viewmodel.DetailTodoViewModel
import org.w3c.dom.Text


class EditTodoFragment : Fragment() {

    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        var btnAdd = view.findViewById<Button>(R.id.btnAdd)

        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observeViewModel()

        btnAdd.setOnClickListener {
            val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
            val txtNotes = view.findViewById<TextView>(R.id.txtNotes)
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            val radioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radioButton.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }



    }


    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            val txtTitle = view?.findViewById<TextView>(R.id.txtTitle)
            val txtNotes = view?.findViewById<TextView>(R.id.txtNotes)
            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)

            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)


            when (it.priority) {
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                else -> radioHigh?.isChecked = true

            }
        })
    }


}


