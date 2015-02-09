// Dane Olson
// 2/9/15
// Ray Toal CMSI 282

package Heap;

import java.util.*;

public class PriorityQueue<E extends Comparable<E>> {
	
	private ArrayList<E> heap; // implement with an arraylist
	
	public PriorityQueue(){
		heap = new ArrayList<E>();
	}
	
	// can take an array and turn it into a priority queue 
	public PriorityQueue(E[] array){
		heap = new ArrayList<E>(Arrays.asList(array));
		for(int i = (array.length - 1) / 2; i >= 0; i--){
			minHeapify(i, heap.size() - 1);
		}
	}
	
	// adds an element to the heap, maintaining heap strucutre 
	public void add(E element){
		heap.add(element);
		for(int i = heap.size() - 1; i > 0; i = parentIndex(i)){
			if(heap.get(i).compareTo(heap.get(parentIndex(i))) > 0){
				swap(i, parentIndex(i));
			}
		}
	}
	
	// removes the first elements of the queue, also the minimum value
	public E remove(){
		if(heap.size() < 0){
			return null;
		}
		E value = heap.get(0);
		heap.set(0, heap.remove(heap.size() - 1));
		minHeapify(0, heap.size() - 1);
		return value;
	}
	
	// returns the length of the priorityqueue
	public int length(){
		return heap.size();
	}
	
	// returns a string representation of the heap in sorted order. The digits are separated by
	// commas and the array is enclosed in brackets. 
	public String toString(){
		this.sort();
		return heap.toString();
	}
	
	// sorts the array using the heapsort algorithm
	public void sort(){
		this.heapSort(this.length() - 1);
	}
	
	// the heapsort algorithm 
	private void heapSort(int lastNode){
		while(lastNode > 0){
			swap(0, lastNode);
			lastNode--;
			minHeapify(0, lastNode);
		}
	}
	
	// after the call the array will be a minimum heap starting at index i. lastNode is a 
	// integer rep of the size of the heap.
	private void minHeapify(int i, int lastNode){
		int largestNode = i;
		if(leftIndex(i) <= lastNode && heap.get(leftIndex(i)).compareTo(heap.get(i)) > 0){
			largestNode = leftIndex(i);
		}
		if(rightIndex(i) <= lastNode 
				&& heap.get(rightIndex(i)).compareTo(heap.get(largestNode)) > 0){
			largestNode = rightIndex(i);
		}
		if(largestNode != i){
			swap(i, largestNode);
			minHeapify(largestNode, lastNode);
		}
	}
	
	// swaps two values in an array
	private void swap(int i, int j){
		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	// returns the parent index if the array was represented as a binary tree
	private int parentIndex(int index){
		return (index) / 2;
	}
	
	// return the left index if the array was represented as a binary tree
	private int leftIndex(int index){
		return 2 * index + 1;
	}
	
	// returns the right index if the array was represented as a binary tree
	private int rightIndex(int index){
		return 2 * index + 2;
	}
}
