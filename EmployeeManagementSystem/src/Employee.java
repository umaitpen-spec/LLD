public class Employee {
    private int employeeId;
    private String employeeName;
    private int age;
    private String Department;
    private String Designation;
    private Employee ReportingTo;
    

    public Employee(int employeeId, String employeeName, int age, 
        String Department, String Designation, Employee ReportingTo) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.age = age;
        this.Department = Department;
        this.Designation = Designation;
        this.ReportingTo = ReportingTo;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDepartment() {
        return Department;
    }
    public void setDepartment(String department) {
        Department = department;
    }
    public String getDesignation() {
        return Designation;
    }
    public void setDesignation(String designation) {
        Designation = designation;
    }
    public Employee getReportingTo() {
        return ReportingTo;
    }
    public void setReportingTo(Employee reportingTo) {
        ReportingTo = reportingTo;
    }
    @Override
    public String toString() {
        String mgr = (ReportingTo != null)?ReportingTo.getEmployeeName():"-" ;
        String str = String.format("%-10s %-20s %-10s %-20s %-20s %-20s",employeeId , employeeName ,age,Department ,Designation ,mgr);
        return str;
    }

    
    
        // return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", age=" + age
        //         + ", Department=" + Department + ", Designation=" + Designation 
        //         + ", ReportingTo=" + ((ReportingTo != null)?ReportingTo.getEmployeeName():"-" )+ "]";
}
