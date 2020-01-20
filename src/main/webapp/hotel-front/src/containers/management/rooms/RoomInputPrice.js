import React, {Component} from 'react';
import {ControlLabel, FormControl, FormGroup, InputGroup} from "react-bootstrap";


class RoomInputPrice extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }

  getValidationState() {
    return this.props.validate('price');
  }

  handleChange(e) {
    this.props.save(e.target.value);
  }

  render() {
    return (
        <FormGroup
            controlId="formPriceInput"
            validationState={this.getValidationState()}
        >
          <ControlLabel>Wpisz cenę za pokój: </ControlLabel>
          <InputGroup>
            <InputGroup.Addon>Aa</InputGroup.Addon>
            <FormControl
                type="number"
                value={this.props.value}
                placeholder="0"
                onChange={this.handleChange}
            />
            <FormControl.Feedback/>
          </InputGroup>
        </FormGroup>
    );
  }
}

export default RoomInputPrice;