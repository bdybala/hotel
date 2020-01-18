import React, {Component} from 'react';
import UserInputFirstName from "./management/UserInputFirstName";
import UserInputLastName from "./management/UserInputLastName";
import UserInputEmail from "./management/UserInputEmail";
import {Form, Panel} from "react-bootstrap";
import CookieManager from "../CookieManager";

class AvailabilityNewReservation extends Component {
  constructor(props) {
    super(props);

    this.setLastName = this.setLastName.bind(this);
    this.setFirstName = this.setFirstName.bind(this);
    this.setEmail = this.setEmail.bind(this);

    this.handleClick = this.handleClick.bind(this);
    this.getValidationState = this.getValidationState.bind(this);
    this.containsFalse = this.containsFalse.bind(this);

    let loggedUser = CookieManager.getLoggedUser();

    if (loggedUser.roleName === 'GUEST') {
      this.state = {
        lastName: loggedUser.firstName,
        firstName: loggedUser.lastName,
        email: loggedUser.login,
        disable: false,
        isGuest: true,
      };
    } else {
      this.state = {
        lastName: '',
        firstName: '',
        email: '',
        disable: true,
        isGuest: false,
      };
    }

    this.validationArray = {
      lastName: '',
      firstName: '',
      email: '',
    };

  }

  getValidationState(field) {
    let result = null;
    switch (field) {
      case 'firstName':
        let length = this.state.firstName.length;
        if (length > 2) {
          this.validationArray.firstName = true;
          result = 'success';
          break;
        } else if (length > 0) {
          this.validationArray.firstName = false;
          result = 'error';
          break;
        }
        this.validationArray.firstName = false;
        result = null;
        break;
      case 'lastName':
        length = this.state.lastName.length;
        if (length > 2) {
          this.validationArray.lastName = true;
          result = 'success';
          break;
        } else if (length > 0) {
          this.validationArray.lastName = false;
          result = 'error';
          break;
        }
        this.validationArray.lastName = false;
        result = null;
        break;
      case 'email':
        const reg = /^[a-z\d]+[\w\d.-]*@(?:[a-z\d]+[a-z\d-]+\.){1,5}[a-z]{2,6}$/i;
        const value = this.state.email;
        let emailValid = value.match(reg);
        if (emailValid) {
          this.validationArray.email = true;
          result = 'success';
          break;
        } else if (value.length > 0) {
          this.validationArray.email = false;
          result = 'error';
          break;
        }
        this.validationArray.email = false;
        result = null;
        break;
      default:
        result = 'warning';

    }
    return result;
  }

  setLastName(lastName) {
    this.setState({lastName: lastName}, this.containsFalse);
  }

  setFirstName(firstName) {
    this.setState({firstName: firstName}, this.containsFalse);
  }

  setEmail(email) {
    this.setState({email: email}, this.containsFalse);
  }

  handleClick() {
    if (this.containsFalse()) {
      console.log(this.state);
    } else {
      const newReservation = {
        firstName: this.state.firstName,
        lastName: this.state.lastName,
        email: this.state.email,
        room: this.props.room,
      };
      this.props.newReservation(newReservation);
    }
  }

  containsFalse() {
    let containsFalse = false;
    for (const key in this.validationArray) {
      if (!this.validationArray[key]) {
        containsFalse = true;
      }
    }
    this.setState({disable: containsFalse});
    return containsFalse;
  }

  render() {
    if (!this.props.show) {
      return null;
    } else {
      return (
          <div className='UserRegisterPanel'>
            <Panel>
              <Panel.Heading>
                <Panel.Title className="panelTitle">
                  Rezerwacja pokoju {this.props.room.number}
                </Panel.Title>
              </Panel.Heading>
              <Panel.Body>
                <Form>
                  <UserInputFirstName save={this.setFirstName}
                                      validate={this.getValidationState}
                                      readOnly={this.state.isGuest}/>
                  <UserInputLastName save={this.setLastName}
                                     validate={this.getValidationState}
                                     readOnly={this.state.isGuest}/>
                  <UserInputEmail save={this.setEmail}
                                  validate={this.getValidationState}
                                  readOnly={this.state.isGuest}/>
                  <button type="button" className="btn btn-primary"
                          onClick={this.handleClick}
                          disabled={this.state.disable}>
                    Rezerwuj pokój
                  </button>
                </Form>
              </Panel.Body>
            </Panel>
          </div>)
    }

  }

}

export default AvailabilityNewReservation;