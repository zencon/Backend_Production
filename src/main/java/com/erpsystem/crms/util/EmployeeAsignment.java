/*package com.erpsystem.crms.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeAsignment {
	
	public static void main(String args[]) {
		
		EmployeeEntity e1 = new EmployeeEntity(1,"Amruta",75000);
				
		EmployeeEntity e2 = new EmployeeEntity(2,"Lalit",57000);
		
		EmployeeEntity e3 = new EmployeeEntity(3,"Rakhi",25000);
		
		EmployeeEntity e4 = new EmployeeEntity(4,"Harshal",10000);
		
		EmployeeEntity e5 = new EmployeeEntity(5,"Avani",50000);
		
		List<EmployeeEntity> al=new ArrayList<EmployeeEntity>();
		
		al.add(e1);
		al.add(e2);
		al.add(e3);
		al.add(e4);
		al.add(e5);
		
		Iterator<EmployeeEntity> itr=al.iterator();
		
		while(itr.hasNext()){  
			
			EmployeeEntity et=(EmployeeEntity)itr.next();  
			if(et.getEmpsal() > 50000) {
		  // System.out.println(et.getEmpid()+"  "+et.getEmpname()+"  "+et.getEmpsal());
		     
			   System.out.println("Salary greater than 50000 "+et.getEmpname()+"  "+et.getEmpsal());
		   }
			else {
				//System.out.println("salary is less than 50000 "+et.getEmpname()+"  "+et.getEmpsal());
			}
		  }  
		
	}

}
*/