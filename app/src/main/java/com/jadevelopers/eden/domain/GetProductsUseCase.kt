package com.jadevelopers.eden.domain

import com.jadevelopers.eden.model.Product

class GetProductsUseCase {
    private val repository = ProductRepository()

    operator fun invoke(): List<ArrayList<Product>>{
        return repository.ge
    }
}