package leetcodetest;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.List;


public class Main {
	
	/*static String calculate(int m, int k) {
		int[] year,num;
		int young,old;
		int number=number(m);
    }*/
	
	static int number(int y) {
		int[] small = new int[y+1];
		int[] big = new int[y+1];
		int[] num = new int[y+1];
		for(int i=1;i<=y;i++) {
			if(i==1) {
				small[i]=1;
				big[i]=1;
			}else {
				small[i] = small[i-1]+big[i-1];
				big[i] = big[i-1] +i/5;
				num[i] = small[i]+big[i];
			}
		}
		return num[y];
	}
	
	public static String measureDistance(List<Double> xList, List<Double> yList, double x, double y) {
		double[] a = new double[xList.size()]; 
		double[] b = new double[xList.size()]; 
		for(int i=0;i<xList.size()-1;i++) {
			if(xList.get(i)!=xList.get(i+1)) {
				a[i] = (yList.get(i)-yList.get(i+1))/(xList.get(i)-xList.get(i+1));
				b[i] = yList.get(i)-a[i]*xList.get(i);
				if(a[i]*x+b[i]-y<0) {
					return "false";
				}
			}
		}
		return "true";
    }
	
	public static double water(int n,int w,int[] a) {
		Arrays.sort(a);
		double x = 0;
		if(w/(3*n)<=a[1]&&w/(3*n)<=a[n]/2) {
			return w;
		}else if(a[n]<a[1]*2) {
				return (a[n]/2)*3*n;
			}
			else {
				return a[1]*3*n;
			}
		
	}


	public static void main(String[] args) {
		System.out.print(number(20));
		
		
		/*Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");
        int m = Integer.valueOf(line[0]);
        int k = Integer.valueOf(line[1]);;
        System.out.println(calculate(m, k));*/
		// TODO Auto-generated method stub
		/*Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        //(x,y)为小广所在的位置
        double x = Double.parseDouble(line.split(",")[0]);
        double y = Double.parseDouble(line.split(",")[1]);

        line = in.nextLine();
        //xList记录了多边形n个点的x坐标,yList记录了多边形n个点的y坐标
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        String[] array = line.split(",");
        for(int i = 0; i < array.length; i++) {
            xList.add(Double.parseDouble(array[i]));
            yList.add(Double.parseDouble(array[i+1]));
            i++;
        }
        in.close();
        System.out.println(measureDistance(xList, yList, x, y));*/
	}

}
