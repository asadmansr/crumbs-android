package com.asadmansoor.crumbs.ui.dashboard

import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.db.entity.CurrentEpicEntity
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_current_tasks.*


class CurrentTaskItem(
    val epicItem: CurrentEpicEntity
): Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int){
        viewHolder.apply {
            item_epic_date.text = epicItem.createdAt.toString()
            item_epic_name.text = epicItem.title
            item_epic_description.text = epicItem.description
            item_epic_status.text = epicItem.status
        }
    }

    override fun getLayout() = R.layout.item_current_tasks
}
