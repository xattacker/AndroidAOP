package com.zj.singclick

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 实现防止按钮连续点击
 * @author jiang zhu on 2019/4/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class SingleClick(val value: Long = 1000/* 点击间隔时间 */)
