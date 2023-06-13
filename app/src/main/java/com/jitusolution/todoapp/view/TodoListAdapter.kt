package com.jitusolution.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jitusolution.todoapp.model.Todo
import com.jitusolution.todoapp.R
import com.jitusolution.todoapp.databinding.TodoItemLayoutBinding

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoItemLayoutInterface {
    class TodoViewHolder(var view:TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder
    {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false)
        return TodoViewHolder(view)






    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.checkListener=this
        holder.view.editListener=this

        holder.view.checkTask.isChecked=false
//        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        checktask.text = todoList[position].title
//
//
//
//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
//            adapterOnClick(todoList[position])
//
//        }
//
//        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
//        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//
//        imgEdit.setOnClickListener {
//            val action =
//                TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//
//            Navigation.findNavController(it).navigate(action)
//        }
//
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked == true) {
//                adapterOnClick(todoList[position])
                todoList[position].is_done=1
            }
        }

    }

    override fun getItemCount(): Int
    {
        return todoList.size

    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()

        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}
