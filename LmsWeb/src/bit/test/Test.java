package bit.test;

import java.util.ArrayList;

import bit.model.HumanDao;
import bit.model.SubboardDao;
import bit.model.SubboardDto;

public class Test {

	public static void main(String[] args) {
		HumanDao hd= new HumanDao();
		for(int i=0;i<30;i++) {
			hd.addStudent("����»", "DDD99","19930704",1,"����Ư", "110"+i, "cho"+i+"@babo.kik");
		}
	}

}
