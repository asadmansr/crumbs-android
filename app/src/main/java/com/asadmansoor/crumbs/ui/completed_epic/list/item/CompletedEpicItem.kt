package com.asadmansoor.crumbs.ui.completed_epic.list.item

import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.CompletedEpic
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_current_epics.*

class CompletedEpicItem(
    val epicItem: CompletedEpic
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_epic_name.text = epicItem.title
        }
    }

    override fun getLayout() = R.layout.item_completed_epics
}
