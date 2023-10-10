## TUGAS TALENTHUB
# REST API APLIKASI BELANJA ONLINE


![alt text](https://i.ibb.co/VvB9LxK/Business-Logic.jpg) <br>
Gambar di atas adalah visualisasi terkait alur kerja dari aplikasi belanja online<br><br>

![alt text](https://i.ibb.co/VJ6TyVw/Databse-Diagram.jpg)<br>
Diagram di atas adalah visualisasi dari hubungan relasi antar database di di mana ada 3 entitas yang kami buat yaitu :
1. Customer
2. Product
3. Address

Untuk pola hubungan antar entitas bisa dilihat pada diagram di atas
<br><br>
## DEPENDENSI YANG DIGUNAKAN
1. quarkus-resteasy-reactive-jackson
2. quarkus-hibernate-validator
3. quarkus-hibernate-orm-panache
4. quarkus-jdbc-postgresql
5. quarkus-smallrye-openapi<br><br>

## FORMAT RESPONSE ENDPOINT
### CUSTOMER RESOURCE
#### -POST/customer
```json
{
  "email": "7",
  "firstName": "<",
  "lastName": "string",
  "password": "%",
  "phoneNumber": "N",
}
```
#### -PUT/customer/id
```json
{
  "email": "a",
  "firstName": "}",
  "lastName": "string",
  "password": "A",
  "phoneNumber": "f",
}
```
<br>

### ADDRESS RESOURCE
#### -POST/address
```json
{
  "addressLine": "/",
  "city": "C",
  "postalCode": "<"
}
```
#### -PUT/address/id
```json
{
  "addressLine": "&",
  "city": "I",
  "postalCode": "!"
}
```
<br>

### PRODUCT RESOURCE
#### -POST/product
```json
{
  "productName": "5",
  "productDesc": "4",
  "image": "B",
  "price": 0,
  "stock": 0
}
```
#### -PUT/product/id
```json
{
  "productName": "a",
  "productDesc": "7",
  "image": "H",
  "price": 0,
  "stock": 0
}
```

Referensi <br>
[Back End with Java Recordings](https://www.youtube.com/playlist?list=PLHV3SW71BZ_LfNaZbb1GxRu9gVQDu-vh3)<br>
[Belajar RESTful API - 19 Tahapan Membuat RESTful API](https://youtu.be/oU5IWALolS8) <br>
[MS SQL Database from E-commerce application](https://youtu.be/z8QnEv668r0)
