package ca.tetervak.studentdataservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDataRepository extends JpaRepository<Student, Integer> {

}
