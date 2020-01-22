import React, {Component} from 'react';
import axios from "axios";
import VisitsList from "./visits/VisitsList";
import {Button, Modal} from "react-bootstrap";

const API = 'http://localhost:8080/api';
const VISIT = '/visit';

class ManageVisits extends Component {
  constructor(props) {
    super(props);

    this.closeErrorModal = this.closeErrorModal.bind(this);
    this.showErrorModal = this.showErrorModal.bind(this);

    this.state = {
      errorMesage: '',
      visits: [],
      isLoading: false,
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

  getVisits() {
    axios.get(API + VISIT)
    .then(response => {
      this.setState({visits: response.data, isLoading: false});
    })
    .catch(error => {
      this.setState({isLoading: true});
      this.showErrorModal(error.message);
    })
  }

  render() {
    return (<div>
      <VisitsList visits={this.state.visits}/>

      <Modal show={this.state.show} onHide={this.closeErrorModal}>
        <Modal.Header>
          <Modal.Title>Wystąpił błąd!</Modal.Title>
        </Modal.Header>
        <Modal.Body> {this.state.errorMessage} </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.closeErrorModal}>Zamknij</Button>
        </Modal.Footer>
      </Modal>
    </div>)
  }

  componentDidMount() {
    this.getVisits()
  }

}

export default ManageVisits;