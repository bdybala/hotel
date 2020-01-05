import React, {Component} from 'react';
import {ControlLabel, FormControl, FormGroup, HelpBlock, InputGroup} from "react-bootstrap";


class RoomInputNumber extends Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    getValidationState() {
        return this.props.validate('number');
    }

    handleChange(e) {
        this.props.save(e.target.value);
    }

    render() {
        return (
            <FormGroup
                controlId="formNumberInput"
                validationState={this.getValidationState()}
            >
                <ControlLabel>Wpisz numer pokoju: </ControlLabel>
                <InputGroup>
                    <InputGroup.Addon>Aa</InputGroup.Addon>
                    <FormControl
                        type="text"
                        value={this.props.value}
                        placeholder="221B"
                        onChange={this.handleChange}
                    />
                    <FormControl.Feedback/>
                </InputGroup>
                <HelpBlock>Nieprawidłowy numer pokoju.</HelpBlock>
            </FormGroup>
        );
    }
}

export default RoomInputNumber;