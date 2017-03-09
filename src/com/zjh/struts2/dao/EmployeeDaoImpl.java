package com.zjh.struts2.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zjh.struts2.vo.Employee;

public class EmployeeDaoImpl {
	private static Map<Integer, Employee> emps = new LinkedHashMap<Integer, Employee>();
	static {
		emps.put(1001, new Employee(1001, "AA", "aa", "aa@qq.com"));
		emps.put(1002, new Employee(1002, "BB", "bb", "bb@qq.com"));
		emps.put(1003, new Employee(1003, "CC", "cc", "cc@qq.com"));
		emps.put(1004, new Employee(1004, "DD", "dd", "dd@qq.com"));
		emps.put(1005, new Employee(1005, "EE", "ee", "ee@qq.com"));
	}

	public void save(Employee emp) {
		long time = System.currentTimeMillis();
		emp.setEmployeeId((int) time);
		emps.put(emp.getEmployeeId(), emp);
	}

	public void delete(Integer employeeId) {
		emps.remove(employeeId);
	}

	public void update(Employee emp) {
		emps.put(emp.getEmployeeId(), emp);
	}

	public Employee get(Integer employeeId) {
		return emps.get(employeeId);
	}

	public List<Employee> getEmployees() {
		return new ArrayList(emps.values());
	}

}
