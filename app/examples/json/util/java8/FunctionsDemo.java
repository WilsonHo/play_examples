package examples.json.util.java8;

import examples.json.util.java8.models.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by wilson on 3/3/17.
 */
public class FunctionsDemo {

    public static void main(String[] args) {
        Function<Employee, String> funcEmpToString= (Employee e)-> {return e.getName();};
        List<Employee> employeeList=
                Arrays.asList(new Employee("Tom Jones", 45),
                        new Employee("Harry Major", 25),
                        new Employee("Ethan Hardy", 65),
                        new Employee("Nancy Smith", 15),
                        new Employee("Deborah Sprightly", 29));
        List<String> empNameList=convertEmpListToNamesList(employeeList, funcEmpToString);
        empNameList.forEach(System.out::println);

        List<Employee> empNameListInitials=applyIdentityToEmpList(employeeList, Function.identity());
        empNameListInitials.forEach(System.out::println);
    }

    public static List<String> convertEmpListToNamesList(List<Employee> employeeList, Function<Employee, String> funcEmpToString){
        List<String> empNameList=new ArrayList<String>();
        for(Employee emp:employeeList){
            empNameList.add(funcEmpToString.apply(emp));
        }
        return empNameList;
    }

    public static List<Employee> applyIdentityToEmpList(List<Employee> employeeList, Function<Employee, Employee> funcEmpToEmp){
        List<Employee> empNameList=new ArrayList<Employee>();
        for(Employee emp:employeeList){
            empNameList.add(funcEmpToEmp.apply(emp));
        }
        return empNameList;
    }
}
