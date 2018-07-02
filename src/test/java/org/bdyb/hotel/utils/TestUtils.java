package org.bdyb.hotel.utils;

import org.bdyb.hotel.domain.Room;
import org.bdyb.hotel.domain.RoomType;
import org.joda.time.DateTime;

import java.util.Date;

public class TestUtils {

    /*Constants
    * */

    public static final Date IN_FOUR_DAYS = new DateTime().plusDays(4).toDate();
    public static final Date IN_THREE_DAYS = new DateTime().plusDays(3).toDate();
    public static final Date IN_TWO_DAYS = new DateTime().plusDays(2).toDate();
    public static final Date TOMORROW = new DateTime().plusDays(1).toDate();
    public static final Date IN_AN_HOUR = new DateTime().plusHours(1).toDate();

    public static final Date FOUR_DAYS_AGO = new DateTime().minusDays(4).toDate();
    public static final Date THREE_DAYS_AGO = new DateTime().minusDays(3).toDate();
    public static final Date TWO_DAYS_AGO = new DateTime().minusDays(2).toDate();
    public static final Date YESTERDAY = new DateTime().minusDays(1).toDate();
    public static final Date HOUR_AGO = new DateTime().minusHours(1).toDate();

    /*Builders
    * */

    public static RoomBuilder getRoomBuilder() {
        return new RoomBuilder();
    }

    public static class RoomBuilder {
        private Room room;

        RoomBuilder() {
            this.room = new Room();
            room.setCreatedTime(new Date());
            room.setFree(true);
        }

        public Room build() {
            return room;
        }

        public RoomBuilder withRoomNumber(String number) {
            room.setNumber(number);
            return this;
        }

        public RoomBuilder withRoomType(RoomType roomType) {
            room.setRoomType(roomType);
            return this;
        }

        public RoomBuilder withMaxCapacity(Integer capacity) {
            room.setMaxCapacity(capacity);
            return this;
        }
    }
}
