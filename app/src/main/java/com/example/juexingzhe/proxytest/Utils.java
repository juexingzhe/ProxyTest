package com.example.juexingzhe.proxytest;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by juexingzhe on 2017/5/22.
 */

public class Utils {

    public static final String TAG = "ProxyTest";

    public static void injectEvent(Activity activity) {
        if (null == activity) {
            return;
        }

        Class<? extends Activity> activityClass = activity.getClass();
        Method[] declaredMethods = activityClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(onClick.class)) {
                Log.i(Utils.TAG, method.getName());
                onClick annotation = method.getAnnotation(onClick.class);
                //获取button id
                int[] value = annotation.value();
                //获取EventType
                EventType eventType = annotation.annotationType().getAnnotation(EventType.class);
                Class listenerType = eventType.listenerType();
                String listenerSetter = eventType.listenerSetter();
                String methodName = eventType.methodName();

                //创建InvocationHandler和动态代理(代理要实现listenerType，这个例子就是处理onClick点击事件)
                ProxyHandler proxyHandler = new ProxyHandler(activity);
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, proxyHandler);

                proxyHandler.mapMethod(methodName, method);
                try {
                    for (int id : value) {
                        //找到Button
                        Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View btn = (View) findViewByIdMethod.invoke(activity, id);
                        //根据listenerSetter方法名和listenerType方法参数找到method
                        Method listenerSetMethod = btn.getClass().getMethod(listenerSetter, listenerType);
                        listenerSetMethod.setAccessible(true);
                        listenerSetMethod.invoke(btn, listener);
                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void injectView(Activity activity) {
        if (null == activity) return;

        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                //找到InjectView注解的field
                InjectView annotation = field.getAnnotation(InjectView.class);
                //找到button的id
                int value = annotation.value();

                try {
                    //找到findViewById方法
                    Method findViewByIdMethod = activityClass.getMethod("findViewById", int.class);
                    findViewByIdMethod.setAccessible(true);
                    findViewByIdMethod.invoke(activity, value);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }

    }


}
