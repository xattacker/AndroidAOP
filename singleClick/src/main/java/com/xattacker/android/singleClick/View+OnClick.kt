package com.xattacker.android.singleClick

import android.view.View

/**
 * 版权：渤海新能 版权所有
 * @author zhujiang
 * 版本：1.5
 * 创建日期：2020/4/29
 * 描述：AndroidAOP
 *
 */


inline fun View.setSingleClick(duration: Long = 1000, crossinline action:()->Unit)
{
    var lastClick=0L

    setOnClickListener {
        val gap = System.currentTimeMillis() - lastClick

        if (gap < duration)
        {
            return@setOnClickListener
        }

        lastClick= System.currentTimeMillis()
        action.invoke()
    }
}