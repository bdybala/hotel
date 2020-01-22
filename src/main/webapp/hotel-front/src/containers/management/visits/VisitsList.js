import React, {Component} from 'react';
import {Button, ButtonToolbar, Glyphicon, Table} from "react-bootstrap";

class VisitsList extends Component {

  handlePayment(visit) {
    console.log('Pay: ' + visit.id)
  }

  handleCheckOut(visit) {
    console.log('CheckOut: ' + visit.id)
  }

  render() {
    const isLoading = this.props.isLoading;

    if (isLoading) {
      return <p> Loading... </p>;
    }

    if (this.props.visits) {
      var listOfReservations = this.props.visits.map((item, i) => {
        return <tbody key={i}>
        <tr>
          <td>{i + 1}</td>
          <td>{item.room.number}</td>
          <td>{item.guests.length}</td>
          <td>{item.guests[0].firstName} {item.guests[0].lastName}</td>
          <td>{new Date(item.since).toDateString()}</td>
          <td>{new Date(item.upTo).toDateString()}</td>
          <td>
            <ButtonToolbar>
              <Button
                  bsStyle="primary"
                  onClick={(e) => this.handlePayment(item)}>
                <Glyphicon glyph='glyphicon glyphicon-eur'
                />
              </Button>
              <Button bsStyle="success"
                      onClick={(e) => this.handleCheckOut(item)}>
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
              <th>Liczba gości</th>
              <th>Imię i nazwisko</th>
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

export default VisitsList;