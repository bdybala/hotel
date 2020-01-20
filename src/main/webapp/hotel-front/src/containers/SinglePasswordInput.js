import React, {Component} from 'react';

import {
  ControlLabel,
  FormControl,
  FormGroup,
  InputGroup
} from 'react-bootstrap';

class SinglePasswordInput extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.state = {
      password: '',
    };
  }

  getValidationState() {
    return null;
  }

  handleChange(e) {
    this.setState({[e.target.name]: e.target.value});
    this.props.save(e.target.name, e.target.value);
  }

  render() {
    return (
        <div>
          <FormGroup
              controlId="formPasswordInput"
          >
            <ControlLabel>Wpisz Hasło: </ControlLabel>
            <InputGroup>
              <InputGroup.Addon>**</InputGroup.Addon>
              <FormControl
                  type="password"
                  name="password"
                  value={this.state.password}
                  placeholder=" Hasło"
                  onChange={this.handleChange}
              />
              <FormControl.Feedback/>
            </InputGroup>
          </FormGroup>
        </div>
    );
  }
}

export default SinglePasswordInput;