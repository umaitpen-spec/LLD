import java.util.List;

public class EmployeeService {

    DBRepo dbRepo;
    public EmployeeService(DBRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    public Employee getEmployeeById(int empId)
    {
        return dbRepo.getEmployeeById(empId);
    }

     public List<Employee> getAllEmpList()
    {
        return dbRepo.getAllEmpList();
    }

     

}
