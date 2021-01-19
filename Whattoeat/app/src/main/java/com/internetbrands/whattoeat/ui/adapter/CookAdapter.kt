package com.internetbrands.whattoeat.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.internetbrands.whattoeat.R
import com.internetbrands.whattoeat.bean.Cook
import com.internetbrands.whattoeat.ui.activity.AddMenuActivity
import com.internetbrands.whattoeat.ui.listener.CookCheckListener
import com.internetbrands.whattoeat.util.Utils

/**
Created by Wei Lai 2020/5/13
Description:
 */
class CookAdapter (var context:Context) : RecyclerView.Adapter<CookAdapter.CookViewHolder>(){

    var list:List<Cook> = ArrayList()
    private var typeArray:Array<String> = context.resources.getStringArray(R.array.cook_type_array)
    var selectedList:ArrayList<Cook> = ArrayList()
    var selected:MutableLiveData<List<Cook>> = MutableLiveData()
    fun selectedList():LiveData<List<Cook>> = selected
    var cookCheckListener:CookCheckListener?=null

    var meatCookList:List<Cook> = ArrayList()
    var noMeatCookList:List<Cook> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookViewHolder {
        var view:View = LayoutInflater.from(context).inflate(R.layout.item_cookmenu,parent,false)
        return CookViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CookViewHolder, position: Int) {
        var cook = list[position]
        holder.nameTv.setText(cook.name)
        holder.typeTv.setText(typeArray[cook.type])
        if(context is AddMenuActivity){
            holder.submitBtn.visibility = View.VISIBLE
            if(selectedList.contains(cook)){
                holder.submitBtn.setText(R.string.del)
                holder.item.setBackgroundColor(context.getColor(R.color.color_308ee3_40))
            }else{
                holder.submitBtn.setText(R.string.add)
                holder.item.setBackgroundColor(context.getColor(R.color.transparent))
            }
            holder.submitBtn.setOnClickListener {
                if(selectedList.contains(cook)){
                    selectedList.remove(cook)
                }else{
                    selectedList.add(cook)
                }
                selected.value = selectedList
                notifyItemChanged(position)
            }
        }else{
            holder.submitBtn.setText(R.string.check)
            holder.submitBtn.setOnClickListener{
                cookCheckListener?.onCheck(cook)
            }
        }
    }

    fun getRandomCook(){
        var meatCook = Utils.getRandomItem(meatCookList)
        var noMeatCook = Utils.getRandomItem(noMeatCookList)

        selectedList.clear()
        selectedList.add(meatCook)
        selectedList.add(noMeatCook)

        selected.value = selectedList

        for(cook in selectedList){
            var position = list.indexOf(cook)
            Log.i("step","position:"+position)
            notifyItemChanged(position)
        }
    }


    class CookViewHolder(var item: View):RecyclerView.ViewHolder(item){
        var nameTv:TextView
        var typeTv:TextView
//        var countTv:TextView
//        var lastCookTv:TextView
        var submitBtn:Button

        init{
            nameTv = item.findViewById(R.id.nameTv)
            typeTv = item.findViewById(R.id.typeTv)
//            countTv = item.findViewById(R.id.countTv)
//            lastCookTv = item.findViewById(R.id.lastCookTv)
            submitBtn = item.findViewById(R.id.submitBtn)
        }
    }
}