package uteevbkru;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by anton on 15.06.17.
 */

public class TonyVettuList<T> implements List<T>{
    private int modCount;
    private final int MULT = 2;
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int size = 0;

    public void TonyVettuList(){
        //TODO
    }
    //++++++++++++
    @Override
    public int size(){
        return this.size;
    }
    //+++++++++++++
    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    //**********
    //TODO
    //**********
    @Override
    public Iterator<T> iterator(){
        return  null;
    }
    //++++++++++++
    @Override
    public Object[] toArray(){
        return Arrays.copyOf(array, size);
    }
    /**
     * Скопировал из ArrayList, не очень понял суть!!!
     * //TODO think!!
     * @param a
     * @param <T>
     * @return
     */
    //+++++++++++++
    @Override
    public <T> T[] toArray(T[] a){
        if (a.length < size)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
    //+++++++++++++
    @Override
    public boolean add(T item){
        sizePlus();

        array[size++] = item;
        return true;
    }
    //++++++++++++
    @Override
    public void add(int index, T element){
        checkRanges(index);
        sizePlus();

        //Нужно сместить все элементы, что правее индекса на 1 вправо
        int count = size - index;
        for(int j = count; j >= 0; j--) {
            array[j + index + 1] = array[j + index];
        }
        array[index] = element;
        size++;
    }

    //+++++++++
    @Override
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    /**
     * Проверяет наличие всех элементов колллекции @param с в array!
     * @param c входная коллекция
     * @return если все элементы есть то true
     */
    //+++++++++++++
    @Override
    public boolean containsAll(Collection<?> c){
        Object[] a = c.toArray();
        int contain = 0;
        for(int i = 0; i < a.length; i++) {
                if (indexOf(a[i]) >= 0)
                    contain++;
        }
        if(contain == a.length)
            return true;
        return false;
    }
    //++++++++++++++
    @Override
    public boolean addAll(int index, Collection<? extends T> c){
        checkRanges(index);

        modCount++;
        Object[] a = c.toArray();
        //Нужно все элементы, что правее индекса сместить на a.lenght вправо
        int count = size - index;
        for(int j = count; j >= 0; j--) {
            array[j + index + 1 + a.length] = array[j + index];
        }
        for(int j = 0; j < a.length; j++){
            sizePlus();
            array[j + index + 1] = a[j];
            size++;
        }
        return true;
    }
    //+++++++++++++++
    @Override
    public boolean addAll(Collection<? extends T> c){
        modCount++;
        Object[] a = c.toArray();
        for(int i = 0; i < a.length; i++){
            sizePlus();
            array[size++] = a[i];
            //add(a[i]); Почему не работает??? TODO think
        }
        return true;
    }

    /**
     * !!!!!!!!______Интересная функция_____!!!!!!!!!
     *
     *Удаляет элементы из array, но только те что есть в входной коллекции
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c){
        Objects.requireNonNull(c);

        Object[] a = c.toArray();
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < size; j++){
                /**
                 * __???___Нормально ли здесть use equals * __???___
                 * In java library use contain!!!
                 */
                if(a[i].equals(array[j])){
                    array[j] = null;
                    break;//
                }
            }

        }
        Object[] newMass = new Object[array.length];
        int size_newmas = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                newMass[size_newmas] = array[i];
                size_newmas++;
            }
        }
        array = newMass;
        sizeMinus();
        return true;
    }
    //+++++++++
    @Override
    public boolean retainAll(Collection<?> c){
        Objects.requireNonNull(c);

        Object[] a = c.toArray();
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < size; j++){
                /**
                 * __???___Нормально ли здесть use equals * __???___
                 * In java library use contain!!!
                 */
                if(a[i].equals(array[j]) == false){
                    array[j] = null;
                    break;
                }
            }

        }
        Object[] newMass = new Object[array.length];
        int size_newmas = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                newMass[size_newmas] = array[i];
                size_newmas++;
            }
        }
        array = newMass;
        sizeMinus();
        return true;
    }
    //+++++++++++++++
    @Override
    public void clear(){
        for(int i = 0; i < array.length; i++)//TODO  ?? может size
            array[i] = null;
    }
    //+++++++++++++
    @Override
    public T get(int index){
        checkRanges(index);

        return (T) array[index];
    }
    //+++++++++++
    @Override
    public T set(int index, T element){
        checkRanges(index);

        array[index] = element;
        return element;
    }
    //+++++++++++++++++
    @Override
    public T remove(int index) {
        checkRanges(index);

        modCount++;
        for (int i = index; i < size; i++){//смещаем правые элементы влево на 1
            array[i] = array[i + 1];
        }
        array[size] = null;// for work of GC
        size--;
        sizeMinus();
        return (T) array[index];
    }
    //+++++++++
    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);

        int place = 0;
        for(int i = 0; i  < array.length; i++){
            if(o.equals(array[i])){
                place = i;
                break;
            }
        }
        for(int j = place; j < array.length; j++){
            array[j] = array[j + 1];
        }
        size--;
        sizeMinus();
        return true;
    }
    //++++++++++
    @Override
    public int indexOf(Object o){
        if(o == null){
            for (int i = 0; i < size; i++) {
                if (array[i] == null)
                    return i;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }
    //+++++++++++++++++
    @Override
    public int lastIndexOf(Object o){
        if(o == null){
            int val = -1;
            for (int i = 0; i < size; i++) {
                if (array[i] == null)
                    val = i;
            }
            return val;
        }
        else {
            int val = -1;
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i]))
                    val = i;
            }
            return val;
        }
    }

    @Override
    public ListIterator<T> listIterator(){
        return new ListIterVet( 0);
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
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkRanges(int index){
        // Index не может быть равен size!!!
        // size на один всегда больше!!!!
        if((index > size) || (index < 0))
            throw new IndexOutOfBoundsException("Index " + index + " size" + size);
    }

    private void sizePlus(){
        modCount++;
        if (size == array.length - 1) {
            resize(array.length * MULT);
        }

    }
    /**
     * Уменьшение размера массива!!!
     */
    private void sizeMinus(){
        if(array.length > INIT_SIZE && size < array.length/CUT_RATE)
            resize(array.length/MULT);
    }


    private class ListIterVet implements ListIterator<T>{
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int modCount;
        int expectedModCount = modCount;

        ListIterVet(int index) {
            super();
            cursor = index;
        }
        //+++++++++
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }
        //++++++++++
        @Override
        public int nextIndex() {
            return cursor;
        }
        //++++++++++
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        //++++++++++++++++
        @Override
        public void set(T t) {
            if(lastRet < 0)
                throw new IllegalStateException();

            checkForComodification();
            try{
                TonyVettuList.this.set(lastRet, t);
            }
            catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }
        //+++++++++++
        @Override
        public T previous() {
            checkForComodification();

            int i = cursor - 1;
            if(i < 0)
                throw new NoSuchElementException();
            Object[] arr = TonyVettuList.this.array;
            if(i >= arr.length)
                throw new ConcurrentModificationException();// TODO think ___?????____Why not is IndexOutOfBoundsException()?
            cursor = i;
            return (T) arr[lastRet = i];
        }
        //+++++++++++++
        @Override
        public boolean hasNext() { return cursor != size;}
        //+++++++++
        @Override
        public T next() {
            checkForComodification();

            int i = cursor;
            if(i < 0)// Это место в библеотеке реализовано не правильно!! i < size!
                throw new NoSuchElementException();
            Object[] arr = TonyVettuList.this.array;
            if(i >= arr.length)
                throw new ConcurrentModificationException();// TODO think ___?????____Why not is IndexOutOfBoundsException()?
            cursor = i + 1;
            return (T) arr[lastRet = i];
        }
        //+++++++++
        @Override
        public void add(T t) {
            checkForComodification();

            try {
                int i = cursor;
                TonyVettuList.this.add(i,t);
                cursor = i + 1;
                lastRet = -1;
                modCount = expectedModCount;
            }
            catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
             }
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                TonyVettuList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        private void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
            //TODO check HOW i use modCount!
        }
    }
}
