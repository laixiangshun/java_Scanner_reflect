package com.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.java.entity.User;

public class Text {

	public static void main(String[] args) {
		//获取类的方式一：
		try {
			Class c1=Class.forName("com.java.entity.User");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取类的方式二：
		Class c2=User.class;
		//获取类的第三种方式：
		User u=new User();
		Class c3=u.getClass();
		
		
		//创建对象
		System.out.println("=======创建对象===========");
		try {
			Object o=c3.newInstance();//获取类的无参数构造函数
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取类的属性
		System.out.println("---------获取类的属性----------");
		Field[] fs=c3.getDeclaredFields();//获取所有属性
		StringBuffer sb=new StringBuffer();
		sb.append(Modifier.toString(c3.getModifiers())+" class "+c3.getSimpleName()+"{\n");
		for (Field field : fs) {
			sb.append("\t");
			sb.append(Modifier.toString(field.getModifiers())+" ");//获取属性的修饰符
			sb.append(field.getType().getSimpleName()+" ");//获取属性的类型名称
			sb.append(field.getName()+";\n");//获取属性的名称
		}
		sb.append("}");
		System.out.println(sb);
		//获取指定属性
		System.out.println("-------------获取指定属性----------");
		try {
			Field field=c3.getDeclaredField("id");
			try {
				Object o=c3.newInstance();
				field.setAccessible(true);//使用反射机制打破封装性，导致java对象的属性不安全
				//给对象属性赋值
				field.set(o, 110);
				//获取对象的赋值
				field.get(o);
				System.out.println(field.get(o));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//通过有参数构造方法获取类的实例
		System.out.println("----------------通过有参数构造方法获取类的实例----------------");
		try {
			Constructor<?> con=c3.getConstructor(int.class,String.class,String.class,int.class);//获取有参数构造函数
			User user=(User)con.newInstance(11,"小明","abc",24);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//调用方法
		System.out.println("-----------------------调用方法-------------------");
		try {
			//不带参数方法的调用
			System.out.println("=============不带参数方法的调用=============");
			Method method=c3.getMethod("add");
			method.invoke(c3.newInstance());
			//带参数方法的调用
			System.out.println("=================带参数方法的调用================");
			Method m=c3.getMethod("addWithParameters", String.class,String.class);
			m.invoke(c3.newInstance(), "小红","bbb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取getter，setter方法
		System.out.println("---------------------获取getter，setter方法-----------------");
		Class<?> cc=null;
		Object obj=null;
		try {
			cc=Class.forName("com.java.entity.User");
			obj=cc.newInstance();
			setter(obj, "name", "青川", String.class);
			setter(obj, "password", "cccccccc",String.class);
			System.out.println("==========name的getter==========");
			getter(obj, "name");
			System.out.println("=============password的getter===========");
			getter(obj, "password");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//setter注入对象的属性
		System.out.println("-----------------setter注入对象的属性------------------");
		Class ccc;
		try {
			ccc = Class.forName("com.java.entity.User");
			Object oob=ccc.newInstance();
			String setname="set"+"Name";
			Method mm=ccc.getMethod(setname, String.class);
			mm.invoke(oob, "东方不败");
			mm=ccc.getMethod("getName");
			System.out.println(mm.invoke(oob));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * setter
	 *  @param o：要操作的对象
    * @param att：要操作的属性
    * @param value：要设置的属性内容
    * @param type：要设置的属性类型
	 */
	public static void setter(Object o,String att,Object value,Class<?> type){
		try {
			Method m=o.getClass().getMethod("set"+initString(att), type);
			m.invoke(o, value);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void getter(Object o,String att){
		try {
			Method m=o.getClass().getMethod("get"+initString(att));
			System.out.println(m.invoke(o));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static String initString(String oldStr){
		String newstr=oldStr.substring(0,1).toUpperCase()+oldStr.substring(1);
		return newstr;
	}
}
