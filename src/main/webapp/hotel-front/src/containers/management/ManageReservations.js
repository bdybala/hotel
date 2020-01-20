import React, {Component} from 'react';
import ReservationList from "./reservations/ReservationList";

import axios from 'axios';
import CookieManager from "../../CookieManager";
import {Button, Modal} from "react-bootstrap";
import ReservationsNewCheckIn from "./reservations/ReservationsNewCheckIn";

const API = 'http://localhost:8080/api';
const MAKE_RESERVATION = '/reservation';

class ManageReservations extends Component {
  constructor(props) {
    super(props);

    this.closeErrorModal = this.closeErrorModal.bind(this);
    this.showErrorModal = this.showErrorModal.bind(this);
    this.removeReservation = this.removeReservation.bind(this);
    this.handleChooseReservation = this.handleChooseReservation.bind(this);

    let loggedUser = CookieManager.getLoggedUser();
    this.state = {
      show: false,
      errorMessage: '',
      reservations: [],
      isLoading: false,
      loggedUser: loggedUser,
      chosenReservation: null,
      showCheckInPanel: false,
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

  getReservations() {
    if (this.state.loggedUser.roleName === 'GUEST') {
      axios.get(API + MAKE_RESERVATION, {
        params: {email: this.state.loggedUser.login}
      })
      .then(response => {
        this.setState({reservations: response.data, isLoading: false});
      })
      .catch(error => {
        this.setState({isLoading: true});
        this.showErrorModal(error.message);
      })
    } else {
      axios.get(API + MAKE_RESERVATION)
      .then(response => {
        this.setState({reservations: response.data, isLoading: false});
      })
      .catch(error => {
        this.setState({isLoading: true});
        this.showErrorModal(error.message);
      })
    }
  }

  removeReservation(id) {
    axios.delete(API + MAKE_RESERVATION + "/" + id)
    .then(response => {
      this.getReservations();
    })
    .catch(error => {
      this.showErrorModal(error.message);
    })
  }

  handleChooseReservation(item) {
    console.log(item);
    this.setState({
      showCheckInPanel: true,
      chosenReservation: item
    });
  }

  render() {
    return (
        <div className="ManageReservations">
          <ReservationList reservations={this.state.reservations}
                           removeReservation={this.removeReservation}
                           handleChooseReservation={this.handleChooseReservation}/>

          <ReservationsNewCheckIn
              doCheckIn={this.doCheckIn}
              show={this.state.showCheckInPanel}
              reservation={this.state.chosenReservation}
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

  componentDidMount() {
    this.getReservations();
  }
}

export default ManageReservations;