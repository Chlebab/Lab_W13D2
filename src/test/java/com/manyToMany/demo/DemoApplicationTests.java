package com.manyToMany.demo;

import com.manyToMany.demo.models.Department;
import com.manyToMany.demo.models.Employee;
import com.manyToMany.demo.repositories.DepartmentRepository;
import com.manyToMany.demo.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}
	@Test
	public void createEmployeeAndDepartment(){
		Department department = new Department("Laser Testing");
		departmentRepository.save(department);

		Employee employee = new Employee("Capitan", "Lazer", "whatevernumber", department);
		employeeRepository.save(employee);
	}
	@Test
	public void departmentCanAddEmployees(){
		Department department1 = new Department("Laser Testing V1");
		Employee employee1 = new Employee("Capitan1", "Lazer1", "whatevernumber", null);
		department1.addEmployee(employee1);
		departmentRepository.save(department1);
		employeeRepository.save(employee1);
	}
	@Test
	public void departmentHasEmployees(){
		Department department1 = new Department("Laser Testing V1");
		Employee employee1 = new Employee("Capitan1", "Lazer1", "whatevernumber", null);
		department1.addEmployee(employee1);
		Long expected = 3L;
		assertEquals(1, department1.getEmployees().size());
		assertEquals(1, departmentRepository.findById(expected).get().getEmployees().size());
	}

}
