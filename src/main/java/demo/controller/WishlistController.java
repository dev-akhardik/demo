package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.entity.Product;
import demo.entity.Admin;
import demo.entity.WishlistEntity;
import demo.respository.ProductRepository;
import demo.respository.AdminRepository;
import demo.respository.WishlistRepository;

import java.util.List;
// import java.util.Optional;
// import java.util.Set;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addToWishlist(@RequestParam Long userId, @RequestParam Long productId) {
        Admin user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        WishlistEntity wishlist = new WishlistEntity(user, product);
        wishlistRepository.save(wishlist);
        return ResponseEntity.ok("Added to wishlist");
    }

    @GetMapping
    public List<WishlistEntity> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromWishlist(@PathVariable Long id) {
        wishlistRepository.deleteById(id);
        return ResponseEntity.ok("Wishlist item removed");
    }
}
