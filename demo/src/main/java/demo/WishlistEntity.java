package demo;

import jakarta.persistence.*;

@Entity
public class WishlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public WishlistEntity() {}

    public WishlistEntity(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

}
