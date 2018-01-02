1. Rest API

⋅⋅* Pobranie listy dostępnych ról
```
GET: /api/roles
```

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

⋅⋅* Logowanie
```
POST: /api/users/login
THROWS: 401 when failed
PAYLOAD:
{
    "username":"login",
    "password":"haslo",
}
```

⋅⋅* Pobranie listy dostępnych rodzajów pokoi
```
GET: /api/roomTypes
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
⋅⋅* Pobranie listy klientów
```
GET: /api/customers
```

⋅⋅* Pobranie listy klientów z wyszukiwaniem
```
GET: /api/customers/?search=firstName:jan,lastName:kowal
```

⋅⋅* Wyszukanie klienta przez nr dowodu lub paszportu
```
GET: /api/customers/byIdCard/{idCardNumber}
Throws: 404 when not found
```

⋅⋅* Pobranie wolnych pokoi
```
POST: /api/rooms/free
PAYLOAD:
{
    "since":"2018-01-03",
    "upTo":"2018-01-08"
}
```

⋅⋅* Pobranie wolnych pokoi o typie
```
GET: /api/rooms/freeByRoomType?since=2018-01-01&to=2018-01-02&roomType=DOUBLE_ROOM
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

⋅⋅* Wyszukanie rezerwacji w przedziale czasowym
```
GET: /api/reservedRooms/betweenTwoDates?since=2018-01-03&to=2018-01-08

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
    "customers": [
    	{"id":1},
    	{"id":2}
	]
}
```

⋅⋅* Sprawdzenie ceny pokoju w dniu
```
POST: /api/prices/byRoom
PAYLOAD:
{
    "roomId":1,
    "day":"2017-12-24"
}
```
⋅⋅* Sprawdzenie cen pokoju
```
GET: /api/prices/byRoom/{roomId}
```

⋅⋅* Pobranie listy rezerwacji jednego klienta
```
GET: /api/reservedRooms/{customerId}
```
