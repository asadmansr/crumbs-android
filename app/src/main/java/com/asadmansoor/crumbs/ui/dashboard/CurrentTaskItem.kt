package com.asadmansoor.crumbs.ui.dashboard

import com.asadmansoor.crumbs.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_current_tasks.*


class CurrentTaskItem(
    val item: String
): Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int){
        viewHolder.apply {
            item_task.text = "hi"
        }
    }

    override fun getLayout() = R.layout.item_current_tasks
}
