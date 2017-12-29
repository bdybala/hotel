1. Rest API


⋅⋅* Rejestracja użytkownika
```
POST: /api/users
PAYLOAD:
{
    "username":"login",
    "email":"example@email.com",
    "password":"haslo",
    "firstName":"Imie",
    "lastName":"Nazwisko",
    "role": {"id":1}
}
```
⋅⋅* Dodanie pokoju
```
POST: /api/rooms
PAYLOAD:
{
    "number":"101D",
    "maxCapacity":5,
    "roomType": {"id":1}
}
```
⋅⋅* Dodanie klienta
```
POST: /api/customers
PAYLOAD:
{
    "firstName":"Imie",
    "lastName":"Nazwisko",
    "pesel":12345678900
    "email":"example@email.com",
    "birthday":"2000-01-01",
    "identityCard": {
        "number":"12312312",
        "expiringDate":"2020-10-10"
        }
}
```

⋅⋅* Dodanie rezerwacji dla klienta
```
POST: /api/reservedRooms
PAYLOAD:
{
    "since":"2018-01-01",
    "upTo":"2018-01-05",
    "room": { 
        "id":1
        },
    "customer":{
        "id":1
    }
}
```
⋅⋅* Dodanie meldunku 
```
POST: /api/occupiedRooms
PAYLOAD:
{
    "since":"2018-01-01",
    "upTo":"2018-01-05",
    "room": { 
        "id":1
        },
    "customer":{
        "id":1
    }
}
```