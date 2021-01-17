package com.asadmansoor.crumbs.ui.epic

import com.asadmansoor.crumbs.R
import com.asadmansoor.crumbs.data.domain.Story
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_current_story.*

class CurrentStoryItem(
    val storyItem: Story
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            item_story_title.text = storyItem.title
            if (storyItem.completed){
                item_story_completed.setImageResource(R.drawable.baseline_check_circle_white_24)
            } else {
                item_story_completed.setImageResource(R.drawable.baseline_radio_button_unchecked_white_24)
            }
        }
    }

    override fun getLayout() = R.layout.item_current_story
}
