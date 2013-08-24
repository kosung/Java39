package bit.java39.vo;

import java.util.Date;

public class Employee {
	protected int 		no;
	protected String 	name;
	protected String 	job;
	protected int 		managerNo;
	protected String	managerName;
	protected Date 		hireDate;
	protected int		salary;
	protected int		commission;
	protected int		departmentNo;
	protected String	departmentName;
	protected String	departmentLocation;
	
	public int getNo() {
		return no;
	}
	public Employee setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Employee setName(String name) {
		this.name = name;
		return this;
	}
	public String getJob() {
		return job;
	}
	public Employee setJob(String job) {
		this.job = job;
		return this;
	}
	public int getManagerNo() {
		return managerNo;
	}
	public Employee setManagerNo(int managerNo) {
		this.managerNo = managerNo;
		return this;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public Employee setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		return this;
	}
	public int getSalary() {
		return salary;
	}
	public Employee setSalary(int salary) {
		this.salary = salary;
		return this;
	}
	public int getCommission() {
		return commission;
	}
	public Employee setCommission(int commission) {
		this.commission = commission;
		return this;
	}
	public int getDepartmentNo() {
		return departmentNo;
	}
	public Employee setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
		return this;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public Employee setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
		return this;
	}
	public String getDepartmentLocation() {
		return departmentLocation;
	}
	public Employee setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
		return this;
	}
	public String getManagerName() {
		return managerName;
	}
	public Employee setManagerName(String managerName) {
		this.managerName = managerName;
		return this;
	}
	
	
}
