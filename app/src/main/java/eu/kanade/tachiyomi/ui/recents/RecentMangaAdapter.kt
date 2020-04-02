package eu.kanade.tachiyomi.ui.recents

import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import eu.davidea.flexibleadapter.items.IFlexible
import eu.kanade.tachiyomi.data.database.models.Manga
import eu.kanade.tachiyomi.ui.manga.chapter.BaseChapterAdapter
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class RecentMangaAdapter(val delegate: RecentsInterface) :
    BaseChapterAdapter<IFlexible<*>>(delegate) {

    init {
        setDisplayHeadersAtStartUp(true)
    }

    val decimalFormat = DecimalFormat("#.###", DecimalFormatSymbols()
        .apply { decimalSeparator = '.' })

    interface RecentsInterface : RecentMangaInterface, DownloadInterface

    interface RecentMangaInterface {
        fun onHeaderClick(position: Int)
        fun onCoverClick(position: Int)
        fun markAsRead(position: Int)
        fun setCover(manga: Manga, view: ImageView)
        fun isSearching(): Boolean
    }

    override fun onItemSwiped(position: Int, direction: Int) {
        super.onItemSwiped(position, direction)
        when (direction) {
            ItemTouchHelper.LEFT -> delegate.markAsRead(position)
        }
    }
}