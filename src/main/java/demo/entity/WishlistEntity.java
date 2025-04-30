package demo.entity;

import jakarta.persistence.*;

@Entity
public class WishlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Admin user;

    @ManyToOne
    private Product product;

    public WishlistEntity() {
    }

    public WishlistEntity(Admin user, Product product) {
        this.user = user;
        this.product = product;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public Admin getUser() {
        return user;
    }

    public void setUser(Admin user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
