import React, {Component} from 'react';
import {Form, Panel} from "react-bootstrap";
import UserInputEmail from "./management/UserInputEmail";
import SinglePasswordInput from "./management/SinglePasswordInput";

class LoginPanel extends Component {
  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
    this.getValidationState = this.getValidationState.bind(this);

    this.setEmail = this.setEmail.bind(this);
    this.setPassword = this.setPassword.bind(this);

    this.state = {
      email: '',
      password: ''
    }
  }

  handleClick() {
    console.log(this.state);

    const credentials = {
      email: this.state.email,
      password: this.state.password
    };

    this.props.login(credentials);
  }

  getValidationState() {
    return null;
  }

  setEmail(email) {
    this.setState({email: email});
  }

  setPassword(field, password) {
    this.setState({password: password});
  }

  render() {
    return (
        <div>
          <Panel>
            <Panel.Heading>
              <Panel.Toggle>
                <Panel.Title className="panelTitle">
                  &nbsp;
                  Logowanie
                </Panel.Title>
              </Panel.Toggle>
            </Panel.Heading>
            <Panel.Body>
              <Form>
                <UserInputEmail save={this.setEmail}
                                validate={this.getValidationState}/>
                <SinglePasswordInput save={this.setPassword}/>
                <button type="button" className="btn btn-primary"
                        onClick={this.handleClick}>
                  Dodaj
                </button>
              </Form>
            </Panel.Body>
          </Panel>
        </div>
    )
  }
}

export default LoginPanel;