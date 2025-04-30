package demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}