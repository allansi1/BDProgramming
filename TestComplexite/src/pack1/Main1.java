package pack1;

import java.util.*;

public class Main1  {
	
	public static int indexOfMax(int[] list ) {
		
		int m = 0;
		for(int i = 0; i < list.length;i++) {
			if(list[i]> list[m]) {
				m=i;
			}
		}
		return m;
		
	}
	
	public static void mySort(int[] list) {
		int m, temp;
		for (int j = 0; j< list.length;j++) {
			m=j;
			for(int i = j; i < list.length;i++) {
				if(list[i]> list[m]) {
					m=i;
				}
			}
			
			temp = list[j];
			list[j] = list[m];
			list[m]=temp;
		
			
		}
	}
	
	public static void mySort2(List<Integer> list) {
		
		for(int a : list) {
			Collections.sort(list);
			System.out.println(a);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
