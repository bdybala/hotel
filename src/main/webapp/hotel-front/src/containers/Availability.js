import React, {Component} from 'react';
import {Button, Modal} from "react-bootstrap";

import axios from 'axios';
import AvailabilityList from "./AvailabilityList";
import AvailabilitySearchPanel from "./AvailabilitySearchPanel";
import AvailabilityNewReservation from "./AvailabilityNewReservation";

const API = 'http://localhost:8080/api';
const FIND_AVAILABLE_ROOMS = '/rooms/availability';
const MAKE_RESERVATION = '/reservation';

class Availability extends Component {
  constructor(props) {
    super(props);
    this.closeErrorModal = this.closeErrorModal.bind(this);
    this.showErrorModal = this.showErrorModal.bind(this);
    this.refreshRoomsList = this.refreshRoomsList.bind(this);
    this.updateSearchFields = this.updateSearchFields.bind(this);
    this.newReservation = this.newReservation.bind(this);
    this.chooseRoomToBook = this.chooseRoomToBook.bind(this);

    this.state = {
      show: false,
      errorMessage: 'initMessage',
      isLoadingAvailableRooms: false,
      currentPage: null,
      totalPages: null,
      since: null,
      upTo: null,
      maxCapacity: null,
      availableRooms: [],
      showReservationPanel: false,
      chosenRoom: null,
    }
  }

  closeErrorModal() {
    this.setState({show: false});
  }

  showErrorModal(errorMessage) {
    this.setState({
      show: true,
      errorMessage: errorMessage,
    });
  }

  refreshRoomsList() {
    this.setState({isLoadingAvailableRooms: true});
    axios.post(API + FIND_AVAILABLE_ROOMS, {
      currentPage: this.state.currentPage,
      since: this.state.since,
      upTo: this.state.upTo,
      roomTypeName: 'SINGLE_ROOM',
      maxCapacity: this.state.maxCapacity,
    })
    .then(result => {
      this.setState({
        currentPage: result.data.currentPage,
        totalPages: result.data.totalPages,
        availableRooms: result.data.availableRooms,
        isLoadingAvailableRooms: false
      });
      this.setState({
        showReservationPanel: false,
        chosenRoom: null
      });
    })
    .catch(error => {
      this.setState({isLoadingRooms: false});
      this.showErrorModal(error.message);
    })
  }

  updateSearchFields(since, upTo, maxCapacity) {
    this.setState({
      since: since,
      upTo: upTo,
      maxCapacity: maxCapacity,
    }, this.refreshRoomsList);
  }

  chooseRoomToBook(item) {
    console.log(item);
    this.setState({
      showReservationPanel: true,
      chosenRoom: item
    });
  }

  newReservation(reservation) {
    console.log('New reservation for customer');
    console.log(reservation);
    axios.post(API + MAKE_RESERVATION, {
      roomId: reservation.room.id,
      since: this.state.since,
      upTo: this.state.upTo,
      firstName: reservation.firstName,
      lastName: reservation.lastName,
      email: reservation.email,
    })
    .then(result => {
      this.clearChoice();
    })
    .catch(error => {
      this.setState({isLoadingRooms: false});
      this.showErrorModal(error.message);
    });
  }

  clearChoice() {
    this.setState({
      showReservationPanel: false,
      chosenRoom: null,
      availableRooms: [],
    });
  }

  render() {
    return (
        <div className="Availability">
          <h3>Dostępne pokoje</h3>

          <AvailabilitySearchPanel
              updateSearchFields={this.updateSearchFields}
          />

          <AvailabilityList
              changePage={this.changePage}
              currentPage={this.state.currentPage}
              totalPages={this.state.totalPages}
              isLoading={this.state.isLoadingAvailableRooms}
              availableRooms={this.state.availableRooms}
              chooseRoomToBook={this.chooseRoomToBook}
          />

          <AvailabilityNewReservation
              newReservation={this.newReservation}
              show={this.state.showReservationPanel}
              room={this.state.chosenRoom}
          />

          <Modal show={this.state.show} onHide={this.closeErrorModal}>
            <Modal.Header>
              <Modal.Title>Wystąpił błąd!</Modal.Title>
            </Modal.Header>
            <Modal.Body> {this.state.errorMessage} </Modal.Body>
            <Modal.Footer>
              <Button onClick={this.closeErrorModal}>Zamknij</Button>
            </Modal.Footer>
          </Modal>
        </div>
    )
  }

}

export default Availability;