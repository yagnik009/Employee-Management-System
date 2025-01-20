package com.Backend.Employee.Management.System.Service;

import com.Backend.Employee.Management.System.Entity.Employee;
import com.Backend.Employee.Management.System.Entity.EmployeeDto;
import com.Backend.Employee.Management.System.Entity.Pagedata;
import com.Backend.Employee.Management.System.Entity.PagedataDto;
import com.Backend.Employee.Management.System.Repositry.repositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.*;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    repositry repositry;


    public List<Employee> save(List<Employee> e) {
        return repositry.saveAll(e);
    }

    public PagedataDto getemployee(Pagedata pagedata) {
        List<Employee> alldata=repositry.findAll();
        List<Employee> returndata=new ArrayList<>();
        int start=pagedata.getPage()* pagedata.getPagesize()- pagedata.getPagesize();
        int end=pagedata.getPage()* pagedata.getPagesize();
        int totalpage=alldata.size()/pagedata.getPagesize();
        for (int i=start;i<end && i<alldata.size();i++){
            returndata.add(alldata.get(i));
        }
        PagedataDto page=new PagedataDto();
        page.setPage(pagedata.getPage());
        page.setPagesize(pagedata.getPagesize());
        page.setTotalEmployees(alldata.size());
        page.setEmployees(returndata);
        page.setTotalpage(totalpage);
        return page;
    }

    public Optional<Employee> getByid(Long id) {
        return repositry.findById(id);
    }

    public EmployeeDto update(Long id,EmployeeDto update) {
        Optional<Employee> optionalEmployee=repositry.findById(id);

        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee with ID" + id + " not found");
        }
        Employee employee=optionalEmployee.get();
        employee.setFirstname(update.getFirstname());
        employee.setLastname(update.getLastname());
        employee.setDepartment(update.getDepartment());
        employee.setEmail(update.getEmail());
        employee.setSalary(update.getSalary());

        Employee updatedEmployee = repositry.save(employee);

        EmployeeDto result = new EmployeeDto();
        result.setId(updatedEmployee.getId());
        result.setFirstname(updatedEmployee.getFirstname());
        result.setLastname(updatedEmployee.getLastname());
        result.setDepartment(updatedEmployee.getDepartment());
        result.setEmail(updatedEmployee.getEmail());
        result.setSalary(updatedEmployee.getSalary());
        result.setCreatedate(updatedEmployee.getDate());
        result.setUpdatdate(updatedEmployee.getUpdatdate());
        return result;
    }


    public void delete(Long id) {
        Optional<Employee> optionalEmployee = repositry.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee with ID " + id + " not found.");
        }
        repositry.delete(optionalEmployee.get());
    }

    public Map<String, Integer> getEmployeeCountByDepartment() {
        List<Employee> ll=repositry.findAll();
        HashMap<String,Integer> hs=new HashMap<>();
        int Marketing=0,IT=0,HR=0,Operations=0,Finance=0;
        for (Employee e:ll){
            if (e.getDepartment().equals("Marketing")){
              Marketing++;
            }if (e.getDepartment().equals("IT")){
                IT++;
            }if (e.getDepartment().equals("HR")){
                HR++;
            }if (e.getDepartment().equals("Operations")){
                Operations++;
            }if (e.getDepartment().equals("Finance")){
                Finance++;
            }
        }
        hs.put("Marketing",Marketing);
        hs.put("IT",IT);
        hs.put("HR",HR);
        hs.put("Operations",Operations);
        hs.put("Finance",Finance);
        System.out.println(hs);

        return hs;

    }

    public EmployeeDto getsalary() {
        List<Employee> ll=repositry.findAll();
        int size=ll.size();
        double minsalary=Integer.MAX_VALUE;
        double maxsalary=Integer.MIN_VALUE;
        double avgsalary=0;
        for (Employee e:ll){
            if (e.getSalary()<minsalary){
                minsalary=e.getSalary();
            }
            if (e.getSalary()>maxsalary){
                maxsalary=e.getSalary();
            }
            avgsalary+=e.getSalary();
        }
        EmployeeDto e=new EmployeeDto();
        e.setTotalEmployees(size);
        e.setMinSalary(minsalary);
        e.setMaxSalary(maxsalary);
        e.setAverageSalary(avgsalary);

        return e;
    }
}
