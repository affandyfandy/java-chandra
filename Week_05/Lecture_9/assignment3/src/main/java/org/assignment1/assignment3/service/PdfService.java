package org.assignment1.assignment3.service;

import org.assignment1.assignment3.model.Employee;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;
    private final EmployeeService employeeService;

    public PdfService(TemplateEngine templateEngine, EmployeeService employeeService) {
        this.templateEngine = templateEngine;
        this.employeeService = employeeService;
    }

    public byte[] generatePdf(List<Employee> employees) throws Exception {
        Context context = new Context();
        context.setVariable("employees", employees);

        Optional<Employee> highestSalaryEmployee = employeeService.getHighestSalaryEmployee();
        Optional<Employee> lowestSalaryEmployee = employeeService.getLowestSalaryEmployee();
        long recordCount = employeeService.getRecordCount();
        OptionalDouble averageSalary = employeeService.getAverageSalary();
        LocalDateTime currentTime = LocalDateTime.now();

        context.setVariable("highestSalaryEmployee", highestSalaryEmployee.orElse(null));
        context.setVariable("lowestSalaryEmployee", lowestSalaryEmployee.orElse(null));
        context.setVariable("recordCount", recordCount);
        context.setVariable("averageSalary", averageSalary.isPresent() ? averageSalary.getAsDouble() : 0);
        context.setVariable("currentDate", currentTime);

        String html = templateEngine.process("pdf-employees", context);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
            return os.toByteArray();
        }
    }
}