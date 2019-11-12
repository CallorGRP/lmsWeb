package bit.test;

import java.util.ArrayList;

import bit.model.HumanDao;
import bit.model.SubboardDao;
import bit.model.SubboardDto;

public class Test {

	public static void main(String[] args) {
		HumanDao hd= new HumanDao();
		for(int i=0;i<30;i++) {
			hd.addStudent("순성쨩", "DDD99","19930704",1,"서울특", "110"+i, "cho"+i+"@babo.kik");
		}
	}

}
