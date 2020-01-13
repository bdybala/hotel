import React, {Component} from 'react';
import RegisterGuest from "./RegisterGuest";
import axios from "axios";
import {Button, Modal} from "react-bootstrap";

const API = 'http://localhost:8080/api';
const REGISTER_USER = '/users/register';

class Register extends Component {
  constructor(props) {
    super(props);

    this.closeErrorModal = this.closeErrorModal.bind(this);
    this.showErrorModal = this.showErrorModal.bind(this);
    this.registerUser = this.registerUser.bind(this);

    this.state = {
      show: false,
      errorMessage: '',
      users: [],
    }
  }

  redirectToTarget() {
    this.setState({toLogin: true});

    this.props.history.push('/login');
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

  registerUser(newUser) {
    console.log(newUser);
    axios.post(API + REGISTER_USER, {
      firstName: newUser.firstName,
      lastName: newUser.lastName,
      email: newUser.email,
      password: newUser.password,
      roleNameEnum: newUser.roleNameEnum,
    })
    .then(result => {
      this.redirectToTarget();
    })
    .catch(error => {
      if (error.response.status === 409) {
        this.showErrorModal(
            'Użytkownik o takim e-mail istnieje już w systemie!');
      } else {
        this.showErrorModal(error.response.data.exception);
      }
    });
  }

  render() {
    return (<div>
      <RegisterGuest registerUser={this.registerUser}/>
      <Modal show={this.state.show} onHide={this.closeErrorModal}>
        <Modal.Header>
          <Modal.Title>Wystąpił błąd!</Modal.Title>
        </Modal.Header>
        <Modal.Body> {this.state.errorMessage} </Modal.Body>
        <Modal.Footer>
          <Button onClick={this.closeErrorModal}>Close</Button>
        </Modal.Footer>
      </Modal>
    </div>)
  }
}

export default Register;