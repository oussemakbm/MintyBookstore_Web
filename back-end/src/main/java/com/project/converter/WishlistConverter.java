package com.project.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.DTOs.WishlistDTO;
import com.project.entities.Wishlist;
@Component
public class WishlistConverter {
ModelMapper modelMapper = new ModelMapper();
	
	public WishlistDTO entityToDTO(Wishlist wishlist){		
		WishlistDTO w = modelMapper.map(wishlist, WishlistDTO.class);
		return w;
	}
	
	
	public List<WishlistDTO> entitiesToDTOs(List<Wishlist> wishlists){
		List<WishlistDTO> list=new ArrayList<WishlistDTO>();
		for (Wishlist w : wishlists) {
			list.add(entityToDTO(w));
		}
		return list;
	}
}
