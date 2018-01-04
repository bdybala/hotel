1. Configuration

Necessary procedures at Postgresql Database:
```

CREATE OR REPLACE FUNCTION countTotalPrice(public.hotel_rooms.id%TYPE, date, date)
  RETURNS double precision AS
$$
DECLARE
    d date := $2;
	room_id public.hotel_rooms.id%TYPE := $1;
    total_price double precision := 0;
    temp_price double precision :=0;
BEGIN

LOOP
    SELECT p.value
    INTO temp_price
    FROM public.hotel_prices p
    WHERE p.rooms_id = room_id AND d BETWEEN p.since AND p.up_to;
    total_price := total_price + temp_price;
    d := d + 1;
    exit WHEN d > $3;
END LOOP;
return total_price;
END
$$ LANGUAGE plpgsql;

```
```
CREATE OR REPLACE FUNCTION getFreeRooms(public.hotel_room_types.name%TYPE, date, date)
  RETURNS table(room_id bigint, max_capacity integer, room_number character varying(255), room_type character varying(255), total_price double precision) AS
$$
DECLARE
    d date := $2;
    total_price double precision := 0;
    temp_price double precision :=0;
BEGIN
	RETURN QUERY
	SELECT
    	r.id room_id,
        r.max_capacity max_capacity,
        r."number" room_number,
        rt.description room_type,
        countTotalPrice(r.id, $2, $3) total_price
    FROM public.hotel_rooms r JOIN public.hotel_room_types rt ON r.room_types_id = r.id
    	LEFT OUTER JOIN public.hotel_occupied_rooms o ON o.rooms_id = r.id
        LEFT OUTER JOIN public.hotel_reserved_rooms rr ON rr.rooms_id = r.id
    WHERE rt.name LIKE $1 AND
    	(o.id IS null OR $2 > o.up_to OR o.since > $3) AND
        (rr.id IS null OR $2 > rr.up_to OR rr.since > $3);
END
$$ LANGUAGE plpgsql;


select * FROM
	getFreeRooms('DOUBLE_ROOM', '2017-12-30'::date, '2018-01-07'::date) 

```

2. Rest API

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
