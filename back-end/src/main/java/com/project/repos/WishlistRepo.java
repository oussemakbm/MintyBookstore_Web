package com.project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Wishlist;

@Repository
public interface WishlistRepo extends CrudRepository<Wishlist, Long>{

}
