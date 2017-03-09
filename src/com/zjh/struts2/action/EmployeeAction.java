package com.zjh.struts2.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.zjh.struts2.dao.EmployeeDaoImpl;
import com.zjh.struts2.vo.Employee;

/*
 * 1. 实现ModelDriven接口、接口方法getModel()，用于向ValueStack压入成员变量employee，便于添加和修改操作的传参
 * 2. 实现Preparable接口、接口方法prepare()，用于为getModel()方法提供Model对象。(详细见note.txt里的说明)
 * 3. struts.xml使用修改后的paramsPrepareparamsStack作为默认拦截器栈
 * (修改了prepare拦截器的alwaysInvokePrepare属性为false，使之不调用prepare方法)，
 * 并为 “需要栈顶对象为employee对象的Action方法” 提供 prepareXxx()方法，以便采取不同的方式为ModelDriven生成Model对象
 *  
 */
public class EmployeeAction implements RequestAware, ModelDriven<Employee>,
		Preparable {
	private EmployeeDaoImpl dao = new EmployeeDaoImpl();
	private Map<String, Object> requestMap;
	private Employee employee;
	// employeeId属性用于修改和删除操作时在ValueStack栈顶接受传参
	private Integer employeeId;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String save() {
		dao.save(employee);
		return "success";
	}

	public void prepareSave() {
		this.employee = new Employee();
	}

	// 不需要ValueStack的employee对象用于传参，所以不需要prepareDelete()方法，
	public String delete() {
		dao.delete(employeeId);
		return "success";
	}

	public String edit() {
		return "edit";
	}

	// employee需要从后台加载，用于回显与表格中
	public void prepareEdit() {
		this.employee = dao.get(employeeId);
	}

	public String update() {
		dao.update(employee);
		return "success";
	}

	public void prepareUpdate() {
		this.employee = new Employee();
	}

	public String list() {
		requestMap.put("emps", dao.getEmployees());
		return "list";
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		requestMap = arg0;
	}

	@Override
	public Employee getModel() {
		return employee;
	}

	@Override
	public void prepare() throws Exception {

	}

}
