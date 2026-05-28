
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {      
    
    public static void main(String[] args) throws Exception {
        
            System.out.println("Welcome to Employee Management System!");
            Scanner sc = new Scanner(System.in);
            DBRepo dbRepo = new DBRepo();
            EmployeeService employeeService = new EmployeeService(dbRepo);
            EmployeeView employeeView = new EmployeeView(employeeService,sc);

            init(dbRepo);

            while (true) { 
            try{
                System.out.println("Enter the option of ut choice:");
                System.out.println("1.View All Employee");
                System.out.println("2. Filter and Update Employee Details");
                System.out.println("3. Display Employee Under Manager");
                System.out.println("0.Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        employeeView.viewEmployees();
                        break;
                    case 2:
                        employeeView.filterDUpdateEmployees();
                        break;
                    case 3:
                        employeeView.displayEmployeeUnderManager();
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Wrong Choice!!!");
                }
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
                sc.next();
            }
        }   
    }


    private static void init(DBRepo dbRepo) {
        Employee emp1 = new Employee(1,"Sriram",45,"Management","CEO",null);
        Employee emp2 = new Employee(2, "Mukund", 42, "HR","HR Manager", emp1);        
        Employee emp3 = new Employee(3, "Sebastian", 38 ,"Finance", "Finance Manager" ,emp1);
        Employee emp4 = new Employee(4, "Aashritha", 32 ,"Product Management", "Dev Manager" ,emp1);
        Employee emp5 = new Employee(5, "Mohammad Rafi", 35 ,"HR", "HR Lead" ,emp2);
        Employee emp6 = new Employee(6, "Anjali Kumar" ,29 ,"HR", "HR Associate" ,emp5);
        Employee emp7 = new Employee(7,"Joseph", 40 ,"Finance", "Finance Associate", emp3);
        Employee emp8 = new Employee(8, "Ramachandran", 27, "Product Development", "Team Lead",emp4);
        Employee emp9 = new Employee(9, "Abhinaya Shankar" ,23 ,"Product Development" ,"System Developer",emp8);
        Employee emp10 = new Employee(10, "Imran Khan" ,28 ,"Product Testing", "QA Lead", emp8);

        dbRepo.addEmployee(emp1);
        dbRepo.addEmployee(emp2);
        dbRepo.addEmployee(emp3);
        dbRepo.addEmployee(emp4);
        dbRepo.addEmployee(emp5);
        dbRepo.addEmployee(emp6);
        dbRepo.addEmployee(emp7);
        dbRepo.addEmployee(emp8);
        dbRepo.addEmployee(emp9);
        dbRepo.addEmployee(emp10);
    }
}
