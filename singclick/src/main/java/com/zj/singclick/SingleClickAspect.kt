package com.zj.singclick

import android.view.View
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

/**
 * 实现防止按钮连续点击
 * @author jiang zhu on 2019/4/19
 */
@Aspect
class SingleClickAspect
{
    /**
     * 定义切点，标记切点为所有被@SingleClick注解的方法
     * com.zj.singclick.SingleClick需要替换成
     * 自己项目中SingleClick这个类的全路径哦
     */
    @Pointcut("execution(@com.zj.singclick.SingleClick * *(..))")
    fun methodAnnotated()
    {
    }

    /**
     * 定义一个切面方法，包裹切点方法
     */
    @Around("methodAnnotated()")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint?)
    {
        if (joinPoint == null)
        {
            return
        }


        // 取出方法的参数
        var view: View? = null
        for (arg in joinPoint.getArgs())
        {
            if (arg is View)
            {
                view = arg
                break
            }
        }
        if (view == null)
        {
            return
        }
        // 取出方法的注解
        val methodSignature = joinPoint.getSignature() as MethodSignature
        val method = methodSignature.method
        if (!method.isAnnotationPresent(SingleClick::class.java))
        {
            return
        }
        val singleClick = method.getAnnotation(SingleClick::class.java)
        // 判断是否快速点击
        if (!ClickHolder.isFastDoubleClick(view, singleClick.value))
        {
            // 不是快速点击，执行原方法
            joinPoint.proceed()
        }
    }

    companion object
    {
        private const val DEFAULT_TIME_INTERVAL: Long = 5000
    }
}