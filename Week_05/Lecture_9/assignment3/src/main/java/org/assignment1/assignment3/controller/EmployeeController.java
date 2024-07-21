package org.assignment1.assignment3.controller;

import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.assignment1.assignment3.model.Employee;
import org.assignment1.assignment3.service.EmployeeService;
import org.assignment1.assignment3.service.PdfService;
import org.assignment1.assignment3.utils.CSVUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final PdfService pdfService;

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employee-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") String id,
            Model theModel) {

        // get the employee from the service
        Employee theEmployee = employeeService.findById(id);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId") String id) {

        // delete the employee
        employeeService.deleteById(id);

        // redirect to /employees/list
        return "redirect:/employees/list";

    }

    @PostMapping("/saveAll")
    public String uploadCsv(@RequestParam("file") MultipartFile file, Model model) {
        if (CSVUtils.hasCSVFormat(file)) {
            try {
                List<Employee> employees = CSVUtils.csvToEmployees(file.getInputStream());
                employeeService.saveAll(employees);
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
                // Handle the exception and add an error message to the model if needed
                model.addAttribute("message", "An error occurred while processing the CSV file: " + e.getMessage());
                return "error";
            }
        } else {
            model.addAttribute("message", "Please upload a valid CSV file.");
            return "error";
        }
        // Redirect to the employee list page
        return "redirect:/employees/list";
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<Employee> employee = employeeService.findAll();
        byte[] pdfContents = null;

        try {
            pdfContents = pdfService.generatePdf(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "employees.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfContents);
    }

}
