package ru.skillbranch.skillarticles.viewmodels

import androidx.lifecycle.LiveData
import ru.skillbranch.skillarticles.data.ArticleData
import ru.skillbranch.skillarticles.data.ArticlePersonalInfo

interface IArticleViewModel {
    /**
     * Получение полной информации о статье из сети
     * (или базы данных если она сохранена, наличие статьи в базе не надо реализовывать в данном уроке)
     */
    fun getArticleContent(): LiveData<List<Any>?>

    /**
     * Получение краткой информации о статье из базы данных
     */
    fun getArticleData(): LiveData<ArticleData?>

    /**
     * Получение пользовательской информации о статье из базы данных
     */
    fun getArticlePersonalInfo(): LiveData<ArticlePersonalInfo?>

    fun handleNightMode()
    fun handleUpText()
    fun handleDownText()
    fun handleBookmark()
    fun handleLike()
    fun handleShare()
    fun handleToggleMenu()

    /**
     * обрабока перехода в режим поиска searchView
     * при нажатии на пункту меню тулбара необходимо отобразить searchView и сохранить состояние при
     * изменении конфигурации (пересоздании активити)
     */
    fun handleSearchMode(isSearch: Boolean)

    /**
     * обрабока поискового запроса, необходимо сохранить поисковый запрос и отображать его в
     * searchView при изменении конфигурации (пересоздании активити)
     */
    fun handleSearch(query: String?)

}