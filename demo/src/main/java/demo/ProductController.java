package demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;



    // POST method to save a new user
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {

        // Save the user to the database
        productRepository.save(product);

        return ResponseEntity.ok("Product created successfully");

    }
    // GET method to read a user by id
    @GetMapping("/{id}")
    public Product geProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("product not found with id " + id);
        }
    }

    // PUT method to update an existing user
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setColor(productDetails.getColor());
            existingProduct.setCategory(productDetails.getCategory());
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    // DELETE method to delete a user by id
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return "Product with id " + id + " has been deleted";
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }
}
