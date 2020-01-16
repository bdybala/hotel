import React, {Component} from 'react';
import {Table} from "react-bootstrap";

class ReservationList extends Component {

  render() {
    const isLoading = this.props.isLoading;

    if (isLoading) {
      return <p> Loading... </p>;
    }

    if (this.props.reservations) {
      var listOfReservations = this.props.reservations.map((item, i) => {
        return <tr key={i}>
          <td>{i + 1}</td>
          <td>{item.room.number}</td>
          <td>{item.firstName} {item.lastName}</td>
          <td>{item.email}</td>
          <td>{new Date(item.since).toDateString()}</td>
          <td>{new Date(item.upTo).toDateString()}</td>
        </tr>
      });
    }
    return (
        <div className='RoomsList'>
          <h3>Lista rezerwacji</h3>
          <Table responsive striped bordered condensed hover>
            <thead>
            <tr>
              <th>#</th>
              <th>Numer pokoju</th>
              <th>Imię i nazwisko</th>
              <th>Email</th>
              <th>Od</th>
              <th>Do</th>
            </tr>
            </thead>
            <tbody>
            {listOfReservations}
            </tbody>
          </Table>
        </div>
    );
  }
}

export default ReservationList;