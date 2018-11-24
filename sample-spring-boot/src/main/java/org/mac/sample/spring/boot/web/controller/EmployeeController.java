/*
 *      (             |"|           !!!       #   ___                             o
 *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
 *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 *
 *                    !!!         |                             |"|            _             o          _     _
 *    __MMM__      `  _ _  '      |.===.         ,,,,,         _|_|_         _|_|_        ` /_\ '     o' \,=./ `o
 *     (o o)      -  (OXO)  -     {}o o{}       /(o o)\        (o o)         (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 * 虽不能至,心向往之。(Although it is not possible, my heart is longing for it.)
 *
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 */

package org.mac.sample.spring.boot.web.controller;

import org.mac.sample.spring.boot.web.model.entity.Department;
import org.mac.sample.spring.boot.web.model.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 员工管理
 *
 * @auther mac
 * @date 2018-11-22
 */
@Controller
public class EmployeeController {

    private static Map<Integer, Employee> employees = new HashMap<>();
    static {
        employees.put(1001, new Employee(1001, "Jerry", "Jerry@163.com", 1, new Department(101, "D-AA")));
        employees.put(1002, new Employee(1002, "Tom", "Tom@163.com", 1, new Department(102, "D-BB")));
        employees.put(1003, new Employee(1003, "Alex", "Alex@163.com", 0, new Department(103, "D-CC")));
        employees.put(1004, new Employee(1004, "Jack", "Jack@163.com", 0, new Department(104, "D-DD")));
        employees.put(1005, new Employee(1005, "Spike", "Spike@163.com", 1, new Department(105, "D-EE")));
    }
    private static AtomicInteger autoIncrementId = new AtomicInteger(1006);

    private static Map<Integer, Department> departments = new HashMap<>();
    static{
        departments.put(101, new Department(101, "D-AA"));
        departments.put(102, new Department(102, "D-BB"));
        departments.put(103, new Department(103, "D-CC"));
        departments.put(104, new Department(104, "D-DD"));
        departments.put(105, new Department(105, "D-EE"));
    }

    @GetMapping("/employees")
    public String list(Model model) {
        model.addAttribute("employees",employees.values());
        return "employee/list";
    }

    @GetMapping("/employee")
    public String toAdd(Model model) {
        model.addAttribute("departments",departments.values());
        return "employee/edit";
    }

    @PostMapping("/employee")
    public String add(Employee employee) {

        Integer employeeId = autoIncrementId.getAndIncrement();
        employee.setId(employeeId);
        employee.setDepartment(departments.get(employee.getDepartment().getId()));

        employees.put(employeeId,employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model) {

        Employee employee = employees.get(id);
        model.addAttribute("employee",employee);

        model.addAttribute("departments",departments.values());

        return "employee/edit";
    }


    @PutMapping("/employee")
    public String edit(Employee employee) {

        employee.setDepartment(departments.get(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);

        return "redirect:/employees";
    }

    @DeleteMapping ("/employee/{id}")
    public String remove(@PathVariable("id") Integer id) {

        employees.remove(id);
        return "redirect:/employees";
    }
}
