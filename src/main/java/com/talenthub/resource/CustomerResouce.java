package com.talenthub.resource;

import com.talenthub.models.Customer;
import com.talenthub.models.Product;
import com.talenthub.validator.Result;
import io.quarkus.panache.common.Sort;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResouce {

    //a. Mendapatkan keseluruhan data
    @GET
    public Object getCustomer(){
        Object customer = Customer.listAll(Sort.ascending("id"));
        return new Result("Data Berhasil Didapatkan",customer);
    }

    //b. Mendapatkan data entitas berdasarkan atribut tertentu
    @GET
    @Path("{id}")
    public Object getCustomerById(@PathParam("id") Long id){
        Customer customer = Customer.findById(id);
        if(customer == null){
            return new Result("Data dengan id " + id + " tidak ditemukan",null);
        }else {
            return new Result("Data Berhasil Didapatkan", customer);
        }
    }

    //c. Menambahkan data entitas baru baik di entitas Customer, Address maupun Product
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object addCustomer(@Valid Customer customer){
        customer.persist();
        return new Result("Data Berhasil Ditambahkan",Customer.listAll(Sort.ascending("id")));
    }

    //d. Mengubah data entitas yang ada.
    @PUT
    @Transactional
    @Path("{id}")
    public Object updateCustomer(@PathParam("id") Long id,@Valid Customer newCustomer){
        try {
            Customer oldCustomer = Customer.findById(id);
            oldCustomer.email = newCustomer.email;
            oldCustomer.firstName = newCustomer.firstName;
            oldCustomer.lastName = newCustomer.lastName;
            oldCustomer.password = newCustomer.password;
            oldCustomer.phoneNumber = newCustomer.phoneNumber;
            return new Result("Data Berhasil Diubah",Customer.findById(id));
        } catch (Exception e) {
            return new Result("Data tidak Ditemukan",null);
        }
    }

    //e. Menghapus data entitas yang ada.
    @DELETE
    @Transactional
    @Path("{id}")
    public Object deleteCustomerById(@PathParam("id") Long id){
        Boolean customer = Customer.deleteById(id);
        if(customer == true) {
            return new Result("Data Berhasil Dihapus", Customer.listAll(Sort.ascending("id")));
        }else{
            return new Result("Data Tidak Ditemukan", null);
        }
    }

    //Fungsi Post Untuk Table Hasil Join Many To Many Entity Custumer dan Product
    @POST
    @Transactional
    @Path("{customer_id}/products/{product_id}")
    public Object carts(@PathParam("customer_id") Long idCostumer,@PathParam("product_id") Long idProduct){
        Customer customer = Customer.findById(idCostumer);
        Product product = Product.findById(idProduct);

        if(customer==null || product ==null) {
            if(customer==null && product==null){
                return new Result("ID Customer dan ID Product tidak ditemukan",null);
            } else if (product==null) {
                return new Result("ID product tidak ditemukan",null);
            }else {
                return new Result("ID Customer tidak ditemukan", null);
            }
        }else {
            customer.products.add(product);
            customer.persist();
            return new Result("Data Berhasil Dipublish", Customer.findById(idCostumer));
        }
    }



}
