import java.util.Arrays;

public class MQSort {
	public static void main(String[] args) {
     
        int[] instance1 = {2,3,7,9,10,15,18,13,20}; // nearly sorted
        int[] instance2 = {4,3,7,0,1,14,12,2,6}; // random

        int[] instco1 = Arrays.copyOf(instance1, instance1.length); //copy of array because i am going to sort it for both sorting algorithms
        long s = System.nanoTime();
        MergeSort.sort(instco1);
        long e = System.nanoTime();
        System.out.println("MergeSort Instance 1 (Nearly Sorted): " +Arrays.toString(instco1));
        System.out.println("Time: "+(e-s) + " ns\n");

        instco1 = Arrays.copyOf(instance1, instance1.length);
        s= System.nanoTime();
        QuickSort.sort(instco1);
        e= System.nanoTime();
        System.out.println("QuickSort Instance 1 (Nearly Sorted) " + Arrays.toString(instco1));
        System.out.println("Time: " +(e-s)+ " ns\n");

        int[] copy2 = Arrays.copyOf(instance2, instance2.length);
        s= System.nanoTime();
        MergeSort.sort(copy2);
         e= System.nanoTime();
        System.out.println("MergeSort Instance 2 (Random): " + Arrays.toString(copy2));
        System.out.println("Time: " + (e-s) + " ns\n");

        copy2 = Arrays.copyOf(instance2, instance2.length);
        s= System.nanoTime();
        QuickSort.sort(copy2);
        e= System.nanoTime();
        System.out.println("QuickSort Instance 2 (Random): " + Arrays.toString(copy2));
        System.out.println("Time: " +(e-s) + " ns\n");
    }

}


class QuickSort {
	public static void sort(int a[]) {
		sort(a,0,a.length-1);
	}
	public static void sort(int a[],int lo,int hi) {
		if(hi<=lo) return;
		int pivot=partition(a,lo,hi);
		sort(a,lo,pivot-1);
		sort(a,pivot+1,hi);
	}
	public static int partition(int a[],int lo,int hi) {
		int i=lo; int j=hi+1;
		int v=a[lo]; //partition item
		while(true) { //scanning right and left
			while(less(a[++i],v)) if(i==hi) break;
			while(less(v,a[--j])) if(j==lo) break;
			if(i>=j) break;
			swap(a,i,j);
		}
		swap(a,lo,j);  //put v into a new position
		return j;
	}
	public static boolean less(int a,int b) {
		if(a<b) {
			return true;
		}
		else  return false;
	}
	public static void swap(int a[],int i,int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	

}
class MergeSort {
	static int aux[]; //auxilary array for merge
	public static void sort(int a[]) {
		aux=new int[a.length];
		sort(a,0,a.length-1);
	}
	public static void sort(int a[],int lo,int hi) {
		if(hi<=lo) return;
		int mid=(lo+hi)/2;
		sort(a,lo,mid); //Sort left
		sort(a,mid+1,hi); //Sort right
		merge(a,lo,mid,hi); //Merge them
	}
	public static void merge(int a[],int lo,int mid,int hi) {
		int i=lo; int j=mid+1;
		for(int k=lo;k<=hi;k++) {
			aux[k]=a[k];
		}
		for(int k=lo;k<=hi;k++) { //merge them back to a[lo...............hi]
			if(i>mid) a[k]=aux[j++];
			else if(j>hi) a[k]=aux[i++];
			else if(less(aux[j],aux[i])) a[k]=aux[j++];
			else a[k]=aux[i++];
		}
	}
	public static boolean less(int a,int b) {
		if(a<b) {
			return true;
		}
		else  return false;
	}
	
}
