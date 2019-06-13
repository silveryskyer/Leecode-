package leetcodetest;

import java.io.Console;


public class Operation {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(divide(10, 3));
	}
	
	
	//����
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
     * ����˼·�������ǳ������������ռ��³�������
     * �̣���ʽ�ǣ�(������-����)�³���=�̣��������������³���=��...��������һ����ѧ���
     * ��һ��������ʽ����������������������̵Ĺ�ϵΪ��(������-����)�³���=�̣��������������³���=��...������
     * �����Ƶ��ó����̡�����+����=��������
     *
     * Ҫ���̣����������뵽���Ǽ������ܱ������ٴΣ���ô�̾�Ϊ���٣��������Լ�����Ч��̫��
     *
     * ��ô���ǿ�����λ�Ʒ�����Ϊ���������λ��ʱЧ���ر�ߣ�������1�൱�ڳ���2������λ��1�൱�ڳ���2
     *
     * ���ǿ��԰�һ��dividend�����������ȳ���2^n��n���Ϊ31�����ϼ�Сnȥ��̽,��ĳ��n����dividend/2^n>=divisorʱ��
     *
     * ��ʾ�����ҵ���һ���㹻������������*divisor�ǲ�����dividend�ģ��������ǾͿ��Լ�ȥ2^n��divisor���Դ�����
     *
     * ���ǿ�����100/3Ϊ��
     *
     * 2^n��1��2��4��8...2^31����������nΪ31ʱ��������ر��100/2^n��һ����С�������϶���С��3�ģ�����ѭ��������
     *
     * ��n=5ʱ��100/32=3, �պ��Ǵ��ڵ���3�ģ���ʱ���ǽ�100-32*3=4��Ҳ���Ǽ�ȥ��32��3�������������ٴ���4��ͬ���ַ������ټ�ȥһ��3
     *
     * ����һ���Ǽ�ȥ��33��3�������̾���33
     *
     * �����еô���һЩ�������������divisor�ǲ���Ϊ0�ģ�Integer.MIN_VALUE��Integer.MAX_VALUE
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
