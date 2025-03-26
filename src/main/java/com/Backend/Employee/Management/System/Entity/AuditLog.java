//package com.Backend.Employee.Management.System.Entity;
//
//import com.Backend.Employee.Management.System.Controllor.Mycontrollor;
//import jakarta.persistence.*;
//import lombok.Builder;
//
//import java.util.Date;
//
//@Entity
//@Builder
//public class AuditLog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "employee_id", nullable = false)
//    private Employee employee;
//
//    private String action;
//    private String modifiedBy;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date timestamp;
//
//
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public String getAction() {
//        return action;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
//
//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//    public Date getTimestamp() {
//        return timestamp;
//    }
//    public void setTimestamp(Date timestamp) {
//        this.timestamp = timestamp;
//    }
//}
//
