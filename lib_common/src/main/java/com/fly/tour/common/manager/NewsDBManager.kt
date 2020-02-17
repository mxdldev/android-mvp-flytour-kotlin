package com.fly.tour.common.manager

import android.content.Context

import com.fly.tour.common.R
import com.fly.tour.common.util.log.KLog
import com.fly.tour.db.dao.NewsDetailDao
import com.fly.tour.db.dao.NewsTypeDao
import com.fly.tour.db.entity.NewsDetail
import com.fly.tour.db.entity.NewsType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.reflect.Type

/**
 * Description: <NewsDBManager><br>
 * Author:      mxdl<br>
 * Date:        2019/5/28<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
</NewsDBManager> */
class NewsDBManager private constructor(private val mContext: Context) {

    val listNewsType: List<NewsType>?
        get() = NewsTypeDao(mContext).listNewsType

    fun initNewsDB() {
        val mNewsTypeDao = NewsTypeDao(mContext)
        if (mNewsTypeDao.isEmpty) {
            val gson = Gson()
            val type = object : TypeToken<List<NewsType>>() {

            }.type
            val mListNewsType = gson.fromJson<List<NewsType>>(getStringByResId(R.raw.news_type), type)
            mNewsTypeDao.addListNewStype(mListNewsType)
        }
        val newsDetailDao = NewsDetailDao(mContext)
        if (newsDetailDao.isEmpty) {
            val gson = Gson()
            val type = object : TypeToken<List<NewsDetail>>() {

            }.type
            val newsDetailList = gson.fromJson<List<NewsDetail>>(getStringByResId(R.raw.news_detail), type)
            newsDetailDao.addListNewsDetail(newsDetailList)
        }
    }

    private fun getStringByResId(resid: Int): String {
        val inputStream = mContext.resources.openRawResource(resid)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        var line: String? = null
        try {
            while (({line = bufferedReader.readLine()}) != null) {
                stringBuilder.append(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return stringBuilder.toString()
    }

    companion object {
        val TAG = NewsDBManager::class.java!!.getSimpleName()
        private var newsDBManager: NewsDBManager? = null

        fun getInstance(context: Context): NewsDBManager? {
            if (newsDBManager == null) {
                synchronized(NewsDBManager::class.java) {
                    if (newsDBManager == null) {
                        newsDBManager = NewsDBManager(context)
                    }
                }
            }
            return newsDBManager
        }
    }
}
