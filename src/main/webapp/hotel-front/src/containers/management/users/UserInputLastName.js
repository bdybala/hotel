import React, { Component } from 'react';

import { FormGroup, ControlLabel, HelpBlock, FormControl, InputGroup } from 'react-bootstrap';

class UserInputLastName extends Component {
	constructor(props) {
		super(props);
		this.handleChange = this.handleChange.bind(this);
	}

	getValidationState() {
		return this.props.validate('lastName');
	}

	handleChange(e) {
		this.props.save(e.target.value);
	}
	
	render() {
		return (
			<FormGroup 
				controlId="formLastNameInput"
				validationState={this.getValidationState()}
			>
			<ControlLabel>Wpisz nazwisko: </ControlLabel>
			<InputGroup>
				<InputGroup.Addon>Aa</InputGroup.Addon>
				{this.props.readOnly && <FormControl
						type="text"
						value={this.props.value}
						placeholder="Kowalski"
						onChange={this.handleChange}
						readOnly
				/>
				}
				{!this.props.readOnly && <FormControl
						type="text"
						value={this.props.value}
						placeholder="Kowalski"
						onChange={this.handleChange}
				/>
				}
				<FormControl.Feedback />
			</InputGroup>
			<HelpBlock>Nazwisko powinno mieć conajmniej 3 znaki.</HelpBlock>
			</FormGroup>
		);
	}
}

export default UserInputLastName;