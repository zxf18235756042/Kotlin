package com.kotion.mydemo.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseAdapter<D>(
    val context: Context,
    var list: ArrayList<D>,
    val layouts: SparseArray<Int>
) : RecyclerView.Adapter<BaseAdapter<D>.BaseVH>() {
    /**
     * 刷新數據
     */
    fun refreshData(data: ArrayList<D>) {
        list = data
        notifyDataSetChanged()
    }

    /**
     * 數據的追加
     */

    fun addData(data:ArrayList<D>){
        list.addAll(data)
        notifyDataSetChanged()
    }


    /**
     * 用来初始化创建ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        var dataBinding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return BaseVH(dataBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        //获取当前条目布局的ID
        val layoutId = getItemViewType(position)
        if (layouts!=null && layouts.size()>0) {
            //获取layout id所对应的BR的id
            var type = layouts.get(layoutId)
            //界面组件显示数据的绑定
            holder.databinding.setVariable(type, list.get(position))
        }
        holder.databinding.root.tag = list.get(position)
        bindData(holder.databinding, list.get(position), layoutId)

    }

    override fun getItemViewType(position: Int): Int {
        return layoutId(position)
    }

    /**
     * 获取对应的布局
     */
    protected abstract fun layoutId(position: Int): Int

    protected abstract fun bindData(binding: ViewDataBinding, data: D, layId: Int)

    inner class BaseVH(val databinding: ViewDataBinding) : RecyclerView.ViewHolder(databinding.root)
}

