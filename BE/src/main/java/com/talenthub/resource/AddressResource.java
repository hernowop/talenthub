package com.talenthub.resource;

import com.talenthub.models.Address;
import com.talenthub.models.Customer;
import com.talenthub.validator.Result;
import io.quarkus.panache.common.Sort;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

    //a. Mendapatkan keseluruhan data
    @GET
    public Object getAddress (){
        Object address = Address.listAll(Sort.ascending("id"));
        return new Result ("Data Berhasil Didapatkan",address);
    }

    //b. Mendapatkan data entitas berdasarkan atribut tertentu
    @GET
    @Path("{id}")
    public Object getAddressById (@PathParam("id") Long id){
        Address address = Address.findById(id);
        if(address == null){
            return new Result("Data dengan id " + id + " tidak ditemukan",null);
        }else {
            return new Result("Data Berhasil Didapatkan", address);
        }
    }
    //c. Menambahkan data entitas baru.
    @POST
    @Transactional
    @Path("{idCustomer}/address")
    public Object addAddressToCustomer(@PathParam("idCustomer") Long idCustomer,@Valid Address address){
        Customer customer = Customer.findById(idCustomer);
        if(customer==null){
            return new Result("Data Customer dengan id " + idCustomer + " Tidak Ditemukan", null);
        }else {
            customer.addresses.add(address);
            address.persist();
            return new Result("Data Berhasil Ditambahkan", Customer.findById(idCustomer));
        }
    }

    //d. Mengubah data entitas yang ada.
    @PUT
    @Transactional
    @Path("{id}")
    public Object updateAddress(@PathParam("id") Long id,@Valid Address newAddress){
        try {
            Address oldAddress = Address.findById(id);
            oldAddress.addressLine = newAddress.addressLine;
            oldAddress.city = newAddress.city;
            oldAddress.postalCode = newAddress.postalCode;
            return new Result("Data berhasil Diubah",oldAddress);
        } catch (Exception e) {
            return new Result("Data tidak Ditemukan",null);
        }

    }
    //e. Menghapus data entitas yang ada.
    @DELETE
    @Transactional
    @Path("{id}")
    public Object deleteAddressById(@PathParam("id") Long id){
        Boolean address = Address.deleteById(id);
        if(address == true) {
            return new Result("Data Berhasil Dihapus", Address.listAll(Sort.ascending("id")));
        }else{
            return new Result("Data Tidak Ditemukan", null);
        }
    }

}
