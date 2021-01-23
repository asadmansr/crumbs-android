package com.asadmansoor.crumbs.ui.dashboard.item

import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.CurrentEpic
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_current_epics.*


class CurrentEpicItem(
    val epicItem: CurrentEpic
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_epic_date.text = epicItem.createdAtString
            item_epic_name.text = epicItem.title
            item_epic_status.text = epicItem.statusString
        }
    }

    override fun getLayout() = R.layout.item_current_epics
}
