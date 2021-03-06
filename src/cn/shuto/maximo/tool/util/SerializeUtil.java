package cn.shuto.maximo.tool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

public class SerializeUtil {
	/**
     * 序列化,List
     */
    @SuppressWarnings({ "resource", "unchecked" })
    public static <T> boolean writeObject(List<T> list,File file)
    {
		T[] array = (T[]) list.toArray();
        try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(array);
			out.flush();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
    }
    /**
     * 序列化一个对象
     * @param t
     * @param file
     * @return
     */
    @SuppressWarnings({ "resource" })
    public static <T> boolean writeObject(T t,File file)
    {
        try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(t);
			out.flush();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
    }
    /**
     * 反序列化,object
     */
    @SuppressWarnings({ "resource", "unchecked" })
	public static <E> E readObject(File file)
    {
        E object;
        try {
			ObjectInputStream out = new ObjectInputStream(new FileInputStream(file));
			object = (E) out.readObject();
			return object;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return null;
    }
    /**
     * 反序列化,List
     */
    @SuppressWarnings({ "resource", "unchecked" })
	public static <E> List<E> readObjectForList(File file)
    {
        E[] objects;
        try {
			ObjectInputStream out = new ObjectInputStream(new FileInputStream(file));
			objects = (E[]) out.readObject();
			return Arrays.asList(objects);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return null;
    }
}
