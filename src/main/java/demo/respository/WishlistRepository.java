package demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.WishlistEntity;

public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {
}
