package org.assignment1.assignment1.specifications;

import java.util.ArrayList;
import java.util.List;

import org.assignment1.assignment1.dto.SearchEmployeeDynamicDTO;
import org.assignment1.assignment1.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeSpecifications {
    public static Specification<Employee> withCriteria(SearchEmployeeDynamicDTO criteria) {
        return (Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getEmpNo() != null) {
                predicates.add(criteriaBuilder.equal(root.get("empNo"), criteria.getEmpNo()));
            }
            if (criteria.getFirstName() != null && !criteria.getFirstName().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        "%" + criteria.getFirstName().toLowerCase() + "%"));
            }
            if (criteria.getLastName() != null && !criteria.getLastName().isBlank()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        "%" + criteria.getLastName().toLowerCase() + "%"));
            }
            if (criteria.getGender() != null && !criteria.getGender().isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), criteria.getGender()));
            }
            if (criteria.getHireDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("hireDate"), criteria.getHireDate()));
            }
            if (criteria.getBirthDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("birthDate"), criteria.getBirthDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
