import React, {Component} from 'react';
import ReservationList from "./ReservationList";

import axios from 'axios';

const API = 'http://localhost:8080/api';
const MAKE_RESERVATION = '/reservation';

class ManageReservations extends Component {
  constructor(props) {
    super(props);

    this.state = {
      reservations: [],
      isLoading: false,
    }
  }

  getReservations() {
    axios.get(API + MAKE_RESERVATION)
    .then(response => {
      this.setState({reservations: response.data, isLoading: false});
    })
    .catch(error => {
      this.setState({isLoading: true});
      this.showErrorModal(error.message);
    })
  }

  render() {
    return (
        <div className="ManageReservations">
          <ReservationList reservations={this.state.reservations}/>
        </div>
    )
  }

  componentDidMount() {
    this.getReservations();
  }
}

export default ManageReservations;