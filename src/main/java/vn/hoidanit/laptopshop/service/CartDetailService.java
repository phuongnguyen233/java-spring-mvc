package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;
    public CartDetailService(CartDetailRepository cartDetailRepository){
        this.cartDetailRepository = cartDetailRepository;
    }
    public List<CartDetail> fetchCartDetails() {
        return this.cartDetailRepository.findAll();
    }
}
