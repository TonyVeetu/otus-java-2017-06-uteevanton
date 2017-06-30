package uteevbkru;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by anton on 15.06.17.
 */


public class TonyVettuList<T> implements List<T>{
    private final int INIT_SIZE = 16;
    private final int CUR_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    //private int pointer = 0;
    private int size = 0;

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public boolean contains(Object o){
        return false;
    }

    //@Override
    //public Iterator<T> iterator(){
    //    return  new Iterator<T>(null);
    //}

    @Override
    public Object[] toArray(){
        return new Object[0];
    }
    @Override
    public <T> T[] toArray(T[] a){
        return null;
    }
    @Override
    public boolean add(T item){
        if(size == array.length-1)
            resize(array.length*2);
        array[size++] = item;
        return true;
    }
    @Override
    public boolean remove(Object o){
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c){
        return false;
    }
    //_____???______
    @Override
    public boolean addAll(Collection<? extends T> c){
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c){
        return false;
    }
    @Override
    public boolean retainAll(Collection<?> c){
        return false;
    }
    @Override
    public void clear(){

    }
    @Override
    public T get(int a){
        return (T) array[a];
    }
    @Override
    public T set(int index, T element){
        if(index <= array.length) {
            array[index] = element;
        }
    }
    @Override
    public void add(int index, T element){

    }
    @Override
    public T remove(int index){

    }
    @Override
    public int indexOf(Object o){
        return 0;
    }
    @Override
    public int lastIndexOf(Object o){
        return 0;
    }
    @Override
    public ListIterator<T> listIterator(){
        return null;
    }
    @Override
    public ListIterator<T> listIterator(int  index){
        return null;
    }
    @Override
    public List<T> subList(int fromIndex, int toIndex){
        return null;
    }


    private void resize(int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }







}
