package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.entity.Admin;
import demo.respository.AdminRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminrepository;

    // POST method to save a new admin
    @PostMapping
    public ResponseEntity<String> createadmin(@RequestBody Admin admin) {

        // Save the admin to the database
        adminrepository.save(admin);

        return ResponseEntity.ok("admin created successfully");

    }

    // GET method to read a admin by id
    @GetMapping("/{id}")
    public Admin getadmin(@PathVariable Long id) {
        Optional<Admin> admin = adminrepository.findById(id);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            throw new RuntimeException("admin not found with id " + id);
        }
    }

    // GET method to fetch all admins
    @GetMapping
    public List<Admin> getAlladmins() {
        return adminrepository.findAll();
    }

    // PUT method to update an existing admin
    @PutMapping("/{id}")
    public Admin updateadmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        Optional<Admin> optionaladmin = adminrepository.findById(id);
        if (optionaladmin.isPresent()) {
            Admin existingadmin = optionaladmin.get();
            existingadmin.setName(adminDetails.getName());
            existingadmin.setEmail(adminDetails.getEmail());
            return adminrepository.save(existingadmin);
        } else {
            throw new RuntimeException("admin not found with id " + id);
        }
    }

    // DELETE method to delete a admin by id
    @DeleteMapping("/{id}")
    public String deleteadmin(@PathVariable Long id) {
        Optional<Admin> admin = adminrepository.findById(id);
        if (admin.isPresent()) {
            adminrepository.deleteById(id);
            return "admin with id " + id + " has been deleted";
        } else {
            throw new RuntimeException("admin not found with id " + id);
        }
    }
}