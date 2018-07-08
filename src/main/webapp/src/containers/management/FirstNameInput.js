import React, { Component } from 'react';

import { FormGroup, ControlLabel, HelpBlock, FormControl, InputGroup } from 'react-bootstrap';

class FirstNameInput extends Component {
	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
	}

	getValidationState() {
		return this.props.validate('firstName');
	}

	handleChange(e) {
		this.props.save(e.target.value);
	}
	
	render() {
		return (
			<FormGroup 
				controlId="formFirstNameInput"
				validationState={this.getValidationState()}
			>
			<ControlLabel>Wpisz imię: </ControlLabel>
			<InputGroup>
				<InputGroup.Addon>Aa</InputGroup.Addon>
				<FormControl
					type="text"
					value={this.props.value}
					placeholder=" Wpisz imię:"
					onChange={this.handleChange}
				/>
				<FormControl.Feedback />
			</InputGroup>
			<HelpBlock>Imię powinno mieć conajmniej 3 znaki.</HelpBlock>
			</FormGroup>
		);
	}
}

export default FirstNameInput;