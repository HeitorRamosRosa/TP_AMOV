package pt.isec.supraindustries.tp_amov

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.isec.supraindustries.tp_amov.Data.Lista

class ListAdapter(private val listList: ArrayList<Lista>,private val listener: OnItemClickListener): RecyclerView.Adapter<ListAdapter.UserViewHolder>(){

    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val username: TextView = view.findViewById(R.id.listName)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val pos : Int = adapterPosition
            if(pos != RecyclerView.NO_POSITION){
                listener.onItemClick(pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mostrar_listas_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.username.text = listList[position].nome;

    }

    override fun getItemCount(): Int = listList.size;

    interface OnItemClickListener{

        fun onItemClick(pos : Int)
    }
}


