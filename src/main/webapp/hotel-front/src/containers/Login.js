import React, {Component} from 'react';
import LoginPanel from "./LoginPanel";

import axios from 'axios';
import {Button, Modal} from "react-bootstrap";

const API = 'http://localhost:8080/api';
const LOGIN = '/users/login';

class Login extends Component {

  constructor(props) {
    super(props);

    this.login = this.login.bind(this);
    this.closeErrorModal = this.closeErrorModal.bind(this);
    this.showErrorModal = this.showErrorModal.bind(this);

    this.state = {
      show: false,
      errorMessage: '',
    };
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

  login(credentials) {
    console.log(credentials);
    axios.post(API + LOGIN, {
      login: credentials.email,
      password: credentials.password,
    })
    .then(result => {
      this.props.handleLogin(result.data);
      this.props.history.push('/');
    })
    .catch(error => {
      this.showErrorModal('Złe dane logowania!');
      console.log(error);
    });
  }

  render() {
    return (
        <div>
          <LoginPanel login={this.login}/>

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

export default Login;