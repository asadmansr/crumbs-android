package com.asadmansoor.crumbs.ui.completed_epic.detail.item

import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.CompletedStory
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_completed_story.*

class CompletedStoryItem(
    private val storyItem: CompletedStory
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_story_title.text = storyItem.title
        }
    }

    override fun getLayout() = R.layout.item_completed_story
}
