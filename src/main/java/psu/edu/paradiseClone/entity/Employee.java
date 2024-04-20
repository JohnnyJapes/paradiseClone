package psu.edu.paradiseClone.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//Luke Hanrahan
//ETI 461

@Entity
@Table(name="employee_record")
public class Employee {
//names left as they are in the table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="empno")
	private int empno;
	
	@Column(name="first_name")
	@NotEmpty(message="Name Empty")
	private String firstName;

	@Column(name="last_name")
	@NotEmpty(message="Name Empty")
	private String lastName;
	
	@Column(name="designation")
	@NotEmpty(message="designation Empty")
	private String designation;
	
	@Column(name="hiredate")
	@NotEmpty(message="hire date Empty")
	private String hiredate;
	
	@Column(name="salary")
	@NotNull(message="salary Empty")
	private int salary;
	
	@Column(name="comm") //commission
	@NotNull(message="comm Empty")
	private Integer comm;
	
	@Column(name="deptno")
	@NotNull(message="deptNo Empty")
	private int deptno;
	
	//define constructor
	public Employee() {}


	


	public Employee(int empno, String first_name, String last_name, String designation, String hiredate, int salary,
			int comm, int deptno) {
		super();
		this.empno = empno;
		this.firstName = first_name;
		this.lastName = last_name;
		this.designation = designation;
		this.hiredate = hiredate;
		this.salary = salary;
		this.comm = comm;
		this.deptno = deptno;
	}





	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", first_name=" + firstName + ", last_name=" + lastName + ", designation="
				+ designation + ", hiredate=" + hiredate + ", salary=" + salary + ", comm=" + comm + ", deptno="
				+ deptno + "]";
	}





	public int getEmpno() {
		return empno;
	}





	public void setEmpno(int empno) {
		this.empno = empno;
	}





	public String getFirstName() {
		return firstName;
	}





	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}





	public String getLastName() {
		return lastName;
	}





	public void setLastName(String last_name) {
		this.lastName = last_name;
	}





	public String getDesignation() {
		return designation;
	}





	public void setDesignation(String designation) {
		this.designation = designation;
	}





	public String getHiredate() {
		return hiredate;
	}





	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}





	public int getSalary() {
		return salary;
	}





	public void setSalary(int salary) {
		this.salary = salary;
	}





	public Integer getComm() {
		return comm;
	}





	public void setComm(Integer comm) {
		this.comm = comm;
	}





	public int getDeptno() {
		return deptno;
	}





	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}





}

	