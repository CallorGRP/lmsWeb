package bit.model;

import java.util.Arrays;

public class EstimateCodeDto {
/*Estimate ���� ����� �ϳ� �ɶ����� (Unique�� ���� EstimateCodeDto�ϳ� / ���ڿ�, ���ڸ� ���� ���ִ� ������ ���� 100���� �����س��´�.*/
	private String[] stringQ=new String[100];
	private double[] doubleQ=new double[100];
	
	public String getStringQ(int idx) {
		if(idx>=0&&idx<100) {
			return stringQ[idx];
		}else {
			return null;
		}
	}
	public void setStringQ(int idx, String stringQ) {
		if(idx>=0&&idx<100) {
			this.stringQ[idx] = stringQ;
		}
	}
	public double getDoubleQ(int idx) {
		if(idx>=0&&idx<100) {
			return doubleQ[idx];
		}else {
			return -1;
		}
	}
	@Override
	public String toString() {
		return "EstimateCodeDto [stringQ=" + Arrays.toString(stringQ) + ", doubleQ=" + Arrays.toString(doubleQ) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(doubleQ);
		result = prime * result + Arrays.hashCode(stringQ);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstimateCodeDto other = (EstimateCodeDto) obj;
		if (!Arrays.equals(doubleQ, other.doubleQ))
			return false;
		if (!Arrays.equals(stringQ, other.stringQ))
			return false;
		return true;
	}
	public void setDoubleQ(int idx,double doubleQ) {
		if(idx>=0&&idx<100) {
			this.doubleQ[idx] = doubleQ;
		}
	}
}