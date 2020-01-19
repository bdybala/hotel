import React, {Component} from 'react';
import {Button, ButtonToolbar, Glyphicon, Table} from "react-bootstrap";

class ReservationList extends Component {

  constructor(props) {
    super(props);

    this.state = {};
  }

  handleRemoveClick(id) {
    this.props.removeReservation(id);
  }

  render() {
    const isLoading = this.props.isLoading;

    if (isLoading) {
      return <p> Loading... </p>;
    }

    if (this.props.reservations) {
      var listOfReservations = this.props.reservations.map((item, i) => {
        return <tbody key={i}>
        <tr>
          <td>{i + 1}</td>
          <td>{item.room.number}</td>
          <td>{item.firstName} {item.lastName}</td>
          <td>{item.email}</td>
          <td>{new Date(item.since).toDateString()}</td>
          <td>{new Date(item.upTo).toDateString()}</td>
          <td>
            <ButtonToolbar>
              <Button bsStyle="danger"
                      onClick={(e) => this.handleRemoveClick(item.id, e)}>
                <Glyphicon glyph='glyphicon glyphicon-remove'/>
              </Button>
            </ButtonToolbar>
          </td>
        </tr>
        </tbody>
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
              <th>Opcje</th>
            </tr>
            </thead>
            {listOfReservations}
          </Table>
        </div>
    );
  }
}

export default ReservationList;