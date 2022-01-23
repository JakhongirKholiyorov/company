package uz.pdp.springmodule2task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springmodule2task1.entity.Address;
import uz.pdp.springmodule2task1.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
