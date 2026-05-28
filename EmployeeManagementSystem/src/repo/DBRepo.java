package repo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Employee;

public class DBRepo {
    private Map<Integer,Employee> empList = new HashMap<>();

    public Employee getEmployeeById(int empId)
    {
        return empList.get(empId);
    }

    public List<Employee> getAllEmpList()
    {
        return (new ArrayList<>(empList.values()));
    }

    public void addEmployee(Employee employee)
    {
        empList.put(employee.getEmployeeId(), employee);
    }
}
