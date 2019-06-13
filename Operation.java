package leetcodetest;

import java.io.Console;


public class Operation {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(divide(10, 3));
	}
	
	
	//迭代
    public double myPow(double x, int n) {
    	double out = 1;
    	if(n==1) return x;
    	if(n==0) return 1;
    	if(x==0) return 0;
    	int subNum = Math.abs(n/2);
    	double subPow = myPow(x, subNum);
    	if(n%2==0) {
    		out = subPow*subPow;
    	}else {
			out = subPow*subPow*x;
		}
    	if(n>0) {
    		return out;
    	}else {
    		return 1/out;
    	}
    }
	
    public String multiply(String num1, String num2) {
    	int n1 = num1.length()-1;
    	int n2 = num2.length()-1;
    	if(n1<0||n2<0) return "";
    	int[] mul = new int[n1+n2+2];
        for (int i = n1; i >=0; --i) {
			for (int j = n2; j >=0; --j) {
				int bitmul = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
				bitmul += mul[i+j+1];
				
				mul[i+j] += bitmul/10;
				mul[i+j+1] = bitmul%10;
			}
		}
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i<mul.length-1 && mul[i] == 0) {
			i++;
		}
        for (; i < mul.length; i++) {
			sb.append(mul[i]);
		}
        return sb.toString();
    }
	 /**
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     *
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     *
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     *
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     *
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     *
     * 我们可以以100/3为例
     *
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     *
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     *
     * 所以一共是减去了33个3，所以商就是33
     *
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     *
     */
    public static int divide(int dividend, int divisor) {
    	if(dividend==0) {
    		return 0;
    	}
    	if(dividend == Integer.MIN_VALUE && divisor ==-1) {
    		return Integer.MAX_VALUE;
    	}
    	boolean negative;
    	negative = (dividend ^ divisor) <0;
    	long t = Math.abs((long) dividend);
    	long d = Math.abs((long) divisor);
    	int result = 0;
    	for (int i = 31; i >=0 ; i--) {
			if((t>>i)>=d) {
				result += 1<<i;
				t-=d<<i;
			}
		}
    	return negative ? -result : result; 
    }
}
