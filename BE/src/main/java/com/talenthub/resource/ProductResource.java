package com.talenthub.resource;

import com.talenthub.models.Product;
import com.talenthub.validator.Result;
import io.quarkus.panache.common.Sort;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	//a. Mendapatkan keseluruhan data
	@GET
	public Object getProduct(){
		Object product = Product.listAll(Sort.ascending("id"));
		return new Result("Data Berhasil Didapatkan",product);
	}

	//b. Mendapatkan data entitas berdasarkan atribut tertentu
	@GET
	@Path("{id}")
	public Object getProductById(@PathParam("id") Long id) {
		Product product = Product.findById(id);
		if(product == null){
			return new Result("Data dengan id " + id + " tidak ditemukan",null);
		}else {
			return new Result("Data Berhasil Didapatkan", product);
		}
	}
	
	//c. Menambahkan data entitas baru.
	@POST
	@Transactional
	public Object addProduct(@Valid Product product) {
		product.persist();
		return new Result ("Data Berhasil Ditambahkan",Product.listAll(Sort.ascending("id")));
	}
	
	//d. Mengubah data entitas yang ada.
	@PUT
	@Transactional
	@Path("{id}")
	public Object editProductById(@PathParam("id") Long id,@Valid Product newProduct) {
		try {
			Product oldProduct = Product.findById(id);
			oldProduct.productName = newProduct.productName;
			oldProduct.productDesc = newProduct.productDesc;
			oldProduct.image = newProduct.image;
			oldProduct.price = newProduct.price;
			oldProduct.stock = newProduct.stock;
			return new Result("Data Berhasil Diubah",Product.findById(id));
		} catch (Exception e) {
			return new Result("Data tidak Ditemukan",null);
		}
	}
	
	//e. Menghapus data entitas yang ada.
	@DELETE
	@Path("{id}")
	@Transactional
	public Object deleteProductById(Long id) {
		Boolean product = Product.deleteById(id);
		if(product == true) {
			return new Result("Data Berhasil Dihapus", Product.listAll(Sort.ascending("id")));
		}else{
			return new Result("Data Tidak Ditemukan", null);
		}
	}
}

