package org.assignment1.assignment1.repository;

import org.assignment1.assignment1.entity.Title;
import org.assignment1.assignment1.entity.TitleId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, TitleId> {
    List<Title> findByIdEmpNo(Integer empNo);

    List<Title> findById_EmpNo(Integer empNo);
}
