import React, {Component} from 'react';

import {Form, Panel} from 'react-bootstrap';

import UserInputPassword from './management/UserInputPassword';
import UserInputFirstName from './management/UserInputFirstName';
import UserInputLastName from './management/UserInputLastName';
import UserInputEmail from './management/UserInputEmail';

import './management/UserRegisterPanel.css';

class RegisterGuest extends Component {
  constructor(props, context) {
    super(props, context);

    this.setLastName = this.setLastName.bind(this);
    this.setFirstName = this.setFirstName.bind(this);
    this.setEmail = this.setEmail.bind(this);
    this.setPassword = this.setPassword.bind(this);

    this.handleClick = this.handleClick.bind(this);
    this.getValidationState = this.getValidationState.bind(this);
    this.containsFalse = this.containsFalse.bind(this);

    this.validationArray = {
      lastName: '',
      firstName: '',
      email: '',
      firstPassword: '',
      secondPassword: '',
    };

    this.state = {
      formValid: false,
      lastName: '',
      firstName: '',
      email: '',
      firstPassword: '',
      secondPassword: '',
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
      case 'firstPassword':
        length = this.state.firstPassword.length;
        if (length > 5) {
          this.validationArray.firstPassword = true;
          result = 'success';
          break;
        } else if (length > 0) {
          this.validationArray.firstPassword = false;
          result = 'error';
          break;
        }
        this.validationArray.firstPassword = false;
        result = null;
        break;
      case 'secondPassword':
        const passwordsMatch = this.state.firstPassword
            === this.state.secondPassword;
        length = this.state.secondPassword.length;
        if (length < 1) {
          this.validationArray.secondPassword = false;
          result = null;
          break;
        } else if (passwordsMatch) {
          this.validationArray.secondPassword = true;
          result = 'success';
          break;
        }
        this.validationArray.secondPassword = false;
        result = 'error';
        break;
      default:
        result = 'warning';

    }
    return result;
  }

  setLastName(lastName) {
    this.setState({lastName: lastName}, this.checkForm);
  }

  setFirstName(firstName) {
    this.setState({firstName: firstName}, this.checkForm);
  }

  setEmail(email) {
    this.setState({email: email}, this.checkForm);
  }

  setPassword(field, password) {
    if (field === 'firstPassword') {
      this.setState({firstPassword: password}, this.checkForm);
    } else if (field === 'secondPassword') {
      this.setState({secondPassword: password}, this.checkForm);
    }
  }

  checkForm() {
    if (!this.containsFalse()) {
      this.setState({formValid: true})
    } else {
      this.setState({formValid: false})
    }
  }

  handleClick() {
    if (this.containsFalse()) {
      console.log(this.state);
    } else {
      const newUser = {
        firstName: this.state.firstName,
        lastName: this.state.lastName,
        email: this.state.email,
        password: this.state.firstPassword,
        roleNameEnum: 'GUEST'
      };
      this.props.registerUser(newUser);
    }
  }

  containsFalse() {
    let containsFalse = false;
    for (const key in this.validationArray) {
      if (!this.validationArray[key]) {
        containsFalse = true;
      }
    }
    return containsFalse;
  }

  render() {
    return (
        <div className='UserRegisterPanel'>
          <Panel>
            <Panel.Heading>
              <Panel.Title className="panelTitle">
                &nbsp;
                Rejestracja nowego użytkownika
              </Panel.Title>
            </Panel.Heading>
            <Panel.Body>
              <Form>
                <UserInputFirstName save={this.setFirstName}
                                    validate={this.getValidationState}/>
                <UserInputLastName save={this.setLastName}
                                   validate={this.getValidationState}/>
                <UserInputEmail save={this.setEmail}
                                validate={this.getValidationState}/>
                <UserInputPassword save={this.setPassword}
                                   validate={this.getValidationState}/>
                <button type="button" className="btn btn-primary"
                        onClick={this.handleClick}
                        disabled={!this.state.formValid}>
                  Zapisz
                </button>
              </Form>
            </Panel.Body>
          </Panel>
        </div>
    );
  }
}

export default RegisterGuest;