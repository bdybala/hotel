import React, {Component} from 'react';
import {ControlLabel, FormControl, FormGroup, InputGroup} from "react-bootstrap";


class RoomInputMaxCapacity extends Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    getValidationState() {
        return this.props.validate('maxCapacity');
    }

    handleChange(e) {
        this.props.save(e.target.value);
    }

    render() {
        return (
            <FormGroup
                controlId="formMaxCapacityInput"
                validationState={this.getValidationState()}
            >
                <ControlLabel>Wpisz maksymalną liczbę gości: </ControlLabel>
                <InputGroup>
                    <InputGroup.Addon>Aa</InputGroup.Addon>
                    <FormControl
                        type="number"
                        value={this.props.value}
                        placeholder="5"
                        onChange={this.handleChange}
                    />
                    <FormControl.Feedback/>
                </InputGroup>
            </FormGroup>
        );
    }
}

export default RoomInputMaxCapacity;