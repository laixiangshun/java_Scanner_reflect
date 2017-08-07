package com.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.java.entity.User;

public class Text {

	public static void main(String[] args) {
		//��ȡ��ķ�ʽһ��
		try {
			Class c1=Class.forName("com.java.entity.User");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ȡ��ķ�ʽ����
		Class c2=User.class;
		//��ȡ��ĵ����ַ�ʽ��
		User u=new User();
		Class c3=u.getClass();
		
		
		//��������
		System.out.println("=======��������===========");
		try {
			Object o=c3.newInstance();//��ȡ����޲������캯��
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ȡ�������
		System.out.println("---------��ȡ�������----------");
		Field[] fs=c3.getDeclaredFields();//��ȡ��������
		StringBuffer sb=new StringBuffer();
		sb.append(Modifier.toString(c3.getModifiers())+" class "+c3.getSimpleName()+"{\n");
		for (Field field : fs) {
			sb.append("\t");
			sb.append(Modifier.toString(field.getModifiers())+" ");//��ȡ���Ե����η�
			sb.append(field.getType().getSimpleName()+" ");//��ȡ���Ե���������
			sb.append(field.getName()+";\n");//��ȡ���Ե�����
		}
		sb.append("}");
		System.out.println(sb);
		//��ȡָ������
		System.out.println("-------------��ȡָ������----------");
		try {
			Field field=c3.getDeclaredField("id");
			try {
				Object o=c3.newInstance();
				field.setAccessible(true);//ʹ�÷�����ƴ��Ʒ�װ�ԣ�����java��������Բ���ȫ
				//���������Ը�ֵ
				field.set(o, 110);
				//��ȡ����ĸ�ֵ
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
		
		//ͨ���в������췽����ȡ���ʵ��
		System.out.println("----------------ͨ���в������췽����ȡ���ʵ��----------------");
		try {
			Constructor<?> con=c3.getConstructor(int.class,String.class,String.class,int.class);//��ȡ�в������캯��
			User user=(User)con.newInstance(11,"С��","abc",24);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���÷���
		System.out.println("-----------------------���÷���-------------------");
		try {
			//�������������ĵ���
			System.out.println("=============�������������ĵ���=============");
			Method method=c3.getMethod("add");
			method.invoke(c3.newInstance());
			//�����������ĵ���
			System.out.println("=================�����������ĵ���================");
			Method m=c3.getMethod("addWithParameters", String.class,String.class);
			m.invoke(c3.newInstance(), "С��","bbb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ȡgetter��setter����
		System.out.println("---------------------��ȡgetter��setter����-----------------");
		Class<?> cc=null;
		Object obj=null;
		try {
			cc=Class.forName("com.java.entity.User");
			obj=cc.newInstance();
			setter(obj, "name", "�ന", String.class);
			setter(obj, "password", "cccccccc",String.class);
			System.out.println("==========name��getter==========");
			getter(obj, "name");
			System.out.println("=============password��getter===========");
			getter(obj, "password");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//setterע����������
		System.out.println("-----------------setterע����������------------------");
		Class ccc;
		try {
			ccc = Class.forName("com.java.entity.User");
			Object oob=ccc.newInstance();
			String setname="set"+"Name";
			Method mm=ccc.getMethod(setname, String.class);
			mm.invoke(oob, "��������");
			mm=ccc.getMethod("getName");
			System.out.println(mm.invoke(oob));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*
	 * setter
	 *  @param o��Ҫ�����Ķ���
    * @param att��Ҫ����������
    * @param value��Ҫ���õ���������
    * @param type��Ҫ���õ���������
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
