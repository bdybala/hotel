import React, {Component} from 'react';
import {Button, Modal} from "react-bootstrap";

import axios from 'axios';
import AvailabilityList from "./AvailabilityList";
import AvailabilitySearchPanel from "./AvailabilitySearchPanel";

const API = 'http://localhost:8080/api';
const FIND_AVAILABLE_ROOMS = '/rooms/availability';

class Availability extends Component {
  constructor(props) {
    super(props);
    this.closeErrorModal = this.closeErrorModal.bind(this);
    this.showErrorModal = this.showErrorModal.bind(this);
    this.refreshRoomsList = this.refreshRoomsList.bind(this);
    this.updateSearchFields = this.updateSearchFields.bind(this);

    this.state = {
      show: false,
      errorMessage: 'initMessage',
      isLoadingAvailableRooms: false,
      currentPage: null,
      totalPages: null,
      since: '1578258122',
      upTo: '1578258122',
      roomTypeName: 'SINGLE_ROOM',
      maxCapacity: null,
      availableRooms: [],
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
      roomTypeName: this.state.roomTypeName,
      maxCapacity: this.state.maxCapacity,
    })
    .then(result => {
      this.setState({
        currentPage: result.data.currentPage,
        totalPages: result.data.totalPages,
        availableRooms: result.data.availableRooms,
        isLoadingAvailableRooms: false
      })
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

  handleMakeReservation(id) {
    console.log('Make reservation for: ' + id);
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
              handleMakeReservation={this.handleMakeReservation}

          />

          <Modal show={this.state.show} onHide={this.closeErrorModal}>
            <Modal.Header>
              <Modal.Title>Wystąpił błąd!</Modal.Title>
            </Modal.Header>
            <Modal.Body> {this.state.errorMessage} </Modal.Body>
            <Modal.Footer>
              <Button onClick={this.closeErrorModal}>Close</Button>
            </Modal.Footer>
          </Modal>
        </div>
    )
  }

  componentDidMount() {
    this.refreshRoomsList();
  }
}

export default Availability;