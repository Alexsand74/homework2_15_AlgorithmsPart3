package org.example;


import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private int size;

    public IntegerListImpl() {
        this.storage = new Integer[10];
    }
    public IntegerListImpl(int intSize) {
        this.storage = new Integer[intSize];
    }

    @Override
    public Integer add(Integer item) {
//        validateSize();
        growIfNeeded();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
//        validateSize();
        growIfNeeded();
        validateItem(item);
        validateIndex(index);
        if (index == size){
            storage[index] = item;
            size++;
            return item;
        }
        System.arraycopy(storage, index, storage, index + 1,size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        return storage[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1){
            throw new IllegalArgumentException("Данного элемента нет !");
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        if (index != size){
            System.arraycopy(storage, index + 1, storage, index ,size - index );
        }
        size--;
        return storage[index];
    }

    @Override
    public boolean contains(Integer item) {
//        return indexOf(item) != -1;  - это было
        validateItem(item);
        Integer[] arrayTemp = toArray();
//        sortInsertion(arrayTemp);
        sortByRecursion(arrayTemp);
        sortBubble(arrayTemp);
        return binarySearch(arrayTemp,item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size-1; i >= 0; i--) {
            if (storage[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
      size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage,size);
    }

    private void validateItem (Integer item){
      if(item == null){
          throw new IllegalArgumentException("Нельзя хранить в списке null-ы !");
      }
    }
//    private void validateSize (){
//      if(size == storage.length){
//          throw new IllegalArgumentException("Размер массива больше заданного !");
//      }
//    }
    private void growIfNeeded(){
        if(size == storage.length){
            grow();
        }
    }
    private void grow(){
        storage = Arrays.copyOf(storage, size + size/2 );
    }
    private void validateIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательный !");
        }
        if (index > size) {
            throw new IllegalArgumentException("Индекс больше заданного размера массива !");
        }
    }
//    public static void sortInsertion(Integer[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int temp = arr[i];
//            int j = i;
//            while (j > 0 && arr[j - 1] >= temp) {
//                arr[j] = arr[j - 1];
//                j--;
//            }
//            arr[j] = temp;
//        }
//    }

    public void sortByRecursion (Integer[] arr){
        quickSort(arr, 0, arr.length-1);
    }
    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public boolean binarySearch(Integer[] arr, Integer element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (arr[mid].equals(element)) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition (arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private Integer partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }
    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    @Override
    public String toString() {
        return "IntegerListImpl  " +
                "storage=" + Arrays.toString(storage) +
                ",       size=" + size ;
    }
}
