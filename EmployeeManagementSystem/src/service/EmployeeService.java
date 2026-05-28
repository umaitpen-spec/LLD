package service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.Employee;
import repo.DBRepo;

public class EmployeeService {

    DBRepo dbRepo;
    private final Map<String, Function<Employee, Object>> getters = Map.of(
        "Name", Employee::getEmployeeName,
        "Department", Employee::getDepartment,
        "Designation", Employee::getDesignation,
        "ReportingTo", Employee::getReportingTo);
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

    
    public boolean chkEqual(String str1,String str2)
    {
        if(str1.length() != str2.length())
            return false;
        for(int i=0;i<str1.length();i++)
            if(str1.charAt(i) != str2.charAt(i))
                return false;
        return true;
    }

    public boolean chkContains(String str1,String key)
    {
        for(int i=0;i<str1.length();i++)
            if(str1.charAt(i) == key.charAt(i) && str1.substring(i,i+key.length()).equals(key))
                return true;
        return false;
    }

    public boolean chkStartsWith(String str1,String key)
    {
        str1 = str1.toLowerCase();
        key = key.toLowerCase();
        return str1.charAt(0) == key.charAt(0) && str1.substring(0,key.length()).equals(key);
    }

    public boolean chkEndsWith(String str1,String key)
    {
        int n = key.length();
        int m = str1.length();
        str1 = str1.toLowerCase();
        key = key.toLowerCase();
        return str1.charAt(m-n) == key.charAt(0) && str1.substring(m-n,m-n+key.length()).equals(key);
    }

    public List<Employee> getEqualList(List<Employee> filterList, String field, String text) {
        return filterList.stream()
                            .filter(emp -> chkEqual((String)getters.get(field).apply(emp),text))
                            .collect(Collectors.toList());
    }

    public List<Employee> getNotEqualList(List<Employee> filterList, String field, String text) {
        return  filterList.stream()
                            .filter(emp -> !chkEqual((String)getters.get(field).apply(emp),text))
                            .collect(Collectors.toList());
    }

    public List<Employee> getContainsList(List<Employee> filterList, String field, String text) {
        return  filterList.stream()
                            .filter(emp -> chkContains((String)getters.get(field).apply(emp),text))
                            .collect(Collectors.toList());
    }

    public List<Employee> getNotContainsList(List<Employee> filterList, String field, String text) {
        return filterList.stream()
                    .filter(emp -> !chkContains((String)getters.get(field).apply(emp),text))
                    .toList();
    }

    public List<Employee> getchkStartsWithList(List<Employee> filterList, String field, String text) {
        return filterList.stream()
                        .filter(emp -> chkStartsWith((String)getters.get(field).apply(emp),(text)))
                        .toList();
    }

    public List<Employee> getchkEndsWithList(List<Employee> filterList, String field, String text) {
        return filterList.stream()
                        .filter(emp -> chkEndsWith(((String)getters.get(field).apply(emp)),text))
                        .toList();
    }

    public void updateName(Employee emp,String name) {
        emp.setEmployeeName(name);
}

    public void updateAge(Employee emp,int age) {
            emp.setAge(age);
    }

    public void updateDept(Employee emp,String dept) {
            emp.setDepartment(dept);
    }

    public void updateDesg(Employee emp,String desg) {
            emp.setDesignation(desg);
    }


}
