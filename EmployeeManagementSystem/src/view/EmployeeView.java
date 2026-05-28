package view;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import model.Employee;
import service.EmployeeService;

public class EmployeeView {

   EmployeeService employeeService;
   private List<Employee> filterList;
   Scanner sc;
    public EmployeeView(EmployeeService employeeService, Scanner sc) {
        this.sc = sc;
        this.employeeService = employeeService;
    }

    public void viewEmployees() {
        String bold = "\033[1m";
        String reset = "\033[0m";

         String str = String.format("%-10s %-20s %-10s %-20s %-20s %-20s","EmployeeId" , "EmployeeName" ,"Age","Department" 
         ,"Designation" ,"Manager");
        System.out.println(bold + str + reset);
        for(Employee employee: employeeService.getAllEmpList())
        {
            System.out.println(employee);
        }
    }

    public void filterDUpdateEmployees() {
        viewEmployees();
        filterList = employeeService.getAllEmpList();
        while(true)
        {
            try
            {
                System.out.println("Select the field u want to filter:");
                System.out.println("1.Employee Name");
                System.out.println("2.Age");
                System.out.println("3.Department");
                System.out.println("4.Designation");
                //System.out.println("5.ReportingTo");
                System.out.println("0.Exit");
                int choice = sc.nextInt();

                switch(choice) {
                    case 2:
                        filterInt(choice);
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                        filterString(choice);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Wrong Choice!!!");
                }
                if(!filterList.isEmpty())
                {
                    System.out.println("Do u want to continue. (y/n)");
                    String t = sc.next();
                    if(t.equals("n"))                    
                    {
                        updateEmployees();
                    }
                }
                else
                    return;
              
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Enter Only NUmbers");
            }
            catch(Exception ex)
            {
                System.out.println("Error Stmt:");
                ex.printStackTrace();
            }
            finally
            {
                
            }
        }
    }

    public void filterInt(int choice) {
        String str = null;
        switch (choice) {
          
            case 2:
                str = "age:";
                break;
            default:
                System.out.println("Wrong Choice!!");
        }
        System.out.print("Enter the "+str);
        int num = sc.nextInt();
        System.out.println("Enter the filter Options");
        System.out.println("1.Equal");
        System.out.println("2.Not Equal");
        System.out.println("3.Greater Than");
        System.out.println("4.Less Than");
        int filterChoice = sc.nextInt();
        
            switch (filterChoice) {
                case 1:
                    filterList = filterList.stream()
                            .filter(a->a.getAge() == num)
                            .collect(Collectors.toList());
                    break;
                case 2:
                    filterList = filterList.stream()
                            .filter(a->a.getAge() != num)
                            .collect(Collectors.toList());
                    break;
                case 3:
                    filterList = filterList.stream()
                            .filter(a->a.getAge() > num)
                            .collect(Collectors.toList());
                    break;
                case 4:
                    filterList = filterList.stream()
                            .filter(a->a.getAge() < num)
                            .collect(Collectors.toList());
                    break;
                default:
                    break;
            
        }
        
        displayFilterEmp();
    }

    public void displayFilterEmp()
    {
        if(!filterList.isEmpty())
        {
            String bold = "\033[1m";
            String reset = "\033[0m";

            String str = String.format("%-10s %-20s %-10s %-20s %-20s %-20s","EmployeeId" , "EmployeeName" ,"Age","Department" 
            ,"Designation" ,"Manager");
            System.out.println(bold + str + reset);
            for(Employee employee: filterList)
            {
                System.out.println(employee);
            }                                       
        }
        else
            System.out.println("No Datas Found");
      
    }

     public void filterString(int choice) {
        String field;
        if (choice == 1)                 
            field = "Name";                
        else if(choice == 3)
                field = "Department";
        else if(choice == 4)
                field = "Designation";
        else if(choice ==  5)
                field = "ReportingTo";
        else
        {
            System.out.println("Wrong Choice!!");
            return;
        }
        
        System.out.print("Enter the "+field+":");
        String  text = sc.next();
        System.out.println("Enter the filter Options");
        System.out.println("1.Equal");
        System.out.println("2.Not Equal");
        System.out.println("3.Contains");
        System.out.println("4.Not Contains");
        System.out.println("5.Starts With");
        System.out.println("6.Ends With");
        int filterChoice = sc.nextInt();
        
        switch (filterChoice) {
                case 1:
                    filterList = employeeService.getEqualList(filterList,field,text);                    
                    break;
                case 2:
                    filterList = employeeService.getNotEqualList(filterList,field,text); 
                   
                    break;
                case 3:
                    filterList =  employeeService.getContainsList(filterList,field,text); 
                    break;
                case 4:
                    filterList =  employeeService.getNotContainsList(filterList,field,text);                              
                    break;
                case 5:
                    filterList = employeeService.getchkStartsWithList(filterList,field,text);  
                    break;
                case 6:
                    filterList = employeeService.getchkEndsWithList(filterList,field,text);  
                    break;
                default:
                    break;
            
        }
        displayFilterEmp();
     }

    private void updateEmployees() {
        System.out.println("Enter the field u want to update");
        System.out.println("1.Employee Name");
        System.out.println("2.Age");
        System.out.println("3.Department");
        System.out.println("4.Designation");
        //System.out.println("5.ReportingTo");
        System.out.println("0.Exit");
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the name:");
                String name = sc.next();
                employeeService.updateName(filterList,name);
                break;
            case 2:
                System.out.print("Enter the Age:");
                int age = sc.nextInt();
                employeeService.updateAge(filterList,age);
                break;
            case 3:
                System.out.print("Enter the Department:");
                String dept = sc.next();
                employeeService.updateDept(filterList,dept);
                break;
            case 4:
                System.out.print("Enter the Designation:");
                String desg = sc.next();
                employeeService.updateDesg(filterList,desg);
                break;
            case 0:
                return;
            default:
                System.out.println("Wrong Choice!!!");
        }
        System.out.println("Updated Successfully!!!");
    }

 

    public void displayEmployeeUnderManager() {
        System.out.println("Select the Manager");
        Set<Employee> mgmrSet = new HashSet<>();
        for(Employee employee:employeeService.getAllEmpList())
            if(employee.getReportingTo() != null)
                mgmrSet.add(employee.getReportingTo());
        List<Employee> mgmrList = new ArrayList<>(mgmrSet);
        if(!mgmrList.isEmpty())
        {
            for(int i=0;i<mgmrList.size();i++)
                System.out.println(i+1+"."+mgmrList.get(i).getEmployeeName());
            System.out.println("Make the choice");
            int n = sc.nextInt();
            System.out.println("All Employees Under The Manager");
            List<Employee> empList = employeeService.getAllEmpList().stream()
                                    .filter(a->(a.getReportingTo() != null && 
                                    a.getReportingTo().getEmployeeId() == mgmrList.get(n-1).getEmployeeId()))
                                    .toList();
            for(Employee emp:empList)
                System.out.println(emp);
        }        
    }

}
